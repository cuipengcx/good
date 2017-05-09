package com.jk.mapper;

import com.jk.model.ScheduleJob;
import com.jk.util.MyMapper;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
public interface ScheduleJobMapper extends MyMapper<ScheduleJob>{
    /**
     * 注:通用mapper 不支持@PostConstruct
     * 查询所有任务
     * @return
     */
    List<ScheduleJob> findList();
}
