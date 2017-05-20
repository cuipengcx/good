package com.jk.config.task;

import com.jk.model.ScheduleJob;
import com.jk.util.schedule.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
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
		log.info("SyncJobFactory execute");
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
		log.info("jobName: {} , jobGroup: {}" , scheduleJob.getJobName(), scheduleJob.getJobGroup());


		try {
			//本地通过反射调度
			if(scheduleJob.getIsLocal()){
				ScheduleUtils.invokMethod(scheduleJob);
			}else { //远程http请求调度
//                RestTemplate restTemplate = SpringUtils.getBean(RestTemplate.class);
//                JSONObject result = restTemplate.getForEntity(scheduleJob.getRemoteUrl(), JSONObject.class).getBody();
//                log.info("result:" + result.toString());
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}