package com.jk.config.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * 定时任务配置
 * @author cuiP
 */
@Configuration
public class ScheduleConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);

        //quartz参数
//        Properties prop = new Properties();
//        prop.put("org.quartz.scheduler.instanceName", "GoodClusterScheduler");
//        prop.put("org.quartz.scheduler.instanceId", "AUTO");
//        //线程池配置
//        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
//        prop.put("org.quartz.threadPool.threadCount", "20");
//        prop.put("org.quartz.threadPool.threadPriority", "5");
//        //JobStore配置
//        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
//        //集群配置
//        prop.put("org.quartz.jobStore.isClustered", "true");
//        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
//        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
//
//        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
//        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
//        factory.setQuartzProperties(prop);
        factory.setConfigLocation(new ClassPathResource("/quartz.properties"));

        factory.setSchedulerName("GoodClusterScheduler");
        //延时启动
        factory.setStartupDelay(30);
        //把spring上下文以key/value的方式存放在了quartz的上下文中，在job实现类中可以用applicationContextSchedulerContextKey所定义的key得到对应的spring上下文
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);

        return factory;
    }
}
