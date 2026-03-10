package com.K.statistic.service;

import com.K.statistic.bean.GeneralStatistic;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlineStatisticService {

    // =========================================================
    // 🚀 核心大招：电影名称内存字典缓存
    // =========================================================
    private static final Map<String, String> movieIdToNameMap = new HashMap<>();
    private static boolean isMovieMapLoaded = false;

    /**
     * 懒加载机制：从 MySQL 离线宽表读取电影 ID 和名称的映射
     */
    private static synchronized void loadMovieNameMap() {
        if (isMovieMapLoaded) return;

        // 假设你在 MySQL 里的表名是 ads_movie_recommendation，里面有 movie_id 和 title 字段
        String sql = "SELECT movie_id, title FROM ads_movie_recommendation";
        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                movieIdToNameMap.put(rs.getString("movie_id"), rs.getString("title"));
            }
            isMovieMapLoaded = true;
            System.out.println("✅ [维表加载成功] 已将 " + movieIdToNameMap.size() + " 部电影名称载入内存字典！");
        } catch (SQLException e) {
            System.err.println("❌ [维表加载失败] 无法从 MySQL 加载电影名称！");
            e.printStackTrace();
        }
    }


    /**
     * 1. 获取今日实时总营收
     */
    public static BigDecimal getGMV(String date) {
        // 🚀 补充了按日期过滤
        String sql = "SELECT SUM(total_amount) AS revenue FROM ads_baize_dw.ads_realtime_revenue WHERE dt = '" + date + "'";
        BigDecimal result = new BigDecimal(0);
        try (Connection conn = DriverManager.getConnection(DATABASE.CLICKHOUSE_URL, DATABASE.CLICKHOUSE_USERNAME, DATABASE.CLICKHOUSE_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                double revenue = rs.getDouble("revenue");
                result = new BigDecimal(revenue).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 2. 获取实时收入类型构成占比
     */
    public static List<GeneralStatistic> getRevenueComposition(String date) {
        // 🚀 补充了按日期过滤
        String sql = "SELECT event_type, SUM(total_amount) AS amount FROM ads_baize_dw.ads_realtime_revenue WHERE dt = '" + date + "' GROUP BY event_type";

        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("pay_vip_month", "包月 VIP");
        typeMap.put("pay_vip_year", "包年 VIP");
        typeMap.put("pay_movie", "单片点播");

        List<GeneralStatistic> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE.CLICKHOUSE_URL, DATABASE.CLICKHOUSE_USERNAME, DATABASE.CLICKHOUSE_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String eventType = rs.getString("event_type");
                double amount = rs.getDouble("amount");

                GeneralStatistic stat = new GeneralStatistic();
                stat.setName(typeMap.getOrDefault(eventType, "其他收入"));
                stat.setValue(String.valueOf(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP)));
                result.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 3. 获取全国节点健康度地图数据
     */
    public static List<GeneralStatistic> getProvinceStatistics(String date) {
        // 🚀 补充了按日期过滤
        String sql = "SELECT cdn_node, SUM(error_count + buffer_count) AS total_issues FROM ads_baize_dw.ads_realtime_qos_stat WHERE dt = '" + date + "' GROUP BY cdn_node";

        Map<String, String> nodeToProvince = new HashMap<>();
        nodeToProvince.put("cdn_beijing", "北京");
        nodeToProvince.put("cdn_shanghai", "上海");
        nodeToProvince.put("cdn_guangdong", "广东");
        nodeToProvince.put("cdn_sichuan", "四川");
        nodeToProvince.put("cdn_zhejiang", "浙江");
        nodeToProvince.put("cdn_jiangsu", "江苏");
        nodeToProvince.put("cdn_shandong", "山东");
        nodeToProvince.put("cdn_henan", "河南");
        nodeToProvince.put("cdn_hubei", "湖北");
        nodeToProvince.put("cdn_hunan", "湖南");
        nodeToProvince.put("cdn_fujian", "福建");
        nodeToProvince.put("cdn_chongqing", "重庆");
        nodeToProvince.put("cdn_shaanxi", "陕西");
        nodeToProvince.put("cdn_liaoning", "辽宁");
        nodeToProvince.put("cdn_xinjiang", "新疆");
        nodeToProvince.put("cdn_xizang", "西藏");
        nodeToProvince.put("cdn_guangxi", "广西");
        nodeToProvince.put("cdn_neimenggu", "内蒙古");
        nodeToProvince.put("cdn_heilongjiang", "黑龙江");
        nodeToProvince.put("cdn_yunnan", "云南");
        nodeToProvince.put("cdn_gansu", "甘肃");
        nodeToProvince.put("cdn_hainan", "海南");

        List<GeneralStatistic> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE.CLICKHOUSE_URL, DATABASE.CLICKHOUSE_USERNAME, DATABASE.CLICKHOUSE_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String cdnNode = rs.getString("cdn_node");
                String issues = rs.getString("total_issues");

                String provinceName = nodeToProvince.get(cdnNode);
                if (provinceName != null) {
                    GeneralStatistic stat = new GeneralStatistic();
                    stat.setName(provinceName);
                    stat.setValue(issues);
                    result.add(stat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 4. 统计实时影视热度排行榜 (带名称翻译功能！)
     */
    public static List<GeneralStatistic> getProductStatsByTrademark(String date) {
        if (!isMovieMapLoaded) {
            loadMovieNameMap();
        }

        // 🚀 补充了按日期过滤
        String sql = "SELECT movie_id, SUM(score) AS total_score FROM ads_baize_dw.ads_realtime_hot_movie WHERE dt = '" + date + "' GROUP BY movie_id ORDER BY total_score DESC LIMIT 10";
        List<GeneralStatistic> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE.CLICKHOUSE_URL, DATABASE.CLICKHOUSE_USERNAME, DATABASE.CLICKHOUSE_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String movieId = rs.getString("movie_id");
                String score = rs.getString("total_score");

                GeneralStatistic stat = new GeneralStatistic();

                String realMovieName = movieIdToNameMap.getOrDefault(movieId, "未收录影片-" + movieId);

                if (realMovieName.length() > 10) {
                    realMovieName = realMovieName.substring(0, 10) + "...";
                }

                stat.setName(realMovieName);
                stat.setValue(score);
                result.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // =========================================================
    // 占位
    // =========================================================
    public static List<GeneralStatistic> getKeywordStatistics(String date) { return new ArrayList<>(); }
    public static List<GeneralStatistic> getProductStatisticsGroupByCategory3(String date) { return new ArrayList<>(); }
}