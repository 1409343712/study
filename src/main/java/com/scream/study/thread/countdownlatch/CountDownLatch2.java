package com.scream.study.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本里模仿跑步比赛，所有选手（线程）等待发令枪响起，当所有选手到达终点后颁发奖项
 * 我们可以通过使用不同的CountDownLatch来控制线程的执行顺序，
 * 例如选手必须等待发令枪响begin之后才能开始比赛,颁奖仪式必须在所有选手都完成比赛后end才能进行
 */
public class CountDownLatch2 {
    static class Player implements Runnable {
        CountDownLatch begin;
        CountDownLatch end;

        Player(CountDownLatch begin, CountDownLatch end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                begin.await();//等待发令枪响
                System.out.println(Thread.currentThread().getName() + "到达终点");
                end.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        try {
            CountDownLatch prepare = new CountDownLatch(4);
            CountDownLatch begin = new CountDownLatch(1);
            CountDownLatch end = new CountDownLatch(4);
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
            for (int i = 0; i < 4; i++) {
                System.out.println(i+1 + "名选手已达到赛场");
                executorService.execute(new Player(begin, end));//选手入场
                prepare.countDown();
                if (prepare.getCount() == 0F) {
                    System.out.println("选手入场完毕");
                }
            }
            prepare.await();
            System.out.println("发令枪响 比赛开始");
            begin.countDown();//开始比赛
            end.await();
            System.out.println("开始颁奖");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
