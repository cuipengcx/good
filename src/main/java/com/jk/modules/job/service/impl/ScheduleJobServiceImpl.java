package com.jk.modules.job.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.common.Constant.JobStatus;
import com.jk.common.base.service.impl.BaseServiceImpl;
import com.jk.common.util.ShiroUtils;
import com.jk.common.util.job.ScheduleUtils;
import com.jk.modules.job.mapper.ScheduleJobMapper;
import com.jk.modules.job.model.ScheduleJob;
import com.jk.modules.job.service.ScheduleJobService;
import com.jk.modules.sys.model.User;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
        }if(StrUtil.isNotEmpty(startTime)){
            criteria.andGreaterThanOrEqualTo("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)));
        }if(StrUtil.isNotEmpty(endTime)){
            criteria.andLessThanOrEqualTo("createTime", DateUtil.endOfDay(DateUtil.parse(endTime)));
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
        User user = ShiroUtils.getUserEntity();

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

        //参数赋值
        scheduleJob.setRemoteRequestMethod(record.getRemoteRequestMethod());
        scheduleJob.setStatus(record.getStatus());
        scheduleJob.setCreateBy(record.getCreateBy());
        scheduleJob.setModifyBy(record.getModifyBy());
        scheduleJob.setCreateTime(record.getCreateTime());
        scheduleJob.setModifyTime(record.getModifyTime());
        if(scheduleJob.getIsLocal()){
            scheduleJob.setIsLocal(true);
            scheduleJob.setBeanClass(scheduleJob.getBeanClass());
            scheduleJob.setMethodName(scheduleJob.getMethodName());
            scheduleJob.setRemoteUrl(null);
            scheduleJob.setRemoteRequestMethod(null);
        }else {
            scheduleJob.setIsLocal(false);
            scheduleJob.setRemoteUrl(scheduleJob.getRemoteUrl());
            scheduleJob.setRemoteRequestMethod("POST"); //默认只支持post
            scheduleJob.setBeanClass(null);
            scheduleJob.setMethodName(null);
        }

        //因为Quartz只能更新cron表达式，当更改了cron表达式以外的属性时，执行的逻辑是：先删除旧的再创建新的。注:equals排除了cron属性
        if(!scheduleJob.equals(record)){
            //删除旧的任务
            ScheduleUtils.deleteScheduleJob(schedulerFactoryBean.getScheduler(), record.getJobName(), record.getJobGroup());
            //创建新的任务,保持原来任务的状态
            scheduleJob.setStatus(record.getStatus());
            ScheduleUtils.createScheduleJob(schedulerFactoryBean.getScheduler(), scheduleJob);
        }else {
            //当cron表达式和原来不一致才做更新
            if(!scheduleJob.getCron().equals(record.getCron())){
                //更新调度任务
                ScheduleUtils.updateScheduleJob(schedulerFactoryBean.getScheduler(), scheduleJob);
            }
        }

        //更新数据库
        User user = ShiroUtils.getUserEntity();
        scheduleJob.setModifyBy(user.getId());
        super.update(scheduleJob);
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
