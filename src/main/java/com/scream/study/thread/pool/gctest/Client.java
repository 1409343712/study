package com.scream.study.thread.pool.gctest;

import java.util.concurrent.ExecutorService;

public class Client {

    public static void main(String[] args) {
        ExecutorService executorService = ThreadProvider.getExecutorService();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.out.println(i);
            Thread t1  = new SleepThread();
            executorService.execute(t1);
        }
    }
}
