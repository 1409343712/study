package com.scream.study.reflect;

import java.lang.reflect.Method;

public class ReflectMethod {
	public static void main(String[] args) {
		testInvoke1();
	}

	/**
	 * getMethod 与  getMethods 会获取到全部public方法（包括父类的方法）
	 * 如果方法是非public修饰 则会报java.lang.NoSuchMethodException
	 */
	private static void testInvoke(){
		try {
			Class clazz = Person.class;
			Person p = (Person)clazz.newInstance();
			// 获取Person类无参数的testInvoke方法
			Method method1 = clazz.getMethod("testInvoke");
			//获取异常类型
			Class[] exArray = method1.getExceptionTypes();
		    String returnValue1 = (String)method1.invoke(p);
		    System.out.println(returnValue1);
		    // 获取Person类有参数的testInvoke方法
		    Method method2 = clazz.getMethod("testInvoke", String.class);
		    String returnvValue2 = (String)method2.invoke(p, "这是有参数的testInvoke方法");
		    System.out.println(returnvValue2);
		    // 获取全部方法
		    Method[] methodArray = clazz.getMethods();
		    for (Method method : methodArray) {
				if(method.getName().equals("testInvoke") ) {
					// 无参数方法反射
					if(method.getParameterTypes().length == 0) {
						String eachReturnValue = (String)method.invoke(p);
						System.out.println(eachReturnValue);
					} else {
						
					}
					//有参数方法反射
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * getDeclaredMethod 与  getDeclaredMethods 会获取到全部方法（不包括父类的方法）
	 * 如果方法是非public修饰的 那么必须method.setAccessible(true) 不然会报java.lang.IllegalAccessException
	 */
	private static void testInvoke1(){
		try {
			Class clazz = Person.class;
			Person p = (Person)clazz.newInstance();
			// 获取Person类无参数的testInvoke方法
			Method method1 = clazz.getDeclaredMethod("testInvoke");
			//非public修饰
			if(method1.getModifiers() != 1) {
				method1.setAccessible(true);//设置非public方法可以被操作
			}
		    String returnValue1 = (String)method1.invoke(p);
		    System.out.println(returnValue1);
		    // 获取Person类有参数的testInvoke方法
		    Method method2 = clazz.getDeclaredMethod("testInvoke", String.class);
			if(method2.getModifiers() != 1) {
				method2.setAccessible(true);
			}
		    String returnvValue2 = (String)method2.invoke(p, "这是有参数的testInvoke方法");
		    System.out.println(returnvValue2);
		    // 获取全部方法
		    Method[] methodArray = clazz.getDeclaredMethods();
		    for (Method method : methodArray) {
		    	method.setAccessible(true);
				if(method.getName().equals("testInvoke") ) {
					// 无参数方法反射
					if(method.getParameterTypes().length == 0) {
						String eachReturnValue = (String)method.invoke(p);
						System.out.println(eachReturnValue);
					} else {
						
					}
					//有参数方法反射
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
