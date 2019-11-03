package com.scream.study.thread.producer;

public class Consumer extends Thread {
    @Override
    public void run() {
        while (true) {
            WareHouse.INSTANCE.add();
        }
    }
}
