package com.K.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import java.sql.DriverManager;
import java.sql.SQLException;

public class GlobalJobListener implements JobListener {

    @Override
    public String getName() {
        return "GlobalJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        context.put("startTime", System.currentTimeMillis());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {}

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String jobName = context.getJobDetail().getKey().getName();
        String jobGroup = context.getJobDetail().getKey().getGroup();

        // 计算耗时
        long startTime = (long) context.get("startTime");
        long duration = System.currentTimeMillis() - startTime;

        // 核心：如果有 JobExecutionException 被抛出，说明任务失败
        String status = (jobException == null) ? "成功" : "失败";
        String message = (jobException == null) ? "执行成功" : jobException.getMessage();

        try (var connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.235.130:3306/yuntai_schedule?useSSL=false&characterEncoding=utf8", "root", "123456");
             var insertStatement = connection.prepareStatement(
                     "INSERT INTO scheduler_job_log (jobName, jobGroup, executeTime, status, duration, message) VALUES (?, ?, NOW(), ?, ?, ?)")) {

            insertStatement.setString(1, jobName);
            insertStatement.setString(2, jobGroup);
            insertStatement.setString(3, status);
            insertStatement.setLong(4, duration);

            // 截断过长的错误信息，防止撑爆 MySQL VARCHAR 字段 (如果你的 message 字段是 TEXT 则无所谓，但保险起见截取前1000个字符)
            if (message != null && message.length() > 1000) {
                message = message.substring(0, 1000) + "...";
            }
            insertStatement.setString(5, message);

            insertStatement.execute();
        } catch (SQLException e) {
            System.err.println("记录调度日志失败: " + e.getMessage());
        }
    }
}