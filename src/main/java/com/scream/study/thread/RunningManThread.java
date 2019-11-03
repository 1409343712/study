package com.scream.study.thread;

public class RunningManThread extends Thread{
    private String name;

    public RunningManThread(String name){
        this.name = name;
    }
    @Override
    public void run() {
        for (int i = 0; i <= 100; i+=10) {
            System.out.println(name + "跑到了第" + i + "米");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new RunningManThread("張三");
        Thread thread2 = new RunningManThread("李四");
        Thread thread3 = new RunningManThread("王五");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
