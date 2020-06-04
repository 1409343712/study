package com.scream.study.thread.pool.springbootexecutor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * create by: scream
 * create time: 2020/2/5 15:38
 * description: 有返回值 创建的CallAble任务
 */
@Slf4j
@Component
public class Service_Callable {
	@Async("firstAsyncExecutor")//指定firstAsyncExecutor线程池执行任务
	public Future<String> doTask1() throws InterruptedException {
		log.info("Task1 started.");
		long start = System.currentTimeMillis();
		Thread.sleep(5000);
		long end = System.currentTimeMillis();

		log.info("Task1 finished, time elapsed: {} ms.", end - start);

		return new AsyncResult<>("Task1 accomplished!");
	}

	@Async("firstAsyncExecutor")//指定firstAsyncExecutor线程池执行任务
	public Future<String> doTask2() throws InterruptedException {
		log.info("Task2 started.");
		long start = System.currentTimeMillis();
		Thread.sleep(3000);
		long end = System.currentTimeMillis();

		log.info("Task2 finished, time elapsed: {} ms.", end - start);

		return new AsyncResult<>("Task2 accomplished!");
	}

	@Async("secondAsyncExecutor")//指定secondAsyncExecutor线程池执行任务
	public Future<String> doTask3(CountDownLatch latch) throws InterruptedException {
		log.info("Task3 started.");
		long start = System.currentTimeMillis();
		Thread.sleep(5000);
		long end = System.currentTimeMillis();

		log.info("Task3 finished, time elapsed: {} ms.", end - start);
		latch.countDown();
		return new AsyncResult<>("Task3 accomplished!");
	}

	@Async("secondAsyncExecutor")//指定secondAsyncExecutor线程池执行任务
	public Future<String> doTask4(CountDownLatch latch) throws InterruptedException {
		log.info("Task4 started.");
		long start = System.currentTimeMillis();
		Thread.sleep(3000);
		long end = System.currentTimeMillis();
		latch.countDown();
		log.info("Task4 finished, time elapsed: {} ms.", end - start);

		return new AsyncResult<>("Task4 accomplished!");
	}
}
