package com.scream.study.spring.ioc.lesson1;

import com.scream.study.spring.ioc.lesson1.configuration.SpringConfig;
import com.scream.study.spring.ioc.lesson1.entity.UserEntity;
import com.scream.study.spring.ioc.lesson1.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestV1 {

	private static AnnotationConfigApplicationContext annotationConfigApplicationContext;

	/**
	 * create by: scream
	 * create time: 2020/1/22 22:53
	 * description: AnnotationConfigApplicationContext 等同于 ClassPathXmlApplicationContext 前者基于注解 后者基于xml
	 */
	@Before
	public void before() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		System.out.println("Spring配置文件加载完毕...");
	}

	@Test
	public void getBean() {
		//通过@Bean注册到容器中的类
		UserEntity userEntity = annotationConfigApplicationContext.getBean("userEntity", UserEntity.class);//SpringConfigV2 userEntity()
		System.out.println(userEntity.toString());
		//通过@Service注解注册到容器中的类
		UserService userService = annotationConfigApplicationContext.getBean("aop_Lesson2_Service", UserService.class);// 类名首字母小写
		System.out.println(userService.getClass());
	}
	@Test
	public void getBeanDefinitionNames() {
		//打印所有注注册到容器中的类
		String[] classes = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String aClass : classes) {
			System.out.println(aClass);
		}
	}

	/**
	 * create by: scream
	 * create time: 2020/1/23 1:12
	 * description: bean是在什么时候创建的？
	 * 懒汉：getBean调用的时候创建  饿汉：配置文件加载的时候创建
	 * 默认懒汉
	 */
	@Test
	public void scope() {
		UserService userService1 = annotationConfigApplicationContext.getBean("aop_Lesson2_Service", UserService.class);
		UserService userService2 = annotationConfigApplicationContext.getBean("aop_Lesson2_Service", UserService.class);
		// spting容器中默认是单例 当改变@scope属性时查看结果
		System.out.println(userService1 == userService2);
	}

	@Test
	public void lazy() {
		// 当懒汉模式下 只有getBean方法被执行的时候 才会输出UserService无参构造函数的内容
//		UserService userService = annotationConfigApplicationContext.getBean("userService", UserService.class);
	}
}
