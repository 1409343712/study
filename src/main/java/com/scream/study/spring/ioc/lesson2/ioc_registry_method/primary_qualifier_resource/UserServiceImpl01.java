package com.scream.study.spring.ioc.lesson2.ioc_registry_method.primary_qualifier_resource;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl01 implements UserService {
	@Override
	public void add() {
		System.out.println("UserServiceImpl01");
	}
}
