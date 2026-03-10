package com.K.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.DriverManager;
import java.sql.SQLException;

public class StaleDataCleanupJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("🧹 [存储维护] 开始清理 MySQL ADS 层过期数据...");

        try (var connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.235.130:3306/ads_baize_dw?useSSL=false&characterEncoding=utf8", "root", "123456");
             var statement = connection.prepareStatement(
                     // 使用 dt 字段，清理 30 天前的高分榜历史数据
                     "DELETE FROM ads_movie_rank_topN WHERE STR_TO_DATE(dt, '%Y-%m-%d') < DATE_SUB(CURDATE(), INTERVAL 30 DAY)"
             )) {

            int deletedRows = statement.executeUpdate();
            System.out.println("✅ [存储维护] 清理完成，共释放 " + deletedRows + " 条过期数据。");

        } catch (SQLException e) {
            throw new JobExecutionException("清理任务执行失败: " + e.getMessage(), e);
        }
    }
}