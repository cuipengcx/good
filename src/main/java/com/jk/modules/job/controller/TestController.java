package com.jk.modules.job.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 远程调度测试类
 * Created by cuip on 2017/5/21.
 */
@Slf4j
@RestController
public class TestController {

    /**
     * 测试无参数
     */
    @PostMapping("test")
    public void test1(){
        log.info("我是无参数远程调度，正在执行。。！");
    }

    /**
     * 测试带参数
     * @param key1
     * @param key2
     * @param key3
     */
    @PostMapping("test/{key1}/{key2}")
    public void test2(@PathVariable("key1") String key1, @PathVariable("key2") String key2, String key3){
        log.info("我是带参数远程调度，正在执行！参数; key1 = {}, key2 = {} ,key3 = {}" , key1, key2, key3);
    }

    /**
     * 测试request接收参数
     * @param request
     */
    @PostMapping("test3")
    public void test3(HttpServletRequest request){
       Map<String, String[]> maps = request.getParameterMap();
       log.info("测试request接收参数，个数: {}", maps.size());
    }
}
