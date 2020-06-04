package com.scream.study.java8.lambda;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/**
 * create by: scream
 * create time: 2020/2/9 15:43
 * description: JAVA8 四大核心函数式接口
 * Consumer<T>：消费型接口（void accept(T t)）
 * Supplier<T>：供给型接口（T get（））
 * Function<T, R>：函数型接口（R apply（T t））
 * Predicate<T>：断言型接口（boolean test（T t））
 */
public class InnerLambdaTest {
	private static final Consumer<Object> consumer = System.out::println;

	@Test
	public void testConsmer() {
		consumer.accept("Hello World");
	}

	@Test
	public void testConsumerJdk() {
		List<Integer> numList = Arrays.asList(2, 3, 1, 4, 5);
		Consumer<Integer> consumer = System.out::println;
		numList.stream().forEach(consumer);
	}

	@Test
	public void testFunction() {
		Function<String, Integer> convert = Integer::valueOf;
		Function<String, Double> after = convert.andThen(Double::valueOf);
		consumer.accept(after.apply("111"));
	}

	@Test
	public void testFunctionJdk() {
		List<Integer> numList = Arrays.asList(2, 3, 1, 4, 5);
		Function<Integer, Double> convert = Double::valueOf;
		numList.stream().map(convert).forEach(System.out::println);
	}

	@Test
	public void testPredicate() {
		Predicate<String> predicate = StringUtils::isEmpty;
		consumer.accept(predicate.test("1"));
	}

	@Test
	public void testPredicateJdk() {
		List<String> charList = Arrays.asList("2", "3", "1", "4", "5");
		Predicate<String> predicate = StringUtils::isNotEmpty;
		consumer.accept(charList.stream().filter(predicate).collect(Collectors.toList()));
	}

	@Test
	public void testSuppier() {
		Supplier<Object> supplier = Object::new;
		consumer.accept(supplier.get());
	}

	@Test
	public void testSuppierJdk() {
		Supplier<Object> supplier = Object::new;
		consumer.accept(Optional.ofNullable(null).orElseGet(supplier));
	}
}