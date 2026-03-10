package com.K.scheduler.controller;

import com.K.scheduler.bean.QuartzScheduler;
import com.K.scheduler.bean.SchedulerJobInfo;
import com.K.scheduler.bean.SchedulerJobLog;
import com.K.scheduler.job.*;
import com.K.scheduler.service.SchedulerService;
import com.K.util.Result;
import org.quartz.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RestController
@RequestMapping("/schedule")
public class SchedulerController {
    /**
     * 获取所有作业
     */
    @GetMapping("getAllJobs")
    public Result<List<SchedulerJobInfo>> getAllJobs() {
        return Result.of(200, "success", SchedulerService.getAllJobs());
    }

    /**
     * 暂停作业
     */
    @PostMapping("pauseJob")
    public Result<String> pauseJob(@RequestBody SchedulerJobInfo jobInfo) throws Exception {
        var scheduler = QuartzScheduler.getInstance();
        scheduler.pauseJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
        jobInfo.setJobStatus("已暂停");
        SchedulerService.pauseJob(jobInfo);
        return Result.of(200, "success", "任务暂停");
    }

    /**
     * 重启作业
     */
    @PostMapping("resumeJob")
    public Result<String> resumeJob(@RequestBody SchedulerJobInfo jobInfo) throws Exception {
        var scheduler = QuartzScheduler.getInstance();
        scheduler.resumeJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
        jobInfo.setJobStatus("运行中");
        SchedulerService.resumeJob(jobInfo);
        return Result.of(200, "success", "任务重启");
    }

    /**
     * 删除作业
     */
    @PostMapping("deleteJob")
    public Result<String> deleteJob(@RequestBody SchedulerJobInfo jobInfo) throws Exception {
        var scheduler = QuartzScheduler.getInstance();
        scheduler.deleteJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
        SchedulerService.deleteJob(jobInfo);
        return Result.of(200, "success", "任务删除");
    }

    /**
     * 创建一个新作业
     */
    private static int triggerCount = 0;

    @PostMapping("createJob/{databaseName}/{tableName}/{fieldName}")
    public Result<String> createJob(@RequestBody SchedulerJobInfo jobInfo, @PathVariable String databaseName, @PathVariable String tableName, @PathVariable String fieldName) throws Exception {
        var scheduler = QuartzScheduler.getInstance();

        // 判断是什么任务，然后新建对应的任务
        JobDetail jobDetail;
        if (jobInfo.getJobType().equals("简单任务(测试)")) {
            jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
        } else {
            var jobData = new JobDataMap();
            jobData.put("databaseName", databaseName);
            jobData.put("tableName", tableName);
            jobData.put("fieldName", fieldName);
            jobDetail = JobBuilder
                    .newJob(MySQLMonitorJob.class)
                    .withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup())
                    .setJobData(jobData)
                    .build();
        }

        // 新建触发器
        Trigger trigger;
        if (jobInfo.getCronJob()) {
            trigger = newTrigger()
                    .withIdentity("trigger" + triggerCount, jobInfo.getJobGroup())
                    .withSchedule(cronSchedule(jobInfo.getCronExpression()))
                    .build();
        } else {
            trigger = newTrigger()
                    .withIdentity("trigger" + triggerCount, jobInfo.getJobGroup())
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(jobInfo.getRepeatTime())
                            .repeatForever())
                    .build();
        }

        triggerCount++;

        // 调度任务
        scheduler.scheduleJob(jobDetail, trigger);

        // 将任务存入MySQL
        jobInfo.setJobStatus("运行中");
        SchedulerService.addJob(jobInfo);

        return Result.of(200, "success", "创建任务成功");
    }
    /**
     * 🌟 新增：获取任务执行日志
     */
    @GetMapping("getJobLogs")
    public Result<List<SchedulerJobLog>> getJobLogs(@RequestParam String jobName, @RequestParam String jobGroup) {
        return Result.of(200, "success", SchedulerService.getJobLogs(jobName, jobGroup));
    }
