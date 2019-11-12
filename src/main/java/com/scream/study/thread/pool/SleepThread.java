package com.scream.study.thread.pool;

public class SleepThread extends Thread{
//    @Override
//    public void run() {
//        try {
//            Thread.sleep(10);
//            System.out.println(Thread.currentThread().getName() + "睡眠结束");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "睡眠结束");
    }
}
