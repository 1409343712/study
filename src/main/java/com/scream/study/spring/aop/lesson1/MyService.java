package com.scream.study.spring.aop.lesson1;

import org.springframework.stereotype.Service;

@Service("myService")
public class MyService {
	private static  final String RESULT = "xxx";
	public String test() {
//		System.out.println(1/0);
		System.out.println("RESULT");
		return RESULT;
	}

}
