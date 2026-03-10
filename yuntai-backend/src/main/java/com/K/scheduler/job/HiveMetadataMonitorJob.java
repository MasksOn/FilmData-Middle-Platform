package com.K.scheduler.job;

import com.K.government.service.HiveMetadataMonitor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HiveMetadataMonitorJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("🚀 [Hive元数据监控] 开始执行定时任务...");
        try {
            // 直接调用你写好的监控数据计算和落盘逻辑
            HiveMetadataMonitor.calculateHiveMetadataDetail();
            System.out.println("✅ [Hive元数据监控] 任务执行完成。");
        } catch (Exception e) {
            // 抛出异常，触发 GlobalJobListener 记录失败状态
            throw new JobExecutionException("Hive元数据监控任务执行失败: " + e.getMessage(), e);
        }
    }
}