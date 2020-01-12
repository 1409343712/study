package com.scream.study.java8;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
	@Test
	public void ifPresent() {
		String str = "我是Optional";
		if (str != null) {
			System.out.println(str);
		}
		Optional.ofNullable(str).ifPresent(System.out::println);
	}

	@Test
	public void orElse() {
		String str = "我是Optional";
		str = Optional.ofNullable(str).orElse("xxx");
		System.out.println(str);
	}

	@Test
	public void map() {
		String str = null;
		Integer num;
//		if (str != null) {
//			num = Integer.parseInt(str);
//		} else {
//			num = 0;
//		}
		num = Optional.ofNullable(str).map(Integer::parseInt).orElse(100);
		System.out.println(num);
	}
}
