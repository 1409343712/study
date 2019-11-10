package com.scream.study.thread.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable<V>可以设置返回值
 */
public class RunningManCallable implements Callable<String> {
    private String name;

    public RunningManCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        for (int i = 0; i <= 100; i += 10) {
            System.out.println(name + "跑到了第" + i + "米");
        }
        return Thread.currentThread().getName()+ "跑完了";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> f1 = new FutureTask<String>(new RunningManCallable("張三"));
        FutureTask<String> f2 = new FutureTask<String>(new RunningManCallable("李四"));
        FutureTask<String> f3 = new FutureTask<String>(new RunningManCallable("王五"));
        Thread thread1 = new Thread(f1);
        Thread thread2 = new Thread(f2);
        Thread thread3 = new Thread(f3);
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println(f1.get());//拿到线程返回值
        System.out.println(f2.get());
        System.out.println(f3.get());
    }
}
