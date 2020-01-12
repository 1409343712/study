package com.scream.study.java8;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	private Map<String, String> helpMap = new HashMap<>();

	@Before
	public void fillMap() {
		helpMap.put("1", "1");
		helpMap.put("2", "2");
		helpMap.put("3", "3");
		helpMap.put("4", "4");
		helpMap.put("5", "5");
	}

	/**
	 * 存在 返回key对应的value
	 * 不存在 返回默认值
	 */
	@Test
	public void getOrDefault() {
		String defaultValue = "未找到";
		System.out.println(helpMap.getOrDefault("1", defaultValue));
		System.out.println(helpMap.getOrDefault("6", defaultValue));
	}

	/**
	 * 迭代
	 */
	@Test
	public void forEach() {
		helpMap.forEach((key, value) -> System.out.println(String.format("key:%s-value:%s", key, value)));
	}

	/**
	 * 存在：替换新增 返回旧值
	 * 不存在：返回null
	 */
	@Test
	public void replace() {
		String before = helpMap.replace("1", "xxx");
		System.out.println(before);
		before = helpMap.replace("6", "xxx");
		System.out.println(before);

	}

	/**
	 * 将所有的value替换为 BiFunction.supply()的返回值
	 */
	@Test
	public void replaceAll() {
		helpMap.replaceAll((key, value) -> {
//			if(key.contains("非法字符") || value.contains("非法字符")){
//				return "合法字符";
//			}
			return value + "xxx";
		});
		System.out.println(helpMap);
	}

	/**
	 * 不存在才替换 返回处理之前的值
	 */
	@Test
	public void putIfAbsent() {
		String before = helpMap.putIfAbsent("1", "x");
		System.out.println(before);
		before = helpMap.putIfAbsent("6", "6");
		System.out.println(before);
		System.out.println(helpMap);
	}

	/**
	 * 不存在才替换 返回Function.apply()
	 */
	@Test
	public void computeIfAbsent() {
		String after = helpMap.computeIfAbsent("6", (key) -> "supply()之后的值");
		System.out.println(after);
		System.out.println(helpMap);
	}
	/**
	 * 将key对应的vaue替换为Function.apply()的返回值，如果返回值是null 那么直接remove
	 * 返回Function.apply()
	 */
	@Test
	public void compute() {
		String resut = helpMap.compute("1", (key, value) -> "supply()之后的值");
		System.out.println(resut);
		System.out.println(helpMap);
		resut = helpMap.compute("1", (key, value) -> null);
		System.out.println(resut);
		System.out.println(helpMap);
	}

	/**
	 * value = 存在key ? Function.supply() : newValue
	 * if value == null 替换key的值为value
	 * else 直接remove key
	 * return value
	 */
	@Test
	public void merge() {
		String result = helpMap.merge("1", "newValue",(key, value) -> "supply()之后的值");
		System.out.println(result);
		System.out.println(helpMap);
		result = helpMap.merge("6", "newValue",(key, value) -> "supply()之后的值");
		System.out.println(result);
		System.out.println(helpMap);
	}
}
