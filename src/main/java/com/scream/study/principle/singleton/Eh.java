package com.scream.study.principle.singleton;

/**
 * 饿汉式写法 
 * 优点：static声明实例保证线程安全，
 * 缺点1：可以通过java反射机制进行破解
 * 缺点2：程序启动就会加载对象，会造成资源的浪费
 * @author 银娣
 *
 */
public class Eh {
	private static final Eh instance = new Eh();
	private Eh() {
		
	}
	public static Eh getInstance() {
		return instance;
	}
	//反射破解单例模式
	public static void main(String[] args) {
		Class clazz = Eh.class;
		try {
			Eh eh1 = (Eh)clazz.newInstance();
			Eh eh2 = (Eh)clazz.newInstance();
			System.out.println(eh1.hashCode());
			System.out.println(eh2.hashCode());
			Eh eh3 = Eh.getInstance();
			Eh eh4 = Eh.getInstance();
			System.out.println(eh3.hashCode());
			System.out.println(eh4.hashCode());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}