package com.scream.study.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PoolTest {
    /**
     * Java通过Executors提供四种线程池，分别为：
     * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
     * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     *
     */
    public static void newCachedThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            Thread thread = new SleepThread();
            pool.execute(thread);
        }
    }

    public void newFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            Thread thread = new SleepThread();
            pool.execute(thread);
        }
    }

    public static void newSingleThreadExecutor() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            Thread thread = new SleepThread();
            pool.execute(thread);
        }
    }

    public static void newScheduledThreadPool() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 100; i++) {
            Thread thread = new SleepThread();
            pool.schedule(thread, 3, TimeUnit.SECONDS);//表示延迟3秒执行。
        }
        for (int i = 0; i < 100; i++) {
            Thread thread = new SleepThread();
            pool.scheduleAtFixedRate(thread, 1, 3, TimeUnit.SECONDS);//表示延迟1秒后每3秒执行一次。
        }
    }

    public static void main(String[] args) {
        //https://www.cnblogs.com/zhujiabin/p/5404771.html
    }
}
