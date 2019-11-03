package com.scream.study.reflect;

import java.lang.reflect.Field;

/**
 * Class 用来描述类本身
 * Package 用来描述类所属的包
 * Field 用来描述类中的属性
 * Method 用来描述类中的方法
 * Constructor 用来描述类中的构造方法
 * Annotation 用来描述类中的注解
 * @author Administrator
 *
 */
public class ReflectField {
	public static void main(String[] args) {
		test2();
	}
	/**
	 * 操作属性1
	 * clazz.getField("name") 和 clazz.getFields() 会获取到全部public属性（包括父类的属性）
	 *如果属性是非public修饰的 则会报java.lang.NoSuchFieldException
	 */
	private static void test1() {
		try {
			Class clazz = Person.class;
			Person p = (Person)clazz.newInstance();
			//获取属性
			Field field = clazz.getField("name");// 获取属性Field
			int modifiers = field.getModifiers();//获取属性修饰符
			Class typeClass = field.getType();// 获取属性类型
			String fieldName = field.getName();//获取属性名
			System.out.println(modifiers);
			System.out.println(typeClass);
			System.out.println(fieldName);
			field.set(p, "富二代");//属性赋值
			System.out.println(field.get(p));
			
			Field[] fieldArray = clazz.getFields();
			for (Field eachField : fieldArray) {
				System.out.println(eachField.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 操作属性2
	 * clazz.getDeclaredField("name") 和 clazz.getDeclaredFields(); 能获取到所有属性（不包括父类）
	 * 如果属性是非public修饰的 那么必须field.setAccessible(true) 不然会报java.lang.IllegalAccessException
	 */
	private static void test2() {
		try {
			Class clazz = Person.class;
			Person p = (Person)clazz.newInstance();
			Field field = clazz.getDeclaredField("name");
			field.setAccessible(true);//设置非public属性可以被操作
			field.set(p, "砕碎");
			String value = (String)field.get(p);
			System.out.println(value);
			Field[] fieldArray = clazz.getDeclaredFields();
			for (Field eachField : fieldArray) {
				eachField.setAccessible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 本例子说明 反射可以将属性的private改为public 但是不能修改final特性
	 * 例如 String 的value属性 只能修改数组里面的值
	 * String.class line 114
	 */
	private static void test3() {
		try {
			String before = "原来叫砕碎";
			Class clazz = before.getClass();
			Field field = clazz.getDeclaredField("value");
			field.setAccessible(true);
//			field.set(before, "后来叫砕碎"); 这样写会报错 因为String的value属性是final修饰的
			char[] tmp = (char[])field.get(before);
			tmp[0] = '后';
			System.out.println(before);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
