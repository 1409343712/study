package com.scream.study.java8.annotation.bean;

import com.scream.study.java8.annotation.Person;
import com.scream.study.java8.annotation.Persons;
import org.junit.Test;

import java.lang.annotation.Annotation;

@Persons({@Person(role= "boss"),@Person(role= "father")})
public class LiSi {
	@Test
	public void test() {
		Annotation[] annotations = LiSi.class.getAnnotations();
		System.out.println(annotations.length);
		Persons p1 = (Persons) annotations[0];
		for (Person p : p1.value()) {
			System.out.println(p.role());
		}
	}
}
