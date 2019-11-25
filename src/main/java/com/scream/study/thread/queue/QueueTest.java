package com.scream.study.thread.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueTest {
    /**
     * 用数组实现的有界阻塞队列。此队列按照先进先出（FIFO）的原则对元素进行排序。默认情况下
     * 不保证访问者公平的访问队列，所谓公平访问队列是指阻塞的所有生产者线程或消费者线程，当
     * 队列可用时，可以按照阻塞的先后顺序访问队列，即先阻塞的生产者线程，可以先往队列里插入
     * 元素，先阻塞的消费者线程，可以先从队列里获取元素。通常情况下为了保证公平性会降低吞吐
     * 量
     *
     * @return
     */
    private static ArrayBlockingQueue getArrayBlockingQueue() {
        ArrayBlockingQueue unFairQueue = new ArrayBlockingQueue(1000);//默认创建非公平阻塞队列
        ArrayBlockingQueue fairQueue = new ArrayBlockingQueue(1000, true); //创建1000长度的公平队列
        try {
            for (int i = 0; i < 100; i++) {
                unFairQueue.put(i);
            }
            while (true) {
                System.out.println(unFairQueue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return fairQueue;
    }

    /**
     * 基于链表的阻塞队列，同 ArrayListBlockingQueue 类似，此队列按照先进先出（FIFO）的原则对
     * 元素进行排序。而 LinkedBlockingQueue 之所以能够高效的处理并发数据，还因为其对于生产者
     * 端和消费者端分别采用了独立的锁来控制数据同步，这也意味着在高并发的情况下生产者和消费
     * 者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。
     *
     * @return
     */
    private static LinkedBlockingQueue getLinkedBlockingQueue() {
//        LinkedBlockingQueue queue = new LinkedBlockingQueue();//无界
        LinkedBlockingQueue queue = new LinkedBlockingQueue(1000);
        return queue;
    }

    public static void main(String[] args) {
        getArrayBlockingQueue();
    }
}
