package com.scream.study.spring.ioc.lesson3.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//@Component
public class EntityLesson3_LifeCycle_Processor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(beanName + "：beforeInit方法执行完毕...");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(beanName + "：afterInit方法执行完毕...");
		return bean;
	}
}
