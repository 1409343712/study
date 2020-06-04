package com.scream.study.java8.lambda;

import org.junit.After;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaTest {
	private List<Integer> numList = Arrays.asList(3,2,5,1,4);

	@Test
	public void noLambda(){
		//1.7写法
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1111111);
			}
		});
		//1.8写法
		t = new Thread(() ->System.out.println(111));


		Collections.sort(numList, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
	}

	/**
	 * Lambada 基础语法 () -> {}
	 */
	@Test
	public void lambda1(){
		Collections.sort(numList, (Integer o1, Integer o2) -> {
			return o1.compareTo(o2);
		});
	}
	@Test
	public void lambda2(){
		Collections.sort(numList, (o1, o2) -> {
			return o1.compareTo(o2);
		});
	}
	@Test
	public void lambda3(){
		Collections.sort(numList, (o1, o2) -> o1.compareTo(o2));
	}

	@Test
	public void lambda5(){
		Collections.sort(numList, Integer::compareTo);
	}

	/**
	 * 思考题：这是个什么玩楞？？？？？
	 * 答案： 6Jm954S2TGFtYmFkYeihqOi+vuW8j+aYr+WvueWHveaVsOW8j+aOpeWPo+eahOeugOWMluWGmeazle+8jOS9huaYr+aIkeWwseaDs+S8oOmAkuS4gOS4quato+W4uOeahOWPguaVsO+8jOS4jeihjOS5iO+8nw==
	 */
	@Test
	public void lambda4(){
		Collections.sort(numList, Comparator.naturalOrder());
	}
	@After
	public void show(){
		System.out.println(numList);
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
	{
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	//使用举例
//    persons.stream().filter(distinctByKey(Person::getName))
}
