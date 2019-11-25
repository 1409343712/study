package com.scream.study.thread.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class DoubleCallable {
    static class A implements Callable<String>{
        @Override
        public String call() throws Exception {
            for (int i = 0; i < 100000; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            return "first";
        }
    }
    static class B implements Callable<String>{
        @Override
        public String call() throws Exception {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            return "second";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> f1 = new FutureTask<String>(new A());
        FutureTask<String> f2 = new FutureTask<String>(new B());
        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(f2);
        t1.start();
        t2.start();
        System.out.println(f1.get());
        System.out.println(f2.get());

    }

}
