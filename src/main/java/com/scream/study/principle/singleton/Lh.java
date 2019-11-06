package com.scream.study.principle.singleton;

/**
 * 懒汉式写法
 * 优点：按需加载，避免出现饿汉式写法的资源浪费
 * 缺点：线程不安全
 * @author 银娣
 *
 */
public class Lh {
	private static Lh instance = null;
	private Lh() {
		
	}
	public static Lh getInstance() {
		if(instance == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			instance = new Lh();
//			System.out.println("创建新对象");
		}
	
		return instance;
	}
	public static void main(String[] args) {
		new PoJieLh().start();
		new PoJieLh().start();
		new PoJieLh().start();
		new PoJieLh().start();
		new PoJieLh().start();
	}
}
/**
 * 破解写法 证明懒汉式单例 线程不安全
 * @author 银娣
 *
 */
class PoJieLh extends Thread{
	@Override
	public void run() {
		Lh instance = Lh.getInstance();
		System.out.println(instance);
	}
	
}