package com.K.government.service;

import com.K.government.bean.DataDictNode;
import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataDictService {

    /**
     * 获取数据字典树状结构
     */
    public List<DataDictNode> getDictionaryTree() throws SQLException {
        List<DataDictNode> tree = new ArrayList<>();
        Map<String, DataDictNode> dbMap = new HashMap<>();
        Map<String, DataDictNode> tbMap = new HashMap<>();

        try (var hiveConn = DriverManager.getConnection(
                "jdbc:mysql://192.168.235.130:3306/hive?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf8", "root", "123456");
             var govConn = DriverManager.getConnection(
                     "jdbc:mysql://192.168.235.130:3306/yuntai_government?useSSL=false&characterEncoding=utf8", "root", "123456")) {

            // 1. 获取用户自定义注释映射表： key = db.tb.col (col可能为空)
            Map<String, String> customComments = new HashMap<>();
            try (var rs = govConn.createStatement().executeQuery("SELECT * FROM hive_data_dictionary")) {
                while (rs.next()) {
                    String key = rs.getString("database_name") + "." + rs.getString("table_name") + "." + rs.getString("column_name");
                    customComments.put(key, rs.getString("custom_comment"));
                }
            }

            // 2. 联表查询 Hive 的 库、表、字段
            String sql = "SELECT DBS.NAME AS dbName, TBLS.TBL_NAME AS tbName, TBLS.TBL_ID as tbId, COLUMNS_V2.COLUMN_NAME AS colName, COLUMNS_V2.COMMENT AS colComment " +
                    "FROM DBS JOIN TBLS ON DBS.DB_ID = TBLS.DB_ID LEFT JOIN COLUMNS_V2 ON TBLS.TBL_ID = COLUMNS_V2.CD_ID ORDER BY dbName, tbName";

            try (var rs = hiveConn.createStatement().executeQuery(sql)) {
                while (rs.next()) {
                    String dbName = rs.getString("dbName");
                    String tbName = rs.getString("tbName");
                    String colName = rs.getString("colName");

                    // 构建数据库节点
                    DataDictNode dbNode = dbMap.computeIfAbsent(dbName, k -> {
                        DataDictNode node = new DataDictNode();
                        node.setId("db_" + dbName); node.setLabel(dbName); node.setType("db"); node.setDbName(dbName);
                        tree.add(node);
                        return node;
                    });

                    // 构建表节点
                    String tbKey = dbName + "." + tbName;
                    DataDictNode tbNode = tbMap.computeIfAbsent(tbKey, k -> {
                        DataDictNode node = new DataDictNode();
                        node.setId("tb_" + tbKey); node.setLabel(tbName); node.setType("table");
                        node.setDbName(dbName); node.setTbName(tbName); node.setColName("");
                        node.setCustomComment(customComments.getOrDefault(tbKey + ".", ""));
                        dbNode.getChildren().add(node);
                        return node;
                    });

                    // 构建字段节点
                    if (colName != null) {
                        DataDictNode colNode = new DataDictNode();
                        colNode.setId("col_" + tbKey + "." + colName); colNode.setLabel(colName); colNode.setType("column");
                        colNode.setDbName(dbName); colNode.setTbName(tbName); colNode.setColName(colName);
                        colNode.setOriginalComment(rs.getString("colComment"));
                        colNode.setCustomComment(customComments.getOrDefault(tbKey + "." + colName, ""));
                        tbNode.getChildren().add(colNode);
                    }
                }
            }
        }
        return tree;
    }

    /**
     * 保存或更新自定义注释
     */
    public void saveCustomComment(String dbName, String tbName, String colName, String comment) throws SQLException {
        try (var conn = DriverManager.getConnection(
                "jdbc:mysql://192.168.235.130:3306/yuntai_government?useSSL=false&characterEncoding=utf8", "root", "123456");
             var stmt = conn.prepareStatement(
                     "INSERT INTO hive_data_dictionary (database_name, table_name, column_name, custom_comment) " +
                             "VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE custom_comment = ?")) {
            stmt.setString(1, dbName);
            stmt.setString(2, tbName);
            stmt.setString(3, colName == null ? "" : colName);
            stmt.setString(4, comment);
            stmt.setString(5, comment);
            stmt.executeUpdate();
        }
    }
}