package com.jk.config.async;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.jk.config
 * @description: 异步任务线程池配置
 * @author: cuiP
 * @date: 2018/1/5 9:52
 * @version: V1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.task.pool")
public class AsyncProperties {

    /**
     * 核心线程数
     */
    private int corePoolSize = 7;

    /**
     * 最大线程数
     */
    private int maxPoolSize = 50;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveSeconds = 60;

    /**
     * 队列长度
     */
    private int queueCapacity = 10000;

    /**
     * 线程名称前缀
     */
    private String threadNamePrefix = "Good-AsyncTask-";
}
