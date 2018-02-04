package com.jk.modules.job.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jk.modules.job.model.ScheduleJobLog;

/**
 *
 * Created by cuip on 2017/5/20.
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLog> {
    /**
     * 根据ID分页查询调度任务历史
     * @param pageNum
     * @param pageSize
     * @param jobName
     * @param startTime
     * @param endTime
     * @return
     */
    Page<ScheduleJobLog> findPage(Integer pageNum, Integer pageSize, Long jobId, String jobName, String startTime, String endTime);
}
