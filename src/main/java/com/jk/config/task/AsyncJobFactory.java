package com.jk.config.task;

import com.jk.model.ScheduleJob;
import com.jk.util.schedule.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 异步任务工厂(计划任务执行处 无状态)
 * @author cuiP
 */
@Slf4j
public class AsyncJobFactory extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("AsyncJobFactory execute");
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
		log.info("jobName: {} , jobGroup: {}" , scheduleJob.getJobName(), scheduleJob.getJobGroup());

		try {
			//本地通过反射调度
			if(scheduleJob.getIsLocal()){
				ScheduleUtils.invokMethod(scheduleJob);
			}else { //远程http请求调度
//				RestTemplate restTemplate = SpringUtils.getBean(RestTemplate.class);
//				JSONObject result = restTemplate.getForEntity(scheduleJob.getRemoteUrl(), JSONObject.class).getBody();
//				log.info("result:" + result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}