//
//    /**
//     * 创建通用新作业（不需要传入特定的数据库和表名参数）
//     */
//    @PostMapping("createGeneralJob")
//    public Result<String> createGeneralJob(@RequestBody SchedulerJobInfo jobInfo) throws Exception {
//        var scheduler = QuartzScheduler.getInstance();
//
//        // 根据 jobType 动态判断实例化哪个 Job
//        JobDetail jobDetail;
//        if (jobInfo.getJobType().equals("简单任务(测试)")) {
//            jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
//        } else if (jobInfo.getJobType().equals("Hive元数据质量监控")) {
//            jobDetail = JobBuilder.newJob(HiveMetadataMonitorJob.class).withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
//        } else if (jobInfo.getJobType().equals("清理过期数据")) {
//            jobDetail = JobBuilder.newJob(StaleDataCleanupJob.class).withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
//        } else {
//            return Result.of(400, "error", "未知的任务类型");
//        }
//
//        // 新建触发器
//        Trigger trigger;
//        if (jobInfo.getCronJob()) {
//            trigger = newTrigger()
//                    .withIdentity("trigger" + triggerCount, jobInfo.getJobGroup())
//                    .withSchedule(cronSchedule(jobInfo.getCronExpression()))
//                    .build();
//        } else {
//            trigger = newTrigger()
//                    .withIdentity("trigger" + triggerCount, jobInfo.getJobGroup())
//                    .startNow()
//                    .withSchedule(simpleSchedule()
//                            .withIntervalInSeconds(jobInfo.getRepeatTime())
//                            .repeatForever())
//                    .build();
//        }
//
//        triggerCount++;
//
//        // 调度任务
//        scheduler.scheduleJob(jobDetail, trigger);
//
//        // 将任务存入MySQL
//        jobInfo.setJobStatus("运行中");
//        SchedulerService.addJob(jobInfo);
//
//        return Result.of(200, "success", "创建通用任务成功");
//    }
    /**
     * 创建通用新作业（不需要传入特定的数据库和表名参数）
     */
    @PostMapping("createGeneralJob")
    public Result<String> createGeneralJob(@RequestBody SchedulerJobInfo jobInfo) throws Exception {
        var scheduler = QuartzScheduler.getInstance();

        // 💡 使用 switch 结构精确路由前端传来的 jobType
        JobDetail jobDetail;
        switch (jobInfo.getJobType()) {
            case "简单任务(测试)":
                jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
                break;
            case "Hive元数据质量监控":
                jobDetail = JobBuilder.newJob(HiveMetadataMonitorJob.class).withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
                break;
            case "ADS大屏数据探活":
                jobDetail = JobBuilder.newJob(AdsDataFreshnessJob.class).withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
                break;
            case "ADS历史数据清理":
                jobDetail = JobBuilder.newJob(StaleDataCleanupJob.class).withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
                break;

            default:
                return Result.of(400, "error", "未知的任务类型: " + jobInfo.getJobType());
        }

        // 新建触发器
        Trigger trigger;
        if (jobInfo.getCronJob()) {
            trigger = newTrigger()
                    .withIdentity("trigger" + triggerCount, jobInfo.getJobGroup())
                    .withSchedule(cronSchedule(jobInfo.getCronExpression()))
                    .build();
        } else {
            trigger = newTrigger()
                    .withIdentity("trigger" + triggerCount, jobInfo.getJobGroup())
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(jobInfo.getRepeatTime())
                            .repeatForever())
                    .build();
        }

        triggerCount++;

        // 调度任务
        scheduler.scheduleJob(jobDetail, trigger);

        // 将任务存入MySQL
        jobInfo.setJobStatus("运行中");
        SchedulerService.addJob(jobInfo);

        return Result.of(200, "success", "创建任务成功");
    }
}
