package com.jk.config.job;

import com.jk.common.util.job.ScheduleExecute;
import com.jk.modules.job.model.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 同步任务工厂(若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作)
 * @author cuiP
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SyncJobFactory extends QuartzJobBean {

	@Override
	public void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("SyncJobFactory execute,执行开始...");
			//获取任务记录
			ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
			//获取spring上下文
			ApplicationContext applicationContext = (ApplicationContext)context.getScheduler().getContext().get("applicationContextKey");

			//执行调度任务
			ScheduleExecute.execute(applicationContext, scheduleJob);
		} catch (SchedulerException e) {
			log.error("SyncJobFactory execute,执行失败...");
		}
	}

}