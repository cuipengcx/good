package com.good.modules.job.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.good.modules.job.model.ScheduleJob;

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
