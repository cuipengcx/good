package com.jk.modules.job.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.common.base.service.impl.BaseServiceImpl;
import com.jk.modules.job.model.ScheduleJobLog;
import com.jk.modules.job.service.ScheduleJobLogService;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
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

        criteria.andEqualTo("jobId", jobId);
        if(StringUtils.isNotEmpty(jobName)){
            criteria.andLike("jobName", "%"+jobName+"%");
        }if(StrUtil.isNotEmpty(startTime)){
            criteria.andGreaterThanOrEqualTo("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)));
        }if(StrUtil.isNotEmpty(endTime)){
            criteria.andLessThanOrEqualTo("createTime", DateUtil.endOfDay(DateUtil.parse(endTime)));
        }

        //倒序
        example.orderBy("createTime").desc();

        //分页
        PageHelper.startPage(pageNum,pageSize);
        List<ScheduleJobLog> jobLogList = this.selectByExample(example);

        return new PageInfo<ScheduleJobLog>(jobLogList);
    }
}
