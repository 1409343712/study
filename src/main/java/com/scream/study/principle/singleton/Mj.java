package com.scream.study.principle.singleton;

/**
 * 
 * 写法简单，简洁明了
 * JDK定义枚举类的构造方法为private的。每个枚举实例都是static final类型的，也就表明只能被实例化一次。
 * 创建枚举默认就是线程安全的，所以不必担心线程问题
 * enum是来自于Enum类的，通过JDK我们可以看到Enum类的构造，可以看到枚举类提供了序列化机制,其能够阻止默认的反序列化 Enum.readObject Enum.readObjectNoData
 *反射类中的newInstance方法中，禁止对枚举进行实例化，详见 Class.newInstance()442行,Constructor类416行
 * 
 * 
 * 
 * @author 银娣
 *
 */
public enum Mj {
	INSTANCE;
	private Mj() {
		System.out.println("枚举构造方法");
	}
	public void doSomething() {
		
	}
	public static void main(String[] args) {
			Class clazz = Mj.class;
			try {
				Mj mj1 = (Mj)clazz.newInstance();
				Mj mj2 = (Mj)clazz.newInstance();
				System.out.println(mj1.hashCode());
				System.out.println(mj2.hashCode());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
