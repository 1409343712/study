package com.scream.study.thread.reject;

import java.util.concurrent.*;

/**
 * 线程池中的线程已经用完了，无法继续为新任务服务，同时，等待队列也已经排满了，再也
 * 塞不下新任务了。这时候我们就需要拒绝策略机制合理的处理这个问题。
 * JDK 内置的拒绝策略如下：
 * 1. AbortPolicy ： 直接抛出异常，阻止系统正常运行。
 * 2. CallerRunsPolicy ： 只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的
 * 任务。显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降。
 * 3. DiscardOldestPolicy ： 丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再
 * 次提交当前任务。
 * 4. DiscardPolicy ： 该策略默默地丢弃无法处理的任务，不予任何处理。如果允许任务丢
 * 失，这是最好的一种方案。
 * 以上内置拒绝策略均实现了 RejectedExecutionHandler 接口，若以上策略仍无法满足实际
 * 需要，完全可以自己扩展 RejectedExecutionHandler 接口。
 */
public class Reject {

    /**
     * 新建一个队列长度一千，线程数为可用核减一，最大为五最小为一的线程池
     * @return
     */
    private static ExecutorService createExecutorService(RejectedExecutionHandler rejectedExecutionHandler) {
        System.out.println("当前系统可用核数为：" + Runtime.getRuntime().availableProcessors());
        int availProcessors = Runtime.getRuntime().availableProcessors() > 1
                ? Runtime.getRuntime().availableProcessors() - 1 : 1;
        int maxProcessors = Runtime.getRuntime().availableProcessors() > 5 ? 5 : availProcessors;
        if (availProcessors > maxProcessors) {
            availProcessors = maxProcessors;
        }
        System.out.println("availProcessors:" + availProcessors + "\r\n" + "maxProcessors" + maxProcessors) ;
        ExecutorService ecxecutorService =  new ThreadPoolExecutor(availProcessors, maxProcessors, 1L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2), rejectedExecutionHandler);
        return ecxecutorService;
    }

    public static void main(String[] args) {
//        ExecutorService executorService = createExecutorService(new ThreadPoolExecutor.AbortPolicy());
        ExecutorService executorService = createExecutorService(new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            Thread thread = new RejectWorkThread();
            executorService.execute(thread);
        }
    }
}
