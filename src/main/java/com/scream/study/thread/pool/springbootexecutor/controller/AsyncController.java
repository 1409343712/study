package com.scream.study.thread.pool.springbootexecutor.controller;

import com.alibaba.fastjson.JSON;
import com.scream.study.thread.pool.springbootexecutor.service.Service_Callable;
import com.scream.study.thread.pool.springbootexecutor.service.Service_Runnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * create by: scream
 * create time: 2020/2/5 15:47
 * description:
 * 若是我们使用线程池，来并发的执行任务，首先需要考虑的是，如何等待最后一个任务执行完毕，对任务结果进行汇总处理,下面介绍三种实现方法
 * 方法一：使用自旋操作，等待任务结果返回。
 * 方法二：使用CountDownLatch计数器
 * 方式三：使用Future的get方法的阻塞特性
 */
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

	@Autowired
	private Service_Callable service_callable;
	@Autowired
	private Service_Runnable service_runnable;

	/**
	 * 第一种：使用自旋锁等待Future.isDone()
	 */
	@RequestMapping("/spinlock")
	public String spinlock() {
		try {
			Future<String> future1 = service_callable.doTask1();
			Future<String> future2 = service_callable.doTask2();
			//自旋锁，停止等待
			while (true) {
				if (future1.isDone() && future2.isDone()) {
					log.info("Task1 result:{}", future1.get());
					log.info("Task2 result:{}", future2.get());
					break;
				}
				Thread.sleep(1000);
			}
			log.info("All tasks finished.");
			return "SUCCESS";
		} catch (InterruptedException e) {
			log.error("错误信息1", e);
		} catch (ExecutionException e) {
			log.error("错误信息2", e);
		}
		return "FAILURE";
	}

	/**
	 * 第二种：使用CountDownLatch.await()进行阻塞等待
	 */
	@RequestMapping("/countdownlatch")
	public String countdownlatch() {
		try {
			CountDownLatch latch = new CountDownLatch(2);
			Future<String> future1 = service_callable.doTask3(latch);
			Future<String> future2 = service_callable.doTask4(latch);
			//等待两个线程执行完毕
			latch.await();
			log.info("All tasks finished!");
			String result1 = future1.get();
			String result2 = future2.get();
			log.info(result1 + "--" + result2);
			return "SUCCESS";
		} catch (InterruptedException e) {
			log.error("错误信息1", e);
		} catch (ExecutionException e) {
			log.error("错误信息2", e);
		}
		return "FAILURE";
	}

	/**
	 * 第三种：使用Future.get()进行阻塞等待
	 * @return
	 */
	@RequestMapping("/futureget")
	public String futureget() {
		try {
			List<Future<String>> tasks = new ArrayList<>();
			tasks.add(service_callable.doTask1());
			tasks.add(service_callable.doTask2());

			List<String> results = new ArrayList<>();
			//各个任务执行完毕
			for (Future<String> task : tasks) {
				//每个任务都会再在此阻塞。
				results.add(task.get());
			}
			log.info("All tasks finished!");
			log.info("执行结果：{}", JSON.toJSONString(results));
			return "SUCCESS";
		} catch (InterruptedException e) {
			log.error("错误信息1", e);
		} catch (ExecutionException e) {
			log.error("错误信息2", e);
		}
		return "FAILURE";
	}

	/**
	 * 不需要返回值：测试Runnable
	 */
	@RequestMapping("/runnable")
	public void runnable() throws InterruptedException {
		service_runnable.executeAsync();
	}

}
