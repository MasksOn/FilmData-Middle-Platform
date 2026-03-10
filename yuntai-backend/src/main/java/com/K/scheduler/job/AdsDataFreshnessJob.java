package com.K.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.DriverManager;
import java.sql.SQLException;

public class AdsDataFreshnessJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("🩺 [健康度监控] 正在探活 ADS 层数据新鲜度...");

        try (var connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.235.130:3306/ads_baize_dw?useSSL=false&characterEncoding=utf8", "root", "123456");
             var statement = connection.prepareStatement(
                     "SELECT dt, COUNT(1) AS row_count FROM ads_movie_rank_topN GROUP BY dt ORDER BY dt DESC LIMIT 1");
             var rs = statement.executeQuery()) {

            if (rs.next()) {
                String latestDt = rs.getString("dt");
                int rowCount = rs.getInt("row_count");

                if (rowCount == 0) {
                    // 如果虽然有分区但数据量为0，视为探活失败
                    throw new JobExecutionException("⚠️ 告警：ADS层表 ads_movie_rank_topN 最新分区 [" + latestDt + "] 数据量为 0！");
                }
                System.out.println("✅ [健康度监控] 探活成功！最新分区: [" + latestDt + "], 共 " + rowCount + " 条数据。");
            } else {
                throw new JobExecutionException("⚠️ 告警：ADS层表 ads_movie_rank_topN 为空！无任何数据！");
            }
        } catch (SQLException e) {
            throw new JobExecutionException("探活任务数据库异常: " + e.getMessage(), e);
        }
    }
}