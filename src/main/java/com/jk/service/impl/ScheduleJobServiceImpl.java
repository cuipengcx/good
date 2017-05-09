package com.jk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.ScheduleJobMapper;
import com.jk.model.ScheduleJob;
import com.jk.service.ScheduleJobService;
import com.jk.util.ScheduleUtils;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
@Transactional
@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJob> implements ScheduleJobService{

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Override
    public void initScheduleJob() {
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.findList();

        if (CollectionUtils.isEmpty(scheduleJobList)) {
            return;
        }

        for (ScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(schedulerFactoryBean.getScheduler(), scheduleJob.getJobName(), scheduleJob.getJobGroup());

            //不存在，创建一个
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(schedulerFactoryBean.getScheduler(), scheduleJob);
            } else {
                //已存在，那么更新相应的定时设置
                ScheduleUtils.updateScheduleJob(schedulerFactoryBean.getScheduler(), scheduleJob);
            }
        }
    }

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
