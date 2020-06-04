package com.scream.study.spring.ioc.lesson2.ioc_registry_method.primary_qualifier_resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 当@Autowired注解引入的接口UserService有多个实现类的时候 必须要指定其中的一个注入到IOC容器中 可以采用以下解决方案
 * 1：@Resource(name = "userServiceImpl01")
 * 2：@Autowired & @Qualifier("userServiceImpl01")
 * 3：UserServiceImpl01 类上加上注解@Primary
 */
@Component
public class QuoteService {

//	@Resource(name = "userServiceImpl02")

	@Autowired
//	@Qualifier("userServiceImpl01")

	private UserService userService;

	public void testAdd() {
		userService.add();
	}
}
