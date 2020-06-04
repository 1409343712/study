package com.scream.study.spring.ioc.lesson3.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//@Component
public class EntityLesson3_LifeCycle implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {
	public EntityLesson3_LifeCycle() {
		System.out.println("EntityLesson3_LifeCycle初始化完成....");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("BeanNameAware执行完毕...");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("获取applicationContext对象...");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("init方法执行完毕...");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("销毁完毕...");

	}
}
