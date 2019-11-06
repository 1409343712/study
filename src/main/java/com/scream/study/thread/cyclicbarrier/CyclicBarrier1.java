package com.scream.study.thread.cyclicbarrier;

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier1 {
    private static Vector<Double> collectList = new Vector<>();
    private static CyclicBarrier barrier = new CyclicBarrier(5, new BossTread());

    static class BossTread implements Runnable {
        @Override
        public void run() {
            double sumDouble = 0;
            System.out.println("收到了" + collectList.size() + "个雇员上交的钱");
            for (Double aDouble : collectList) {
                sumDouble += Double.valueOf(aDouble);
            }
            System.out.println("共计收到" + sumDouble + "美元");
            collectList.clear();
        }
    }

    static class WorkThread implements Runnable {
        @Override
        public void run() {
            double monney = Math.random();
            System.out.println("员工" + Thread.currentThread().getName() + "上交了" + monney + "美元");
            collectList.add(monney);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("员工" + Thread.currentThread().getName() + "继续工作");
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new WorkThread());
        }
    }
}
