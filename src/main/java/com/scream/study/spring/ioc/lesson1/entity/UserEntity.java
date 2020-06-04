package com.scream.study.spring.ioc.lesson1.entity;

import lombok.Data;

@Data
public class UserEntity {
	private String name;
	private Integer age;

	public UserEntity(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
