package com.scream.study.thread.producer;

public class Controller {
    public static void main(String[] args) {
        Thread thread1 = new Producer();
        //设置线程优先级 1-10 由高到低
        thread1.setPriority(10);
        Consumer thread2 = new Consumer();
        Consumer thread3 = new Consumer();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
