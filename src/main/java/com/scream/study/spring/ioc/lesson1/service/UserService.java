package com.scream.study.spring.ioc.lesson1.service;

import org.springframework.stereotype.Service;


/**
 * @Scope
 * singleton  单例模式 全局有且仅有一个实例。（默认）
 * prototype  原型模式 每次获取Bean的时候都会有一个新的实例。
 * request    表示针对每次请求都会产生一个新的Bean对象，并且该Bean对象仅在当前Http请求内有效。
 * session    作用域表示煤气请求都会产生一个新的Bean对象，并且该Bean仅在当前Http session内有效。
 */

/**
 * @Lazy
 * spring默认是在配置文件加载的时候就会创建bean对象
 * 但是一旦加上@Lazy注解后 那么就意味着加载顺序的控制权移交给了@Lazy注解
 * -@Lazy(true)：getBean调用的时候创建  @Lazy(false)：配置文件加载的时候创建
 * 默认true
 *
 * 注意：一旦@Scope的值不等于singleton 那么@Lazy注解将失效 将会强制在getBean的时候创建bean对象
 */
//@Scope("prototype")
//@Lazy(false)
//@Lazy
@Service("userServce1")
public class UserService {
	public UserService() {
		System.out.println("无参构造函数被执行...");
	}
}
