package com.jk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.model.ScheduleJobLog;
import com.jk.service.ScheduleJobLogService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 *
 * Created by cuip on 2017/5/20.
 */
@Transactional
@Service
public class ScheduleJobLogServiceImpl extends BaseServiceImpl<ScheduleJobLog> implements ScheduleJobLogService{

    @Transactional(readOnly = true)
    @Override
    public PageInfo<ScheduleJobLog> findPage(Integer pageNum, Integer pageSize, Long jobId, String jobName, String startTime, String endTime) {
        Example example = new Example(ScheduleJobLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(jobName)){
            criteria.andLike("jobName", "%"+jobName+"%");
        }if(startTime != null && endTime != null){
            criteria.andBetween("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)), DateUtil.endOfDay(DateUtil.parse(endTime)));
        }
        //倒序
        example.orderBy("createTime").desc();

        //分页
        PageHelper.startPage(pageNum,pageSize);
        List<ScheduleJobLog> jobLogList = this.selectByExample(example);

        return new PageInfo<ScheduleJobLog>(jobLogList);
    }
}
