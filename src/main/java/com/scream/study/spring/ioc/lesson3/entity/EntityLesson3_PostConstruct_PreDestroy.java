package com.scream.study.spring.ioc.lesson3.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class EntityLesson3_PostConstruct_PreDestroy {
	public EntityLesson3_PostConstruct_PreDestroy() {
		System.out.println("EntityLesson3初始化完成....");
	}

	@PostConstruct
	public void initMethod() {
		System.out.println("initMethod 执行结束...");
	}
    @PreDestroy
	public void destroyMethod() {
		System.out.println("destroyMethod 执行结束...");
	}
}
