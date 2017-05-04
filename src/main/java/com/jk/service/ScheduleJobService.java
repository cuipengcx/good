package com.jk.service;

import com.jk.model.ScheduleJob;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
public interface ScheduleJobService extends BaseService<ScheduleJob>{

    /**
     * 根据ID，查询定时任务
     */
    ScheduleJob findScheduleJobById(Long jobId);

    /**
     * 查询定时任务列表
     */
    List<ScheduleJob> findPage();

    /**
     * 保存定时任务
     */
    void saveScheduleJob(ScheduleJob scheduleJob);

    /**
     * 更新定时任务
     */
    void updateScheduleJob(ScheduleJob scheduleJob);

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
