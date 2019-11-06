package com.scream.study.thread.countdownlathc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch1 implements Runnable {
    private CountDownLatch latch;

    public CountDownLatch1(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "准备开始工作");
        latch.countDown();
    }

    public static void main(String[] args) {
        try {
            CountDownLatch latch = new CountDownLatch(3);
            Thread t1 = new Thread(new CountDownLatch1(latch));
            Thread t2 = new Thread(new CountDownLatch1(latch));
            Thread t3 = new Thread(new CountDownLatch1(latch));
            t1.start();
            t2.start();
            t3.start();
            System.out.println("等待latch执行完毕！");
            latch.await();
            System.out.println("latch执行完毕 开始后续操作");
            System.out.println("啥也不干 你说气人不");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
