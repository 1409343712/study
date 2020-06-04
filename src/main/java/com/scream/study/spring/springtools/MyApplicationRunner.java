package com.scream.study.spring.springtools;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("ApplicationRunner.run()方法将在项目启动成功后立即执行，避免因为一些Bean还没有初始化而导致空指针异常...");
	}
}