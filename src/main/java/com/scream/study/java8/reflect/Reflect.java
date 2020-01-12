package com.scream.study.java8.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflect {
	public static void main(String[] args) throws Exception {
		Method method = Reflect.class.getMethod("main", String[].class);
		Arrays.stream(method.getParameterTypes()).forEach(item -> System.out.println(item.getName()));
	}
}
