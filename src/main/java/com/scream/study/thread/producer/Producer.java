package com.scream.study.thread.producer;

public class Producer extends Thread {
    @Override
    public void run() {
        while (true) {
            WareHouse.INSTANCE.remove();
        }
    }
}
