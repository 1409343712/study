package com.scream.study.thread.volatiletest;

import com.scream.study.thread.pool.gctest.SleepThread;

import java.util.concurrent.*;

public class VolatilePooProvider {
    /**
     * 新建一个队列长度一千，线程数为可用核减一，最大为五最小为一的线程池
     * @return
     */
    public static ExecutorService createExecutorService() {
        System.out.println("当前系统可用核数为：" + Runtime.getRuntime().availableProcessors());
        int availProcessors = Runtime.getRuntime().availableProcessors() > 1
                ? Runtime.getRuntime().availableProcessors() - 1 : 1;
        int maxProcessors = Runtime.getRuntime().availableProcessors() > 5 ? 5 : availProcessors;
        if (availProcessors > maxProcessors) {
            availProcessors = maxProcessors;
        }
        System.out.println("availProcessors:" + availProcessors + "\r\n" + "maxProcessors" + maxProcessors) ;
        ExecutorService ecxecutorService =  new ThreadPoolExecutor(availProcessors, maxProcessors, 1L, TimeUnit.MILLISECONDS,
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
}
