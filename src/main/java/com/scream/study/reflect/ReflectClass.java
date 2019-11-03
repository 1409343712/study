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
public class ReflectClass {
	public static void main(String[] args) {
		test1();
	}
	/**
	 * 获取Class的三种方式
	 */
	private static void test1() {
		try {
			Class clazz1 = Class.forName("designprinciples.reflect.Person");//第一种（包名.类名）
			System.out.println(clazz1);
			Class clazz2 = Person.class;//第二种 （类名.class）
			System.out.println(clazz2);
			Person reflect = new Person();
			Class clazz3 = reflect.getClass();//第三种 （对象.getClass()） 继承自Object
			System.out.println(clazz3);
			Person xx = (Person)clazz1.newInstance();//相当于调用无参构造方法 如果构造方法有参数则抛出nosuchmethodException
			System.out.println(xx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取父类和接口的方法
	 */
	private static void test2() {
		Class<Thread> clazz = Thread.class;
		Class<? super Thread> superClass = clazz.getSuperclass();//获取继承父类
		System.out.println(superClass);
		Class [] interfaceArray = clazz.getInterfaces();//获取实现的接口
		if(interfaceArray != null) {
			for (Class each : interfaceArray) {
				System.out.println(each);
			}
		}
	}
	/**
	 * 类有自己结构 权限修饰符 特征修饰符 类名字 继承 实现
	 */
	private static void test3() {
		Class clazz = Person.class;
		//获取类的修饰符（权限 特征） 每一个修饰符 用一个整数来表示
		// 0默认不写 1--public 2--private 4--protected 8--static 16--final 32--sychronized 
		// 64--volatile 128--transient 256--native 512--interface 1024--abstract
		int modifiers = clazz.getModifiers();
		System.out.println(modifiers);
		
		String name = clazz.getName();//获取类的名字（带包名）
		String simpleName = clazz.getSimpleName();//获取类的名字（不带包名）
		System.out.println(name + " -- " + simpleName);
		
		Package nowPackage = clazz.getPackage();//获取类所在的包
		System.out.println(nowPackage.getName());//获取包名
		
	}
}
