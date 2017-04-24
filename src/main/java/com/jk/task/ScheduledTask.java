package com.jk.task;

import com.jk.service.RoleService;
import com.jk.service.UserService;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author cuiP
 * Created by cuip on 2017/4/5.
 */
@Component
public class ScheduledTask {

    private static final Log LOG = LogFactory.get();

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @Async
    @Scheduled(cron = "0/10 * * * * ? ")
    public void asyncTask1(){
        LOG.info("异步任务1开始执行...");
    }

    @Async
    @Scheduled(cron = "0/10 * * * * ? ")
    public void asyncTask2(){
        LOG.info("异步任务2开始执行...");
    }


}
