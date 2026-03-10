package com.K.government.service;

import com.K.government.bean.GovernmentDetail;
import com.K.scheduler.bean.MonitorDetail;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class GovernmentService {

    /**
     * 计算并获取血缘关系拓扑图 (Mermaid格式)
     */
    public String getLineage() {
        // 更新血缘图
        var edges = TableLineageService.generateEdges();
        TableLineageService.sinkToNeo4j(edges);

        // 从neo4j中查询所有边以及顶点，并绘成`Mermaid`字符串
        var driver = GraphDatabase.driver("bolt://192.168.235.132:7687", AuthTokens.basic("neo4j", "123456"));
        var session = driver.session();
        var graphString = session.executeRead(tx -> {
            var graph = new StringBuilder("graph LR\n");
            var result = tx.run("MATCH (n)-[r]->(m) RETURN n,r,m");
            for (var record : result.list()) {
                graph.append("    ")
                     .append(record.get("n").get("tableName"))
                     .append(" --> ")
                     .append(record.get("m").get("tableName"))
                     .append("\n");
            }
            return graph.toString().replaceAll("\"", "");
        });
        session.close();
        driver.close();

        return graphString;
    }

    /**
     * 查询 Hive 元数据质量评分数据
     */
    public List<GovernmentDetail> getHiveMetadataScore() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        var result = new ArrayList<GovernmentDetail>();

        // 🌟 优化：使用 try-with-resources 自动释放 connection 和 statement
        try (var connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.235.130:3306/yuntai_government?useSSL=false&characterEncoding=utf8", "root", "123456");
             var selectStatement = connection.prepareStatement("SELECT * FROM hive_metadata_monitor");
             var resultSet = selectStatement.executeQuery()) {

            while (resultSet.next()) {
                var detail = new GovernmentDetail();
                detail.setDatabaseName(resultSet.getString("database_name"));
                detail.setTableName(resultSet.getString("table_name"));
                detail.setFieldsNumber(resultSet.getInt("fields_number"));
                detail.setHasBusinessOwner(resultSet.getBoolean("has_business_owner"));
                detail.setHasOutputLastSevenDay(resultSet.getBoolean("has_output_last_seven_days"));
                detail.setMissingCommentFieldsNumber(resultSet.getInt("missing_comment_fields_number"));
                detail.setHasTechnicalOwner(resultSet.getBoolean("has_technical_owner"));
                detail.setHasTableComment(resultSet.getBoolean("has_table_comment"));
                result.add(detail);
            }
        }
        return result;
    }

    /**
     * 查询 MySQL 数据质量监控最新数据
     */
    public List<MonitorDetail> getMySQLDataMonitor() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        var result = new ArrayList<MonitorDetail>();

        String sql = "SELECT m1.* FROM mysql_data_monitor m1 " +
                     "WHERE m1.dt = (" +
                     "    SELECT MAX(dt) FROM mysql_data_monitor m2 " +
                     "    WHERE m1.database_name = m2.database_name " +
                     "    AND m1.table_name = m2.table_name " +
                     "    AND m1.field_name = m2.field_name" +
                     ")";

        try (var connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.235.130:3306/yuntai_government?useSSL=false&characterEncoding=utf8", "root", "123456");
             var selectStatement = connection.prepareStatement(sql);
             var resultSet = selectStatement.executeQuery()) {

            while (resultSet.next()) {
                var detail = new MonitorDetail();
                detail.setDatabaseName(resultSet.getString("database_name"));
                detail.setTableName(resultSet.getString("table_name"));
                detail.setFieldName(resultSet.getString("field_name"));
                detail.setFieldNullRate(resultSet.getDouble("field_null_rate"));
                detail.setDt(resultSet.getString("dt"));
                result.add(detail);
            }
        }
        return result;
    }
}