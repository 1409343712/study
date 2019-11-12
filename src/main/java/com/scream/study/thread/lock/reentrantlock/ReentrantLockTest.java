package com.scream.study.thread.lock.reentrantlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁练习
 */
public class ReentrantLockTest implements Callable<Integer>{
    private transient ReentrantLock lock;

    private List<String> list = new ArrayList<>();

    public ReentrantLockTest(List<String> list, ReentrantLock lock) {
        this.list = list;
        this.lock = lock;
    }
    @Override
    public Integer call() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread().getName());
                list.add("xxx");

            }
        } finally {
            lock.unlock();
        }
        return list.size();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        List<String> list = new ArrayList<>();
        FutureTask<Integer> f1 = new FutureTask<>(new ReentrantLockTest(list, lock));
        FutureTask<Integer> f2 = new FutureTask<>(new ReentrantLockTest(list, lock));
//        FutureTask<Integer> f3 = new FutureTask<>(new ReentrantLockTest(list));
//        FutureTask<Integer> f4 = new FutureTask<>(new ReentrantLockTest(list));
        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(f2);
//        Thread t3 = new Thread(f3);
//        Thread t4 = new Thread(f4);
        t1.start();
        t2.start();
//        t3.start();
//        t4.start();
        System.out.println(f1.get().hashCode());
        System.out.println(f2.get().hashCode());
//        System.out.println(f3.get().hashCode());
//        System.out.println(f4.get().hashCode());

    }
}
