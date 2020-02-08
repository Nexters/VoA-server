package com.voa.goodbam.support;

import com.voa.goodbam.SpringContext;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ScheduleTaskService {

    final public static ScheduleTaskService instance = new ScheduleTaskService();
    private TaskScheduler scheduler;

    public ScheduleTaskService() {

        ApplicationContext context = SpringContext.getAppContext();
        ThreadPoolTaskScheduler threadPoolTaskScheduler = context.getBean(ThreadPoolTaskScheduler.class);
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.setThreadNamePrefix("threadPoolScheduler");
        this.scheduler = threadPoolTaskScheduler;

    }

    public void schedule(Runnable task, LocalDateTime localDateTime) {
        scheduler.schedule(task, Date.from( localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

}
