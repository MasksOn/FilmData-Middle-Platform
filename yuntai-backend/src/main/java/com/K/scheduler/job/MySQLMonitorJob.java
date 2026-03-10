package com.K.scheduler.job;

import com.K.scheduler.bean.MonitorDetail;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLMonitorJob implements Job {
    public MySQLMonitorJob() {}

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        var dataMap = context.getJobDetail().getJobDataMap();
        var databaseName = dataMap.getString("databaseName");
        var tableName = dataMap.getString("tableName");
        var fieldName = dataMap.getString("fieldName"); // 这里现在可能会收到 "ALL"

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new JobExecutionException("找不到 MySQL 驱动: " + e.getMessage(), e);
        }

        // 用来存放最终需要检测的字段集合
        var targetFields = new ArrayList<String>();

        try (var connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.235.130:3306/" + databaseName + "?useSSL=false&characterEncoding=utf8", "root", "123456")) {

            // 1. 判断是否是全表字段扫描模式
            if ("ALL".equalsIgnoreCase(fieldName) || fieldName == null || fieldName.trim().isEmpty()) {
                System.out.println("🔍 检测到全表字段扫描模式，正在获取 [" + tableName + "] 的表结构...");
                // 去 information_schema 获取该表的所有字段
                try (var metaConn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.235.130:3306/information_schema?useSSL=false&characterEncoding=utf8", "root", "123456");
                     var metaStmt = metaConn.prepareStatement("SELECT COLUMN_NAME FROM COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?")) {
                    metaStmt.setString(1, databaseName);
                    metaStmt.setString(2, tableName);
                    try (var rs = metaStmt.executeQuery()) {
                        while (rs.next()) {
                            targetFields.add(rs.getString("COLUMN_NAME"));
                        }
                    }
                }
            } else {
                // 如果只指定了单字段，就只测这一个
                targetFields.add(fieldName);
            }

            if (targetFields.isEmpty()) {
                throw new JobExecutionException("⚠️ 表 " + tableName + " 中没有找到任何字段！");
            }

            // 2. 动态构建高效的单条 SQL 聚合查询
            // 如果有 id 和 name 字段，拼接出来的 SQL 类似：
            // SELECT count(1) as TotalAll, count(`id`) as `id_notnull`, count(`name`) as `name_notnull` FROM `tableName`
            StringBuilder querySql = new StringBuilder("SELECT count(1) as TotalAll");
            for (String field : targetFields) {
                querySql.append(", count(`").append(field).append("`) as `").append(field).append("_notnull`");
            }
            querySql.append(" FROM `").append(tableName).append("`");

            var monitorDetails = new ArrayList<MonitorDetail>();

            // 3. 执行查询并解析每个字段的结果
            try (var selectStmt = connection.prepareStatement(querySql.toString());
                 var rs = selectStmt.executeQuery()) {

                if (rs.next()) {
                    long totalAll = rs.getLong("TotalAll");

                    for (String field : targetFields) {
                        long notNullCount = rs.getLong(field + "_notnull");

                        // 🌟 修正计算逻辑：空值数 = 总行数 - 非空行数
                        long nullCount = totalAll - notNullCount;

                        // 计算空值率 (Null Rate)
                        double nullRate = (totalAll == 0) ? 0.0 : (100.0 * nullCount / totalAll);

                        var detail = new MonitorDetail();
                        detail.setDatabaseName(databaseName);
                        detail.setTableName(tableName);
                        detail.setFieldName(field);
                        detail.setFieldNullRate(nullRate); // 正确存入空值率
                        monitorDetails.add(detail);
                    }
                }
            }

            // 4. 将最新数据写入 yuntai_government (带时间分区机制)
            try (var insertConnection = DriverManager.getConnection(
                    "jdbc:mysql://192.168.235.130:3306/yuntai_government?useSSL=false&characterEncoding=utf8", "root", "123456")) {

                // 🌟 新增：先删除【今天】同一张表、同一个字段的旧数据（防止一天内多次执行产生重复数据）
                try (var deleteStmt = insertConnection.prepareStatement(
                        "DELETE FROM mysql_data_monitor WHERE database_name = ? AND table_name = ? AND field_name = ? AND dt = CURDATE()")) {
                    for (var detail : monitorDetails) {
                        deleteStmt.setString(1, detail.getDatabaseName());
                        deleteStmt.setString(2, detail.getTableName());
                        deleteStmt.setString(3, detail.getFieldName());
                        deleteStmt.addBatch();
                    }
                    deleteStmt.executeBatch();
                }

                // 🌟 修改：插入数据时，顺便把当天的日期 CURDATE() 存进 dt 字段
                try (var insertStmt = insertConnection.prepareStatement(
                        "INSERT INTO mysql_data_monitor (database_name, table_name, field_name, field_null_rate, dt) VALUES (?, ?, ?, ?, CURDATE())")) {
                    for (var detail : monitorDetails) {
                        insertStmt.setString(1, detail.getDatabaseName());
                        insertStmt.setString(2, detail.getTableName());
                        insertStmt.setString(3, detail.getFieldName());
                        insertStmt.setDouble(4, detail.getFieldNullRate());
                        insertStmt.addBatch();
                    }
                    insertStmt.executeBatch();
                }
                System.out.println("✅ [" + tableName + "] 表的 " + targetFields.size() + " 个字段监控数据已更新！");
            }

        } catch (SQLException e) {
            // 抛出异常供 GlobalJobListener 捕获
            throw new JobExecutionException("MySQL监控任务执行异常: " + e.getMessage(), e);
        }
    }
}