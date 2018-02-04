package com.jk.modules.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jk.modules.job.mapper.ScheduleJobLogMapper;
import com.jk.modules.job.model.ScheduleJobLog;
import com.jk.modules.job.service.ScheduleJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Created by cuip on 2017/5/20.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService{

    @Transactional(readOnly = true)
    @Override
    public Page<ScheduleJobLog> findPage(Integer pageNum, Integer pageSize, Long jobId, String jobName, String startTime, String endTime) {
        return this.selectPage(
                new Page<>(pageNum, pageSize),
                new EntityWrapper<ScheduleJobLog>()
                        .like(StringUtils.isNotBlank(jobName), "job_name", jobName)
                        .ge(StringUtils.isNotBlank(startTime), "create_time", startTime + "00:00:00")
                        .le(StringUtils.isNotBlank(endTime), "create_time", endTime + "23:59:59")
                        .orderBy("create_time", false)
        );
    }
}
