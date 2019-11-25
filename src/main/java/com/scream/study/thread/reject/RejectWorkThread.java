package com.scream.study.thread.reject;

public class RejectWorkThread extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
