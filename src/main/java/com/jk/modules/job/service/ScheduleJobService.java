package com.jk.modules.job.service;

import com.github.pagehelper.PageInfo;
import com.jk.common.base.service.BaseService;
import com.jk.modules.job.model.ScheduleJob;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
public interface ScheduleJobService extends BaseService<ScheduleJob> {

    /**
     * 初始化定时任务
     */
    void initScheduleJob();

    /**
     * 分页查询任务调度列表
     * @param pageNum
     * @param pageSize
     * @param jobName
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<ScheduleJob> findPage(Integer pageNum, Integer pageSize, String jobName, String startTime, String endTime);
    /**
     * 保存定时任务
     */
    void saveScheduleJob(ScheduleJob scheduleJob);

    /**
     * 更新定时任务
     */
    void updateScheduleJob(ScheduleJob scheduleJob);

    /**
     * 删除定时任务
     */
    void deleteScheduleJob(Long jobIds);

    /**
     * 暂停运行调度任务
     */
    void pauseJob(Long jobId);

    /**
     * 恢复运行调度任务
     */
    void resumeJob(Long jobId);

    /**
     * 运行一次调度任务
     */
    void runOnce(Long jobId);

    /**
     * 根据任务名称和任务分组查询任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    ScheduleJob findByJobNameAndJobGroup(String jobName, String jobGroup);
}
