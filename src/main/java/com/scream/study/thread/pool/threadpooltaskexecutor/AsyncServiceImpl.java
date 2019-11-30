package com.scream.study.thread.pool.threadpooltaskexecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
//@Scope("prototype")
public class AsyncServiceImpl implements AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
    @Override
    @Async("asyncServiceExecutor")// 在executeAsync方法上增加注解@Async(“asyncServiceExecutor”)，asyncServiceExecutor是ExecutorConfiguration.java中的方法名，表明executeAsync方法是属于asyncServiceExecutor方法创建的线程池
    public void executeAsync() {
        logger.info("start Async");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("end Async");
    }
}
