package com.jk.config.job;

import com.jk.common.util.job.ScheduleExecute;
import com.jk.modules.job.model.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 异步任务工厂(计划任务执行处 无状态)
 * @author cuiP
 */
@Slf4j
public class AsyncJobFactory extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("AsyncJobFactory execute,执行开始...");
			ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);

			//获取spring上下文
			ApplicationContext applicationContext = (ApplicationContext)context.getScheduler().getContext().get("applicationContextKey");

			//执行调度任务
			ScheduleExecute.execute(applicationContext, scheduleJob);
		} catch (SchedulerException e) {
			log.error("AsyncJobFactory execute,执行失败...");
		}
	}
}