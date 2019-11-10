package com.scream.study.dxc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallAbleTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 100; i += 10) {
            System.out.println(Thread.currentThread().getName() + "跑到了第" + i + "米");
        }
        return Thread.currentThread().getName() + "到达终点";
    }
    public static void main(String[] args) {
        FutureTask<String> f1 = new FutureTask<String>(new CallAbleTest());
        FutureTask<String> f2 = new FutureTask<String>(new CallAbleTest());
        FutureTask<String> f3 = new FutureTask<String>(new CallAbleTest());
        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(f2);
        Thread t3 = new Thread(f3);
        t1.start();
        t2.start();
        t3.start();
        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
