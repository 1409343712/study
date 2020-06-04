package com.scream.study.spring.aop.lesson1;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestV1 {
	private AnnotationConfigApplicationContext applicationContext;

	@Before
	public void before() {
		applicationContext = new AnnotationConfigApplicationContext(Configuration_Aop_Lesson1.class);
	}

	@Test
	public void test() {
		MyService myService = applicationContext.getBean("myService", MyService.class);
		System.out.println(myService.test());
	}
}
