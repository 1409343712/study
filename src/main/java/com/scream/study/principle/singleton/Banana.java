package com.scream.study.principle.singleton;


/**
 * @author Administrator
 * @date 2019/3/17
 */
public class Banana {
	//私有化构造函数
	private Banana(){ }

	//定义一个静态枚举类
	static enum SingletonEnum{
		//创建一个枚举对象，该对象天生为单例
		INSTANCE;
		private Banana banana;
		//私有化枚举的构造函数
		private SingletonEnum(){
			banana =new Banana();
		}
		public Banana getInstnce(){
			return banana;
		}
	}

	//对外暴露一个获取MjHelper对象的静态方法
	public static Banana getInstance(){
		return SingletonEnum.INSTANCE.getInstnce();
	}
}
