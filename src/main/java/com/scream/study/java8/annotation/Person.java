package com.scream.study.java8.annotation;

import java.lang.annotation.Repeatable;

@Repeatable(Persons.class)
public  @interface Person{
	String role() default "";
}
