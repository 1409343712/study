package com.scream.study.dxc;

public class RunnableTest implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i += 10) {
            System.out.println(Thread.currentThread().getName() + "跑到了第" + i + "米");
        }
        System.out.println(Thread.currentThread().getName() + "到达终点");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableTest());
        Thread t2 = new Thread(new RunnableTest());
        Thread t3 = new Thread(new RunnableTest());
        t1.start();
        t2.start();
        t3.start();
    }
}
