package com.scream.study.java8;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReferenceTest {


	@Test
	public void staticMethod() {
		Consumer<String> consumer = System.out::println;
		consumer.accept("博哥最爱artery");
	}

	@Test
	public void instanceMethod() {
		String value = "java太难了";
		Predicate<String> predicate = value::startsWith;
		System.out.println(predicate.test("xj"));
	}

	@Test
	public void thisMethod() {
		String value = "java太难了";
		Predicate<String> predicate = this::helperMethod;
		System.out.println(predicate.test(value));
	}

	private boolean helperMethod(String value) {
		return value.startsWith("x");
	}
	@Test
	public void constructorMetod(){
		Supplier<Object> supplier = Object::new;
		System.out.println(supplier.get());
	}
}
