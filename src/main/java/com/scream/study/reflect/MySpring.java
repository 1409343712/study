package com.scream.study.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射模拟spring IOC注入
 * @author 银娣
 *
 */
public class MySpring {
	public static void main(String[] args) {
		ioc("designprinciples.reflect.Person");
	}
	private static void ioc(String classPath) {
		try {
			Class<?> clazz = Class.forName(classPath);
			Object obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				String methodName = "set".concat(fieldName.substring(0,1).toUpperCase()).concat(fieldName.substring(1, fieldName.length()));
				if(containMethod(clazz, methodName)) {
					Class<?> fieldClass = field.getType();//获取属性类型
					System.out.println(fieldClass.getName());
					Constructor<?> fieldCon = fieldClass.getDeclaredConstructor(String.class); //调用参数类型的带有String的构造方法 八个包装类7个都有带String参数的构造方法 除了char
					Method method = clazz.getDeclaredMethod(methodName, fieldClass);//通过反射拿到方法
					method.setAccessible(true);//设置方法可以被访问
					Object newField = fieldCon.newInstance("1111");
					System.out.println(newField instanceof String);
					method.invoke(obj, newField);//反射调用方法
				}
			}
			for (Field field : fields) {
				String fieldName = field.getName();
				String methodName = "get".concat(fieldName.substring(0,1).toUpperCase()).concat(fieldName.substring(1, fieldName.length()));
				if(containMethod(clazz, methodName)) {
					Method method = clazz.getDeclaredMethod(methodName);//通过反射拿到方法
					Object returnValue = method.invoke(obj);
					System.out.println(fieldName + "-" + returnValue.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static boolean containMethod(Class clazz, String methodName) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if(methodName.equals(method.getName())) {
				return true;
			}
		}
		return false;
	}

}
