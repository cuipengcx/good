package com.jk.common.util.job;

import com.jk.common.util.SpringUtils;
import com.jk.modules.job.model.ScheduleJob;
import com.jk.modules.job.model.ScheduleJobLog;
import com.jk.modules.job.service.ScheduleJobLogService;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 调度任务执行
 * Created by cuiP on 2017/5/20.
 */
@Slf4j
public class ScheduleExecute {

    public static void execute(ApplicationContext applicationContext, ScheduleJob scheduleJob){

        ScheduleJobLogService scheduleJobLogService = applicationContext.getBean(ScheduleJobLogService.class);

        ScheduleJobLog scheduleJobLog = new ScheduleJobLog();
        scheduleJobLog.setJobId(scheduleJob.getId());
        scheduleJobLog.setJobName(scheduleJob.getJobName());
        scheduleJobLog.setJobGroup(scheduleJob.getJobGroup());
        scheduleJobLog.setCron(scheduleJob.getCron());
        scheduleJobLog.setIsLocal(scheduleJob.getIsLocal());
        scheduleJobLog.setRemoteUrl(scheduleJob.getRemoteUrl());
        scheduleJobLog.setBeanClass(scheduleJob.getBeanClass());
        scheduleJobLog.setMethodName(scheduleJob.getMethodName());
        scheduleJobLog.setParams(scheduleJob.getParams());
        scheduleJobLog.setIsAsync(scheduleJob.getIsAsync());
        scheduleJobLog.setRemarks(scheduleJob.getRemarks());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            if(scheduleJob.getIsLocal()){  //本地通过反射调度

                //执行本地调度
                ScheduleExecute.invokMethod(scheduleJob);

                //任务执行总时长
                long times = System.currentTimeMillis() - startTime;
                scheduleJobLog.setTimes(times);
                //任务状态 0：失败  1：成功
                scheduleJobLog.setStatus(1);

                log.info("本地调度任务→jobName: {} , jobGroup: {}，执行完毕，总耗时: {}毫秒！" , scheduleJob.getJobName(), scheduleJob.getJobGroup(), times);

            }else {                     //远程http请求调度

				RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);
                String params = scheduleJob.getParams();
                if(StrUtil.isEmpty(params)){
                    //执行调用
                    restTemplate.postForLocation(scheduleJob.getRemoteUrl(), null);
                }else {
                    //执行调用
                    Map<String, Object> uriVariables = new HashMap<String, Object>();

                    //封装参数 key1=1,key2=2
                    String[] paramsArray = params.split(",");
                    for (String s : paramsArray) {
                        if(StrUtil.isNotEmpty(s)){
                            String[] strMap = s.split("=");
                            if(strMap.length == 2){
                                uriVariables.put(strMap[0], strMap[1]);
                            }
                        }
                    }
                    restTemplate.postForLocation(scheduleJob.getRemoteUrl(), null, uriVariables);
                }

                //任务执行总时长
                long times = System.currentTimeMillis() - startTime;
                scheduleJobLog.setTimes(times);
                //任务状态 0：失败  1：成功
                scheduleJobLog.setStatus(1);

                log.info("远程调度任务→jobName: {} , jobGroup: {}，执行完毕，总耗时: {}毫秒！" , scheduleJob.getJobName(), scheduleJob.getJobGroup(), times);
            }
        } catch (Exception e) {
            log.error("调度任务→jobName: {} , jobGroup: {}，执行失败！e: {}", scheduleJob.getJobName(), scheduleJob.getJobGroup(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            scheduleJobLog.setTimes(times);
            //任务状态 0：失败  1：成功
            scheduleJobLog.setStatus(0);
            scheduleJobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        }finally {
            scheduleJobLogService.save(scheduleJobLog);
        }
    }


    /**
     * 通过反射调用scheduleJob中定义的方法
     *
     * @param scheduleJob
     */
    private static void invokMethod(ScheduleJob scheduleJob) throws Exception {
        Object object = null;
        Class<?> clazz = null;
        if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
            try {
                clazz = Class.forName(scheduleJob.getBeanClass());
                // 特别注意: 这里应该通过字节码去spring中查找对应的bean，这样才能在实例中通过注解注入所有spring管理的bean
                //错误用法: object = clazz.newInstance();
                object = SpringUtils.getBean(clazz);
            } catch (Exception e) {
                throw new SchedulerException("请检查是否配置正确！！！");
            }
        }

        if (object == null) {
            throw new SchedulerException("请检查是否配置正确！！！");
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
            throw new SchedulerException("方法名设置错误！！！");
        } catch (SecurityException e) {
            throw new SchedulerException("任务启动失败！");
        }


        if (method != null) {
            try {
                ReflectionUtils.makeAccessible(method);
                if(StringUtils.isNotBlank(scheduleJob.getParams())){
                    method.invoke(object, scheduleJob.getParams());
                }else{
                    method.invoke(object);
                }
                log.debug("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功!");
            } catch (Exception e) {
                throw new SchedulerException(e.getMessage());
            }
        }
    }
}
