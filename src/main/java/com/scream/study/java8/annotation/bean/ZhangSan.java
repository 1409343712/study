package com.scream.study.java8.annotation.bean;

import com.scream.study.java8.annotation.Person;
import com.scream.study.java8.annotation.Persons;
import org.junit.Test;

import java.lang.annotation.Annotation;

@Person(role = "CEO")
@Person(role = "husband")
@Person(role = "father")
@Person(role = "son")
public class ZhangSan {

	@Test
	public void testRepeat() {
		Annotation[] annotations = ZhangSan.class.getAnnotations();
		System.out.println(annotations.length);
		Persons p1 = (Persons) annotations[0];
		for (Person p : p1.value()) {
			System.out.println(p.role());
		}
	}
	@Test
	public void testTypeUse() {
		Annotation[] annotations = ZhangSan.class.getAnnotations();
		System.out.println(annotations.length);
		Persons p1 = (Persons) annotations[0];
		for (Person p : p1.value()) {
			System.out.println(p.role());
		}
	}
}
