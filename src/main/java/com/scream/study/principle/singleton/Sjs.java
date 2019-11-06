package com.scream.study.principle.singleton;

/**
 * 双检索式写法
 * 优点：按需加载，避免出现饿汉式写法的资源浪费
 * 缺点：可以通过反射破解
 * @author 银娣
 *
 */
public class Sjs {

	private static Sjs instance = null;
	private Sjs() {
		
	}
	public static Sjs getInstance() {
		//如果instance不是null则不会被锁住，提高效率
		if(instance ==null) {
			synchronized (Sjs.class) {
				if(instance == null) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					instance = new Sjs();
//					System.out.println("创建新对象");
				}
			}
		}
		return instance;
	}
	public static void main(String[] args) {
		new PoJieSjs().start();
		new PoJieSjs().start();
		new PoJieSjs().start();
		new PoJieSjs().start();
		new PoJieSjs().start();
	}

}
/**
 * 破解写法 证明双检索单例 线程安全
 * @author 银娣
 *
 */
 class PoJieSjs extends Thread{
	@Override
	public void run() {
		Sjs instance = Sjs.getInstance();
		System.out.println(instance);
	}
	
}
