package com.scream.study.reflect;

import java.io.Serializable;

public class Person extends Animal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Person() {
		System.out.println("执行无参数构造方法");
	}
	private Person(String value) {
		System.out.println("执行有参数构造方法" + value);
	}
	
	protected String name;
	
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	private String testInvoke() {
		return "testInvoke-withOutArgs";
	}
	private String testInvoke(String value) {
		return "testInvoke-".concat(value);
	}
	
}
