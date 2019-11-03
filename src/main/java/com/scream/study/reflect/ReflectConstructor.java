package com.scream.study.reflect;

import java.lang.reflect.Constructor;

public class ReflectConstructor {
	public static void main(String[] args) {
		test2();
	}

	/**
	 * clazz.getConstructor() | clazz.getConstructor(Class<?>... parameterTypes) 只能获取当前类的public构造方法
	 * 非public构造方法会报java.lang.NoSuchMethodException
	 */
	private static void test1(){
		Class clazz = Person.class;
		try {
			Constructor con = clazz.getConstructor();//获取无参构造函数
			Person p = (Person)con.newInstance();
			Constructor con1 = clazz.getConstructor(String.class);//获取有参构造函数
			Person p1 = (Person)con1.newInstance("xxx");
			Constructor[] conArray = clazz.getConstructors();//拿到所有构造函数
			for (Constructor constructor : conArray) {
				System.out.println(constructor.getParameterTypes().length);
				System.out.println(constructor.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * clazz.getDeclaredConstructor() | clazz.getDeclaredConstructor(Class<?>... parameterTypes) 会获取当前类的所有构造方法
	 * 如果构造方法是非public修饰的 那么必须Constructor.setAccessible(true) 不然会报java.lang.IllegalAccessException
	 */
	private static void test2(){
		Class clazz = Person.class;
		try {
			Constructor<Person> con = clazz.getDeclaredConstructor();
			con.setAccessible(true);
			con.newInstance();
			Constructor<Person> con1 = clazz.getDeclaredConstructor(String.class);
			con1.setAccessible(true);
			con1.newInstance("xxx");
			Constructor[] conArray = clazz.getDeclaredConstructors();
			for (Constructor constructor : conArray) {
				constructor.setAccessible(true);
				System.out.println(constructor.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
