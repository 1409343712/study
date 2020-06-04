package com.scream.study.spring.ioc.lesson2.ioc_registry_method.primary_qualifier_resource;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserServiceImpl02 implements UserService {
	@Override
	public void add() {
		System.out.println("UserServiceImpl02");
	}
}
