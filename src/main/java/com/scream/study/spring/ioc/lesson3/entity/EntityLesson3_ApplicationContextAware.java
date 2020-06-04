package com.scream.study.spring.ioc.lesson3.entity;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;

//@Component
public class EntityLesson3_ApplicationContextAware implements ApplicationContextAware {
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Arrays.asList(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
	}
}
