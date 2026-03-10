package com.K.scheduler.bean;

import com.K.scheduler.job.GlobalJobListener;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

public class QuartzScheduler {
    private static Scheduler scheduler = null;

    public static Scheduler getInstance() throws Exception {
        if (scheduler == null) {
            var factory = new StdSchedulerFactory();
            scheduler = factory.getScheduler();

            // 🌟 核心修复：必须把监听器注册到调度器里！否则 GlobalJobListener 永远不会生效！
            scheduler.getListenerManager().addJobListener(
                    new GlobalJobListener(),
                    EverythingMatcher.allJobs()
            );
        }

        return scheduler;
    }
}