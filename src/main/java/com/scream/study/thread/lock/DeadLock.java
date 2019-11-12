package com.scream.study.thread.lock;

/**
 * 本例主要演示线程如何死锁
 * 线程A持有资源1请求资源2 同时 线程B持有资源2请求资源1 这种情况就会产生死锁
 * cmd-jps查看当前类的线程ID jconsole 线程ID 查看类的线程执行情况
 */
public class DeadLock extends Thread{
    private static Object hairA = new Object();

    private static Object hairB = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (hairA){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (hairB){
                    System.out.println("抓住了B的头发");
                }
            }
        }).start();
        new Thread(()->{
            synchronized (hairB){
                synchronized (hairA){
                    System.out.println("抓住了A的头发");
                }
            }
        }).start();
    }
}
