package com.ebooks.productservice.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduledTaskServiceImpl {

    private final Scheduler scheduler;

    public ScheduledTaskServiceImpl(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        log.info("Scheduled tasks execute at: {}", System.currentTimeMillis());
    }

    @Scheduled(cron = "0 15 2 * * ?")
    public void performNightlyAudit(){
        log.info("Starting nightly audit process...");
    }

    public void pauseJob(String jobName, String groupName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName, groupName));
    }

    public void resumeJob(String jobName, String groupName) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobName, groupName));
    }

    public void deleteJob(String jobName, String groupName) throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey(jobName, groupName));
    }

}
