package com.scream.study.spring.ioc.lesson3.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * create by: scream
 * create time: 2020/1/30 23:55
 * description: InitializingBean：提供了initMethod。DisposableBean：提供了destroyMethod
 */
//@Component
public class EntityLesson3_InitializingBean_DisposableBean implements InitializingBean, DisposableBean {
	public EntityLesson3_InitializingBean_DisposableBean() {
		System.out.println("EntityLesson3初始化完成....");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet 执行完毕。。。");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy 执行完毕。。。");
	}
}
