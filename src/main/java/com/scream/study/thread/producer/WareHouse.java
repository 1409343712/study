package com.scream.study.thread.producer;

import java.util.*;

public enum WareHouse {
    INSTANCE;
    private List<String> tmpList = new ArrayList<String>();

    public synchronized void add() {
        if (tmpList.size() < 20) {
            System.out.println("生产者创建了一个元素");
            tmpList.add("a");
        } else {
            this.notifyAll();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void remove() {
        if (tmpList.size() > 0) {
            System.out.println("消费者拿走了一个元素");
            tmpList.remove(0);
        } else {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // stack 先进后出
        Stack<String> tmpStack = new Stack<String>();
        tmpStack.push("1");
        tmpStack.push("2");
        tmpStack.push("3");
        tmpStack.push("4");
        while (!tmpStack.isEmpty()) {
            System.out.println("xxx" + tmpStack.pop());
//            System.out.println(tmpStack.peek());
        }
    }
}
