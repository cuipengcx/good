package com.jk.service;

import com.github.pagehelper.PageInfo;
import com.jk.model.ScheduleJob;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
public interface ScheduleJobService extends BaseService<ScheduleJob>{

    /**
     * 初始化定时任务
     */
    void initScheduleJob();

//    /**
//     * 根据ID，查询定时任务
//     */
//    ScheduleJob findScheduleJobById(Long jobId);
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
     * 删除
     * @param jobId
     */
    void deleteScheduleJob(Long jobId);

    /**
     * 批量删除定时任务
     */
    void deleteBatchScheduleJob(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatchScheduleJob(Long[] jobIds, int status);

    /**
     * 立即执行
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     */
    void resume(Long[] jobIds);
}
