package com.K.government.service;

import com.K.government.bean.SourceTarget;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TableLineageService {
    // 根据 Dolphin Scheduler 调度过的Hive任务，来计算Hive中表的血缘关系
// 采用符合 ANTLR Parser 语法的骨架 SQL，专门用于抽取血缘关系 (表级别)
// 专门为 ANTLR4 Parser 定制的严格语法骨架 SQL，用于生成全链路血缘图
// 专门为升级版 ANTLR4 Parser 定制的全链路业务血缘 SQL 数组
    private static final String[] sqls = {
            // ================= 0. Data Source -> ODS (数据同步层) =================
            // 来源: MySQL2HiveODS.scala
            "INSERT INTO ods_baize_dw.ods_movie_info SELECT * FROM baize_movies_data.ml_movies",
            "INSERT INTO ods_baize_dw.ods_rating_log SELECT * FROM baize_movies_data.ml_ratings",
            "INSERT INTO ods_baize_dw.ods_link_info SELECT * FROM baize_movies_data.ml_links",
            "INSERT INTO ods_baize_dw.ods_tag_log SELECT * FROM baize_movies_data.ml_tags",

            // ================= 1. ODS -> DWD (数据清洗与明细层) =================
            // 来源: ODS2DWD.scala
            "INSERT INTO dwd_baize_dw.dwd_movie_info SELECT * FROM ods_baize_dw.ods_movie_info",
            "INSERT INTO dwd_baize_dw.dwd_rating_log SELECT * FROM ods_baize_dw.ods_rating_log",
            "INSERT INTO dwd_baize_dw.dwd_link_info SELECT * FROM ods_baize_dw.ods_link_info",
            "INSERT INTO dwd_baize_dw.dwd_tag_log SELECT * FROM ods_baize_dw.ods_tag_log",

            // ================= 2. DWD -> DWS (多维轻度汇总层) =================
            // 来源: DWD2DWS.scala (基础宽表与1d表)
            "INSERT INTO dws_baize_dw.dws_movie_info_wide SELECT * FROM dwd_baize_dw.dwd_movie_info JOIN dwd_baize_dw.dwd_link_info ON 1=1",
            "INSERT INTO dws_baize_dw.dws_movie_rating_1d SELECT * FROM dwd_baize_dw.dwd_rating_log",
            "INSERT INTO dws_baize_dw.dws_user_behavior_1d SELECT * FROM dwd_baize_dw.dwd_rating_log JOIN dwd_baize_dw.dwd_tag_log ON 1=1",

            // 来源: DWD2DWS01.scala (核心主题宽表)
            "INSERT INTO dws_baize_dw.dws_movie_evaluation SELECT * FROM dwd_baize_dw.dwd_movie_info JOIN dwd_baize_dw.dwd_rating_log ON 1=1",
            "INSERT INTO dws_baize_dw.dws_user_profile_std SELECT * FROM dwd_baize_dw.dwd_rating_log",
            "INSERT INTO dws_baize_dw.dws_genre_stat_all SELECT * FROM dwd_baize_dw.dwd_movie_info JOIN dwd_baize_dw.dwd_rating_log ON 1=1",

            // 来源: DWD2DWS_daily.scala (新增日增量统计表)
            "INSERT INTO dws_baize_dw.dws_tag_stat_1d SELECT * FROM dwd_baize_dw.dwd_tag_log",
            "INSERT INTO dws_baize_dw.dws_genre_daily_stat_1d SELECT * FROM dwd_baize_dw.dwd_movie_info JOIN dwd_baize_dw.dwd_rating_log ON 1=1",

            // ================= 3. DWS/DWD -> ADS (应用大屏与算法特征层) =================
            // 来源: DWS2ADS_UserProfile.scala (用户画像快照)
            "INSERT INTO ads_baize_dw.ads_user_profile_snapshot SELECT * FROM dws_baize_dw.dws_user_profile_std JOIN dwd_baize_dw.dwd_rating_log ON 1=1 JOIN dws_baize_dw.dws_movie_info_wide ON 1=1",

            // 来源: DWS2ADS_MovieSnapshot.scala (电影评价快照)
            "INSERT INTO ads_baize_dw.ads_movie_evaluation_snapshot SELECT * FROM dws_baize_dw.dws_movie_info_wide JOIN dws_baize_dw.dws_movie_evaluation ON 1=1 JOIN dwd_baize_dw.dwd_rating_log ON 1=1",

            // 来源: DWS2ADS_GlobalRank.scala (全局榜单)
            "INSERT INTO ads_baize_dw.ads_movie_rank_topN SELECT * FROM dws_baize_dw.dws_movie_evaluation JOIN dws_baize_dw.dws_movie_info_wide ON 1=1",
            "INSERT INTO ads_baize_dw.ads_genre_popular_stat SELECT * FROM dws_baize_dw.dws_genre_stat_all",

            // 来源: DWS2ADS_GenreTrend.scala (类型趋势分析)
            "INSERT INTO ads_baize_dw.ads_genre_trend_analysis SELECT * FROM dwd_baize_dw.dwd_rating_log JOIN dws_baize_dw.dws_movie_info_wide ON 1=1",

            // 来源: DWS2ADS_EchartsDashboard.scala (ECharts 大屏四合一)
            "INSERT INTO ads_baize_dw.ads_user_active_heatmap_stat SELECT * FROM dwd_baize_dw.dwd_rating_log",
            "INSERT INTO ads_baize_dw.ads_tag_wordcloud_stat SELECT * FROM dwd_baize_dw.dwd_tag_log",
            "INSERT INTO ads_baize_dw.ads_decade_genre_trend SELECT * FROM dws_baize_dw.dws_movie_info_wide",
            "INSERT INTO ads_baize_dw.ads_movie_scatter_stat SELECT * FROM dws_baize_dw.dws_movie_evaluation JOIN dws_baize_dw.dws_movie_info_wide ON 1=1",

            // 来源: DWS2ADS_AlgorithmFeatures.scala (算法专属特征库)
            "INSERT INTO ads_baize_dw.ads_movie_recommendation SELECT * FROM dws_baize_dw.dws_movie_info_wide JOIN dws_baize_dw.dws_movie_evaluation ON 1=1",
            "INSERT INTO ads_baize_dw.ads_user_segmentation SELECT * FROM dws_baize_dw.dws_user_profile_std"
    };


    // 最终计算出来的血缘图中的所有边
    public static List<SourceTarget> generateEdges() {
        // 用来去重，已经添加的边不再添加
        var edgeSet = new HashSet<String>();
        var edges = new ArrayList<SourceTarget>();
        for (var sql : sqls) {
            var list = TableLineage.lineage(sql);
            for (var st : list) {
                if (!edgeSet.contains(st.source() + " --> " + st.target())) {
                    edgeSet.add(st.source() + " --> " + st.target());
                    edges.add(st);
                }
            }
        }
        return edges;
    }

    // 将计算出来的血缘图的所有边保存到`neo4j`图数据库
    public static void sinkToNeo4j(List<SourceTarget> edges) {
        // 创建一个集合类型，用来保存已经添加过的节点
        var nodes = new HashSet<String>();
        var driver = GraphDatabase.driver("bolt://192.168.235.132:7687", AuthTokens.basic("neo4j", "123456"));
        var session = driver.session();
        session.executeWriteWithoutResult(tx -> {
            // 删除neo4j中所有的节点以及它们的边
            tx.run("MATCH (n) DETACH DELETE n");
            // 遍历所有的边，将节点和边添加到`neo4j`图数据库中
            for (var edge : edges) {
                // 将边的source节点添加到neo4j中
                if (!nodes.contains(edge.source())) {
                    nodes.add(edge.source());
                    tx.run("CREATE (n:Table {tableName: '" + edge.source() + "'})");
                }
                // 将边的target节点添加到neo4j中
                if (!nodes.contains(edge.target())) {
                    nodes.add(edge.target());
                    tx.run("CREATE (n:Table {tableName: '" + edge.target() + "'})");
                }
                // 将source --> target边添加到neo4j中
                tx.run("MATCH" +
                        "  (a:Table)," +
                        "  (b:Table)" +
                        "  WHERE a.tableName = '" + edge.source() + "' AND b.tableName = '" + edge.target() + "'" +
                        "  CREATE (a)-[r:OUTPUT]->(b)");
            }
        });

        session.close();
        driver.close();
    }
}
