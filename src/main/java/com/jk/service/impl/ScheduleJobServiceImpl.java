package com.jk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.model.ScheduleJob;
import com.jk.service.ScheduleJobService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
@Transactional
@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJob> implements ScheduleJobService{
    @Override
    public ScheduleJob findScheduleJobById(Long jobId) {
        return null;
    }

    @Override
    public PageInfo<ScheduleJob> findPage(Integer pageNum, Integer pageSize, String jobName, String startTime, String endTime) {
        Example example = new Example(ScheduleJob.class);
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
        List<ScheduleJob> jobList = this.selectByExample(example);

        return new PageInfo<ScheduleJob>(jobList);
    }


    @Override
    public void saveScheduleJob(ScheduleJob scheduleJob) {

    }

    @Override
    public void updateScheduleJob(ScheduleJob scheduleJob) {

    }

    @Override
    public void deleteBatchScheduleJob(Long[] jobIds) {

    }

    @Override
    public int updateBatchScheduleJob(Long[] jobIds, int status) {
        return 0;
    }

    @Override
    public void run(Long[] jobIds) {

    }

    @Override
    public void pause(Long[] jobIds) {

    }

    @Override
    public void resume(Long[] jobIds) {

    }
}
