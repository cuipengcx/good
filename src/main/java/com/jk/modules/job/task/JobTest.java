package com.jk.modules.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobTest {

	/**
	 * 测试无参数的调度任务
     */
	public void run1() {
		log.info("我是无参的run1方法，正在被执行");
	}

	/**
	 * 测试带参数的调度任务
	 * @param params
     */
	public void run2(String params){
		log.info("我是带参的run2方法，正在被执行, 参数为:{}", params);

	}

	/**
	 * 测试同步异步有效性
     */
	public void run3(){
		try {
			log.info("我是测试同步异步的有效性run3方法，正在被执行");
			Thread.sleep(1000 * 60L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
