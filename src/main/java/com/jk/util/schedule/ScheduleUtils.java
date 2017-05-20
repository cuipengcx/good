package com.jk.util.schedule;

import com.jk.common.Constant.JobStatus;
import com.jk.exception.MyException;
import com.jk.model.ScheduleJob;
import com.jk.config.task.AsyncJobFactory;
import com.jk.config.task.SyncJobFactory;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * author : fengjing
 * createTime : 2016-08-04
 * description : 定时任务操作辅助类
 * version : 1.0
 */
@Slf4j
public class ScheduleUtils {

    /**
     * 获取触发器key
     * 
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static TriggerKey getTriggerKey(String jobName, String jobGroup) {

        return TriggerKey.triggerKey(jobName , jobGroup);
    }

    /**
     * 获取jobKey
     *
     * @param jobName the job name
     * @param jobGroup the job group
     * @return the job key
     */
    public static JobKey getJobKey(String jobName, String jobGroup) {

        return JobKey.jobKey(jobName, jobGroup);
    }

    /**
     * 获取表达式触发器
     *
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @return cron trigger
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobName, String jobGroup) {

        try {
            TriggerKey triggerKey = ScheduleUtils.getTriggerKey(jobName, jobGroup);
            return (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            log.error("获取定时任务CronTrigger出现异常", e);
            throw new MyException("获取定时任务CronTrigger出现异常");
        }
    }

    /**
     * 创建任务
     *
     * @param scheduler the scheduler
     * @param scheduleJob the schedule job
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        createScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup(),
            scheduleJob.getCron(), scheduleJob.getIsSync(), scheduleJob);
    }

    /**
     * 创建定时任务
     *
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @param cronExpression the cron expression
     * @param isSync the is sync
     * @param param the param
     */
    public static void createScheduleJob(Scheduler scheduler, String jobName, String jobGroup,
                                         String cronExpression, Boolean isSync, Object param) {
        try {
            //同步或异步
            Class<? extends Job> jobClass = isSync ? AsyncJobFactory.class : SyncJobFactory.class;

            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName , jobGroup).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder).build();

            String jobTrigger = trigger.getKey().getName();

            ScheduleJob scheduleJob = (ScheduleJob)param;
            scheduleJob.setJobTrigger(jobTrigger);

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, scheduleJob);

            scheduler.scheduleJob(jobDetail, trigger);

            //暂停任务 (如果修改修改任务时，选择先删除再创建，保持原来的任务状态)
            if(JobStatus.PAUSE.getValue() == scheduleJob.getStatus()){
                pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
            }
        } catch (SchedulerException e) {
            log.error("创建定时任务失败", e);
            throw new MyException("创建定时任务失败");
        }
    }

    /**
     * 运行一次任务
     * 
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void runOnce(Scheduler scheduler, String jobName, String jobGroup) {
        JobKey jobKey = getJobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("运行一次定时任务失败", e);
            throw new MyException("运行一次定时任务失败");
        }
    }

    /**
     * 暂停任务
     * 
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void pauseJob(Scheduler scheduler, String jobName, String jobGroup) {

        JobKey jobKey = getJobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败", e);
            throw new MyException("暂停定时任务失败");
        }
    }

    /**
     * 恢复任务
     *
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void resumeJob(Scheduler scheduler, String jobName, String jobGroup) {

        JobKey jobKey = getJobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败", e);
            throw new MyException("暂停定时任务失败");
        }
    }


    /**
     * 更新定时任务
     *
     * @param scheduler the scheduler
     * @param scheduleJob the schedule job
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        updateScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup(),
            scheduleJob.getCron(), scheduleJob.getIsSync(), scheduleJob);
    }

    /**
     * 更新定时任务
     *
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @param cronExpression the cron expression
     * @param isSync the is sync
     * @param param the param
     */
    public static void updateScheduleJob(Scheduler scheduler, String jobName, String jobGroup,
                                         String cronExpression, boolean isSync, Object param) {

        //同步或异步
//        Class<? extends Job> jobClass = isSync ? AsyncJobFactory.class : SyncJobFactory.class;

        try {
//            JobDetail jobDetail = scheduler.getJobDetail(getJobKey(jobName, jobGroup));

//            jobDetail = jobDetail.getJobBuilder().ofType(jobClass).build();

            //更新参数 实际测试中发现无法更新
//            JobDataMap jobDataMap = jobDetail.getJobDataMap();
//            jobDataMap.put(ScheduleJobVo.JOB_PARAM_KEY, param);
//            jobDetail.getJobBuilder().usingJobData(jobDataMap);

            TriggerKey triggerKey = ScheduleUtils.getTriggerKey(jobName, jobGroup);

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            // 忽略状态为PAUSED的任务，解决集群环境中在其他机器设置定时任务为PAUSED状态后，集群环境启动另一台主机时定时任务全被唤醒的bug
            if(!triggerState.name().equalsIgnoreCase("PAUSED")){
                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            log.error("更新定时任务失败", e);
            throw new MyException("更新定时任务失败");
        }
    }

    /**
     * 删除定时任务
     *
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobName, String jobGroup) {
        try {
            scheduler.deleteJob(getJobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            log.error("删除定时任务失败", e);
            throw new MyException("删除定时任务失败");
        }
    }

    /**
     * 通过反射调用scheduleJob中定义的方法
     *
     * @param scheduleJob
     */
    public static void invokMethod(ScheduleJob scheduleJob) {
        Object object = null;
        Class<?> clazz = null;
        if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
            try {
                clazz = Class.forName(scheduleJob.getBeanClass());
                object = clazz.newInstance();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

        }
        if (object == null) {
            log.error("任务名称 = [" + scheduleJob.getJobName()
                    + "]---------------未启动成功，请检查是否配置正确！！！");
            return;
        }


        clazz = object.getClass();
        Method method = null;
        try {
            if(StrUtil.isNotBlank(scheduleJob.getParams())){
                method = clazz.getDeclaredMethod(scheduleJob.getMethodName(), String.class);
            }else{
                method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
            }

        } catch (NoSuchMethodException e) {
            log.error("任务名称 = [" + scheduleJob.getJobName()
                    + "]---------------未启动成功，方法名设置错误！！！");
        } catch (SecurityException e) {
            log.error(e.getMessage(), e);
        }


        if (method != null) {
            try {
                ReflectionUtils.makeAccessible(method);
                if(StringUtils.isNotBlank(scheduleJob.getParams())){
                    method.invoke(object, scheduleJob.getParams());
                }else{
                    method.invoke(object);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        log.debug("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
    }

}