package com.scream.study.spring.ioc.lesson3;

import com.scream.study.spring.ioc.lesson3.configuration.*;
import com.scream.study.spring.ioc.lesson3.entity.EntityLesson3;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestV3 {
	private AnnotationConfigApplicationContext annotationConfigApplicationContext;

	@Before
	public void before() {
//		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Configuration_Scope_Lazy.class);
//		System.out.println("IOC容器初始化完成...");
	}

	// 1、判断@Scope单例下是否在IOC容器初始化的时候就对Bean进行实例化操作 结论：是
	// 2、判断@Scope多例下是否在获取bean的时候才会实例化 结论：是
	// 3、判断@Scope多例下是否每次获取bean的时候都会实例化 结论：是
	@Test
	public void testScope() {
		EntityLesson3 entityLesson3 = annotationConfigApplicationContext.getBean("entityLesson3", EntityLesson3.class);
		entityLesson3 = annotationConfigApplicationContext.getBean("entityLesson3", EntityLesson3.class);
	}

	// 1、判断@Lazy下是否是在获取bean的时候才会实例化 结论：是
	// 2、判断@Lazy下是否只实例化一次Bean 结论：是
	// 3、判断@Scope多例下是否每次获取bean的时候都会实例化 结论：否
	@Test
	public void testLazy() {
		EntityLesson3 entityLesson3 = annotationConfigApplicationContext.getBean("entityLesson3", EntityLesson3.class);
		entityLesson3 = annotationConfigApplicationContext.getBean("entityLesson3", EntityLesson3.class);
		entityLesson3 = annotationConfigApplicationContext.getBean("entityLesson3", EntityLesson3.class);
	}

	// initMethod方法在无参Constructor执行之后执行
	@Test
	public void testInit_Destroy() {
//		annotationConfigApplicationContext.close();
		annotationConfigApplicationContext.removeBeanDefinition("entityLesson3");
	}

	// 测试通过实现InitializingBean，DisposableBean 执行initMethod 和 destroyMethod
	@Test
	public void test_InitializingBean_DisposableBean() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Configuration_InitializingBean_DisposableBean.class);
		annotationConfigApplicationContext.close();
	}

	// 测试通过@PostConstruct @PreDestroy 执行initMethod 和 destroyMethod
	@Test
	public void test_PostConstruct_PreDestroy() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Configuration_PostConstruct_PreDestroy.class);
		annotationConfigApplicationContext.close();
	}

	// 判断是否继承ApplicationContextAware接口就可以获取到ApplicationContext对象 结论：是
	@Test
	public void test_ApplicationContextAware() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Configuration_ApplicationContextAware.class);
	}

	// 测试Bean生命周期
	@Test
	public void test_LifeCycle() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Configuration_LifeCycle.class);
	}
}
