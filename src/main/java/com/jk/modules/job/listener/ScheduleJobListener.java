package com.jk.modules.job.listener;

import com.jk.modules.job.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 项目启动时初始化定时任务
 * @author cuiP
 * Created by JK on 2017/5/9.
 */
@Slf4j
@Component
public class ScheduleJobListener {

    @Resource
    private ScheduleJobService scheduleJobService;

    /**
     * 项目启动时初始化
     */
    @PostConstruct
    public void init() {

        if (log.isInfoEnabled()) {
            log.info("初始化定时任务...,开始");
        }

        scheduleJobService.initScheduleJob();

        if (log.isInfoEnabled()) {
            log.info("初始化定时任务...,完成");
        }
    }
}
