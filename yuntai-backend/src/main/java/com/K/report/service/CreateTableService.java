package com.K.report.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTableService {

    // ⚠️ 请根据你的实际配置修改这里的 IP、库名和密码
    private static final String MYSQL_URL = "jdbc:mysql://192.168.235.130:3306/ads_baize_dw?useSSL=false&characterEncoding=utf-8";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASS = "123456";

    private static final String CH_URL = "jdbc:clickhouse://192.168.235.132:8123/ads_baize_dw?compress=0";
    private static final String CH_USER = "default";
    private static final String CH_PASS = "123456";

    // 原有的建表方法保留...
    public static String createMySQLTable(String sql) { /*...*/ return "success"; }
    public static String createClickHouseTable(String sql) { /*...*/ return "success"; }

    // ========================================================
    // 🚀 新增：动态执行 SELECT 查询并返回列名和数据
    // ========================================================
    public static Map<String, Object> queryClickHouse(String sql) {
        return executeDynamicQuery(CH_URL, CH_USER, CH_PASS, sql);
    }

    public static Map<String, Object> queryMySQL(String sql) {
        return executeDynamicQuery(MYSQL_URL, MYSQL_USER, MYSQL_PASS, sql);
    }

    /**
     * 核心封装：JDBC 动态获取元数据和数据行
     */
    private static Map<String, Object> executeDynamicQuery(String url, String user, String pass, String sql) {
        Map<String, Object> result = new HashMap<>();
        List<String> columns = new ArrayList<>();
        List<Map<String, Object>> data = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // 1. 获取动态列名
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(metaData.getColumnName(i));
            }

            // 2. 获取动态数据
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (String col : columns) {
                    row.put(col, rs.getObject(col));
                }
                data.add(row);
            }

            result.put("status", "success");
            result.put("columns", columns);
            result.put("data", data);

        } catch (SQLException e) {
            e.printStackTrace();
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        return result;
    }
    // 指向 Python 微服务的地址
    private static final String HIVE_ENGINE_URL = "http://localhost:8000/internal/hive/execute";
}