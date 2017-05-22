package com.jk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.common.Constant.JobStatus;
import com.jk.mapper.ScheduleJobMapper;
import com.jk.model.ScheduleJob;
import com.jk.model.User;
import com.jk.service.ScheduleJobService;
import com.jk.util.task.ScheduleUtils;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.BeanUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
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
        scheduleJob.setStatus(JobStatus.NORMAL.getValue());
        //创建调度任务
        ScheduleUtils.createScheduleJob(schedulerFactoryBean.getScheduler(), scheduleJob);

        //保存到数据库
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        scheduleJob.setId(null);
        scheduleJob.setStatus(1);
        scheduleJob.setCreateBy(user.getId());
        if(scheduleJob.getIsLocal()){
            scheduleJob.setRemoteUrl(null);
            scheduleJob.setRemoteRequestMethod(null);
        }else {
            scheduleJob.setBeanClass(null);
            scheduleJob.setMethodName(null);
            scheduleJob.setRemoteRequestMethod("POST"); //默认只支持post
        }
        super.save(scheduleJob);
    }

    @Override
    public void updateScheduleJob(ScheduleJob scheduleJob) {

        //根据ID获取修改前的任务记录
        ScheduleJob record = super.findById(scheduleJob.getId());

        //复制一份数据库中的原始数据
        ScheduleJob recordCopy = new ScheduleJob();
        BeanUtil.copyProperties(record, recordCopy);

        //参数赋值
        record.setJobName(scheduleJob.getJobName());
        record.setJobGroup(scheduleJob.getJobGroup());
        record.setCron(scheduleJob.getCron());
        record.setParams(scheduleJob.getParams());
        record.setIsAsync(scheduleJob.getIsAsync());
        record.setRemarks(scheduleJob.getRemarks());
        if(scheduleJob.getIsLocal()){
            record.setIsLocal(true);
            record.setBeanClass(scheduleJob.getBeanClass());
            record.setMethodName(scheduleJob.getMethodName());
            record.setRemoteUrl(null);
            record.setRemoteRequestMethod(null);
        }else {
            record.setIsLocal(false);
            record.setRemoteUrl(scheduleJob.getRemoteUrl());
            record.setRemoteRequestMethod("POST"); //默认只支持post
            record.setBeanClass(null);
            record.setMethodName(null);
        }

        //因为Quartz只能更新cron表达式，当更改了cron表达式以外的属性时，执行的逻辑是：先删除旧的再创建新的。注:equals排除了cron属性
        if(!recordCopy.equals(record)){
            //删除旧的任务
            ScheduleUtils.deleteScheduleJob(schedulerFactoryBean.getScheduler(), record.getJobName(), record.getJobGroup());
            //创建新的任务
            scheduleJob.setStatus(record.getStatus());
            ScheduleUtils.createScheduleJob(schedulerFactoryBean.getScheduler(), scheduleJob);
        }else {
            //当cron表达式和原来不一致才做更新
            if(!recordCopy.getCron().equals(record.getCron())){
                //更新调度任务
                ScheduleUtils.updateScheduleJob(schedulerFactoryBean.getScheduler(), scheduleJob);
            }
        }

        //更新数据库
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        record.setModifyBy(user.getId());
        super.update(record);
    }

    @Override
    public void deleteScheduleJob(Long jobId) {
        ScheduleJob scheduleJob = super.findById(jobId);
        //删除运行的任务
        ScheduleUtils.deleteScheduleJob(schedulerFactoryBean.getScheduler(), scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //删除数据
        super.deleteById(jobId);
    }

    @Override
    public void pauseJob(Long jobId) {
        ScheduleJob scheduleJob = super.findById(jobId);
        //暂停正在运行的调度任务
        ScheduleUtils.pauseJob(schedulerFactoryBean.getScheduler(), scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //更新数据库状态为 禁用 0
        ScheduleJob model = new ScheduleJob();
        model.setId(jobId);
        model.setStatus(0);
        super.updateSelective(model);
    }

    @Override
    public void resumeJob(Long jobId) {
        ScheduleJob scheduleJob = super.findById(jobId);
        //恢复处于暂停中的调度任务
        ScheduleUtils.resumeJob(schedulerFactoryBean.getScheduler(), scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //更新数据库状态 启用 1
        ScheduleJob model = new ScheduleJob();
        model.setId(jobId);
        model.setStatus(1);
        super.updateSelective(model);
    }

    @Override
    public void runOnce(Long jobId) {
        ScheduleJob scheduleJob = super.findById(jobId);
        //运行一次
        ScheduleUtils.runOnce(schedulerFactoryBean.getScheduler(), scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    @Transactional(readOnly = true)
    @Override
    public ScheduleJob findByJobNameAndJobGroup(String jobName, String jobGroup) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setJobName(jobName);
        scheduleJob.setJobGroup(jobGroup);
        return super.findOne(scheduleJob);
    }
}
