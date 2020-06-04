package com.scream.study.thread.pool.springbootexecutor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * create by: scream
 * create time: 2020/2/5 15:38
 * description: 无返回值 创建的Runnable任务
 */
@Slf4j
@Component
public class Service_Runnable {
	@Async//指定asyncServiceExecutor_1线程池执行任务
	public void executeAsync() throws InterruptedException {
		log.info("start executeAsync");
		Thread.sleep(1000);
		System.out.println(1 / 0);
		log.info("end executeAsync");
	}
}
