package com.scream.study.thread.pool;

import com.scream.study.thread.pool.gctest.SleepThread;

import java.util.concurrent.*;

/**
 * Java通过Executors提供四种线程池，
 * 分别为：newCachedThreadPool,newFixedThreadPool,newSingleThreadExecutor,newScheduledThreadPool
 */
public class PoolTest {
	/**
	 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	 */
	private static void newCachedThreadPool() {
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			Thread thread = new SleepThread();
			pool.execute(thread);
		}
	}

	/**
	 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	 */
	private static void newFixedThreadPool() {
		ExecutorService pool = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 100; i++) {
			Thread thread = new SleepThread();
			pool.execute(thread);
		}
	}

	/**
	 * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
	 */
	private static void newSingleThreadExecutor() {
		ExecutorService pool = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 100; i++) {
			Thread thread = new SleepThread();
			pool.execute(thread);
		}
	}

	/**
	 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	 */
	private static void newScheduledThreadPool() {
		System.out.println("准备开始执行");
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new SleepThread();
//            pool.schedule(thread, 3, TimeUnit.SECONDS);//表示延迟3秒执行。
//        }
		Thread thread = new SleepThread();
		pool.scheduleAtFixedRate(thread, 1, 3, TimeUnit.SECONDS);//表示延迟1秒后每3秒执行一次。
	}

	/**
	 * 新建一个队列长度一千，线程数为可用核减一，最大为五最小为一的线程池
	 *
	 * @return
	 */
	private static ExecutorService createExecutorService() {
		System.out.println("当前系统可用核数为：" + Runtime.getRuntime().availableProcessors());
		int availProcessors = Runtime.getRuntime().availableProcessors() > 1
				? Runtime.getRuntime().availableProcessors() - 1 : 1;
		int maxProcessors = Runtime.getRuntime().availableProcessors() > 5 ? 5 : availProcessors;
		if (availProcessors > maxProcessors) {
			availProcessors = maxProcessors;
		}
		System.out.println("availProcessors:" + availProcessors + "\r\n" + "maxProcessors" + maxProcessors);
		ExecutorService ecxecutorService = new ThreadPoolExecutor(availProcessors, maxProcessors, 1L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(1000), new RejectedExecutionHandler() {

			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				try {
					if (!executor.isShutdown()) {
						executor.getQueue().put(r);
					}
				} catch (InterruptedException e) {
					System.out.println("队列中断");
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		});
		return ecxecutorService;
	}

	public static void main(String[] args) {
		newScheduledThreadPool();
	}
}
