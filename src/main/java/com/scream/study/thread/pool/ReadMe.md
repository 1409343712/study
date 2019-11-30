1、new Thread的弊端
执行一个异步任务你还只是如下new Thread吗？

new Thread(new Runnable() {
 
@Override
public void run() {
// TODO Auto-generated method stub
}
}).start();

那你就out太多了，new Thread的弊端如下：
a. 每次new Thread新建对象性能差。
b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
c. 缺乏更多功能，如定时执行、定期执行、线程中断。
相比new Thread，线程池的好处在于：
a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
c. 提供定时执行、定期执行、单线程、并发数控制等功能。

**线程池的作用：**``````````````
 线程池作用就是限制系统中执行线程的数量。
     根据系统的环境情况，可以自动或手动设置线程数量，达到运行的最佳效果；少了浪费了系统资源，多了造成系统拥挤效率不高。用线程池控制线程数量，其他线程排 队等候。一个任务执行完毕，再从队列的中取最前面的任务开始执行。若队列中没有等待进程，线程池的这一资源处于等待。当一个新任务需要运行时，如果线程池 中有等待的工作线程，就可以开始运行了；否则进入等待队列。
为什么要用线程池:
1.减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。
2.可以根据系统的承受能力，调整线程池中工作线线程的数目，防止因为消耗过多的内存，而把服务器累趴下(每个线程需要大约1MB内存，线程开的越多，消耗的内存也就越大，最后死机)。

Java里面线程池的顶级接口是Executor，但是严格意义上讲Executor并不是一个线程池，而只是一个执行线程的工具。真正的线程池接口是ExecutorService。

**Java通过Executors提供四种线程池，分别为：**
newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

Executors 返回的线程池对象的弊端如下：
1）FixedThreadPool 和 SingleThreadPool: 允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
2）CachedThreadPool 和 ScheduledThreadPool: 允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。

**当一个任务通过 execute(Runnable) 方法欲添加到线程池时，线程池采用的策略如下：**

1. 如果此时线程池中的数量小于 corePoolSize ，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。

2. 如果此时线程池中的数量等于 corePoolSize ，但是缓冲队列 workQueue 未满，那么任务被放入缓冲队列。

3. 如果此时线程池中的数量大于 corePoolSize ，缓冲队列 workQueue 满，并且线程池中的数量小于maximumPoolSize ，建新的线程来处理被添加的任务。

4. 如果此时线程池中的数量大于 corePoolSize ，缓冲队列 workQueue 满，并且线程池中的数量等于maximumPoolSize ，那么通过 handler 所指定的策略来处理此任务。
 
处理任务的优先级为：

核心线程 corePoolSize 、任务队列 workQueue 、最大线程 maximumPoolSize ，如果三者都满了，使用 handler处理被拒绝的任务。当线程池中的线程数量大于 corePoolSize 时，如果某线程空闲时间超过 keepAliveTime ，线程将被终止。这样，线程池可以动态的调整池中的线程数。