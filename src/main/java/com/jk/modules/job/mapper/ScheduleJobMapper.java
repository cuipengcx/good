package com.jk.modules.job.mapper;

import com.jk.modules.job.model.ScheduleJob;
import com.jk.common.base.mapper.BaseMapper;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {
    /**
     * 注:通用mapper 不支持@PostConstruct
     * 查询所有任务
     * @return
     */
    List<ScheduleJob> findList();
}
