package com.scream.study.spring.ioc.lesson3.entity;

//@Component
//@Scope("prototype")
//@Lazy
public class EntityLesson3 {
	public EntityLesson3() {
		System.out.println("EntityLesson3初始化完成....");
	}

	public void initMethod() {
		System.out.println("initMethod 执行结束...");
	}

	public void destroyMethod() {
		System.out.println("destroyMethod 执行结束...");
	}
}
