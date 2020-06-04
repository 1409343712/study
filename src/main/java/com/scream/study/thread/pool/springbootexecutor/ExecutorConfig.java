package com.scream.study.thread.pool.springbootexecutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * create by: scream
 * create time: 2020/2/5 1:59
 * description: 根据业务配置不同的线程池
 * 我们不推荐一个项目配置一个线程池，这样若是某些业务出现异常时，会影响到整个项目的健壮性。故我们可以根据业务，为不同的业务配置不同参数的数据库连接池。
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig implements AsyncConfigurer {

	@Bean
	public Executor firstAsyncExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new VisiableThreadPoolTaskExecutor();
		//核心线程数
		threadPoolTaskExecutor.setCorePoolSize(5);
		//最大线程数
		threadPoolTaskExecutor.setMaxPoolSize(5);
		//配置队列大小
		threadPoolTaskExecutor.setQueueCapacity(50);
		//(maxPoolSize-corePoolSize)部分线程空闲最大存活时间
		threadPoolTaskExecutor.setKeepAliveSeconds(60);
		//配置线程池前缀
		threadPoolTaskExecutor.setThreadNamePrefix("firstAsyncExecutor-");
		//拒绝策略
		// 默认 ThreadPoolExecutor.AbortPolicy();
		threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 等待所有任务结束后再关闭线程池，避免强制关闭线程池造成任务中断
		threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		// 设置线程销毁等待时间 超过时间自动销毁
		threadPoolTaskExecutor.setAwaitTerminationSeconds(60);

		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}

	@Bean
	public Executor secondAsyncExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new VisiableThreadPoolTaskExecutor();
		//核心线程数
		threadPoolTaskExecutor.setCorePoolSize(5);
		//最大线程数
		threadPoolTaskExecutor.setMaxPoolSize(5);
		//配置队列大小
		threadPoolTaskExecutor.setQueueCapacity(50);
		//(maxPoolSize-corePoolSize)部分线程空闲最大存活时间
		threadPoolTaskExecutor.setKeepAliveSeconds(60);
		//配置线程池前缀
		threadPoolTaskExecutor.setThreadNamePrefix("secondAsyncExecutor-");
		//拒绝策略
		// 默认 ThreadPoolExecutor.AbortPolicy();
		threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		// 等待所有任务结束后再关闭线程池，避免强制关闭线程池造成任务中断
		threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		// 设置线程销毁等待时间 超过时间自动销毁
		threadPoolTaskExecutor.setAwaitTerminationSeconds(60);

		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (ex, method, params) -> {
			log.error("异步线程执行失败。方法：[{}],异常信息[{}] : ", method, ex.getMessage(), ex);
		};
	}
}
