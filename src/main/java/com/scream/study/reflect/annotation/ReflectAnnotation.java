package com.scream.study.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectAnnotation {
	public static void main(String[] args) {
		test2();
	}
	
	/**
	 * 解析PerxonX类中上面的注解信息
	 */
//	@SuppressWarnings("all")
	private static void test1() {
		Class clazz = PersonX.class;
		try {
			Field[] fieldArray = clazz.getDeclaredFields();
			for (Field field : fieldArray) {
				field.setAccessible(true);
				AnnotationX annoX = field.getAnnotation(AnnotationX.class);
				String[] valueArray = annoX.value();
				for (String value : valueArray) {
					System.out.println(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void test2() {
		Class clazz = PersonX.class;
		try {
			Field[] fieldArray = clazz.getDeclaredFields();
			for (Field field : fieldArray) {
				field.setAccessible(true);
				Annotation anno = field.getAnnotation(AnnotationX.class);
				//利用反射执行anno中的value方法
				Class annoClass = anno.getClass();
				Method method = annoClass.getMethod("value");
				String[] valueArray = (String[])method.invoke(anno);
				for (String value : valueArray) {
					System.out.println(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
