package com.scream.study.thread.pool.gctest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public final class ThreadProvider {
    private static Logger LOGGER = LoggerFactory.getLogger(ThreadProvider.class);
    private ThreadProvider() {

    }
    // 线程池
    private static ExecutorService ecxecutorService;
    // 单线程 线程池
    private static ExecutorService singleEcxecutorService;


    /**
     * 新建一个队列长度一千，线程数为可用核减一，最大为五最小为一的线程池
     * @return
     */
    private static void createExecutorService() {
        LOGGER.warn("当前系统可用核数为：" + Runtime.getRuntime().availableProcessors());
        int availProcessors = Runtime.getRuntime().availableProcessors() > 1
                ? Runtime.getRuntime().availableProcessors() - 1 : 1;
        int maxProcessors = Runtime.getRuntime().availableProcessors() > 5 ? 5 : availProcessors;
        if (availProcessors > maxProcessors) {
            availProcessors = maxProcessors;
        }
        System.out.println("availProcessors:" + availProcessors + "\r\n" + "maxProcessors" + maxProcessors) ;
        ecxecutorService =  new ThreadPoolExecutor(availProcessors, maxProcessors, 1L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1000), new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    if (!executor.isShutdown()) {
                        executor.getQueue().put(r);
                    }
                } catch (InterruptedException e) {
                    LOGGER.error("队列中断", e);
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static void createSingleExecutorService() {
        singleEcxecutorService =  new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1000), new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    if (!executor.isShutdown()) {
                        executor.getQueue().put(r);
                    }
                } catch (InterruptedException e) {
                    LOGGER.error("队列中断", e);
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * 等到所有线程任务都执行完毕
     * isShutDown当调用shutdown()或shutdownNow()方法后返回为true。
     * isTerminated当调用shutdown()方法后，并且所有提交的任务完成后返回为true;
     * isTerminated当调用shutdownNow()方法后，成功停止后返回为true;
     * 如果线程池任务正常完成，都为false
     * @param executorService
     * @throws InterruptedException
     */
    public static void waitForExecutorTerminated(ExecutorService executorService) throws InterruptedException {
        while (true) {
            if (executorService.isTerminated()) {
                break;
            }
            Thread.sleep(1000);
        }
    }
    /**
     * 返回自定义线程池，需要的时候才实例化线程池，Application范围内只实例化一次
     * @return
     */
    public static ExecutorService getExecutorService() {
        if(ecxecutorService == null) {
            createExecutorService();
        }
        return ecxecutorService;
    }
    /**
     * 返回单线程线程池，需要的时候才实例化线程池，Application范围内只实例化一次
     * @return
     */
    public static ExecutorService getSingleExecutorService() {
        if(singleEcxecutorService == null) {
            createSingleExecutorService();
        }
        return singleEcxecutorService;
    }
}

