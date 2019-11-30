package com.scream.study.thread.pool.threadpooltaskexecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private VisiableThreadPoolTaskExecutor watcher;
    @RequestMapping("/async")
    public String async() {
        watcher.showThreadPoolInfo();
        System.out.println("start submit");
        asyncService.executeAsync();
        System.out.println("end submit");
        return "success";
    }
}
