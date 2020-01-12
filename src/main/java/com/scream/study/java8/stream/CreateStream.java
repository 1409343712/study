package com.scream.study.java8.stream;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateStream {

	/**
	 * 有限流
	 */
	@Test
	public void streamOf() {
		Stream<String> stream = Stream.of("A", "B", "C");
		stream.forEach(System.out::println);
	}

	@Test
	public void arrays() {
		String[] arrays = {"A", "B", "C"};
		Stream<String> stream = Arrays.stream(arrays);
		stream.forEach(System.out::println);
	}

	@Test
	public void list() {
		String[] arrays = {"A", "B", "C"};
		Stream<String> stream = Arrays.asList(arrays).stream();
		stream.forEach(System.out::println);
	}

	@Test
	public void set() {
		String[] arrays = {"A", "B", "C"};
		Stream<String> stream = new HashSet<>(Arrays.asList(arrays)).stream();
		stream.forEach(System.out::println);
	}

	@Test
	public void map() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "A");
		map.put("2", "B");
		map.put("3", "C");
		map.put("1", "A");
		map.put("2", "B");
		map.put("3", "C");
		Stream<String> stream = map.values().stream();
		stream.forEach(System.out::println);
	}
	/**
	 * 无限流
	 */
	@Test
	public void genarate(){
		Random random = new Random();
		Stream<Double> doubleStream = Stream.generate(random::nextDouble).limit(100);
		doubleStream.forEach(System.out::println);
	}
	@Test
	public void pattern(){
		String value = "A B C 1 2 3";
		Stream<String> stream = Pattern.compile("\\D").splitAsStream(value);
		System.out.println(stream.collect(Collectors.joining()));
	}
	@Test
	public void lines() throws Exception{
		Stream<String> stream = Files.lines(Paths.get("D://stream.txt"));
		stream.forEach(System.out::println);
	}
}
