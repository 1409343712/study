package com.scream.study.thread.lock;

public class LockTest {
	private static  int i = 0;
	//对象锁
	public synchronized void test() {
		System.out.println(Thread.currentThread().getName()+":"+"获得资源");
		while(i<100) {
			System.out.println(Thread.currentThread().getName()+":" + i++);
		}
		System.out.println(Thread.currentThread().getName()+":"+"释放资源");
	}
	//类锁
	public static synchronized void test1() {
		System.out.println(Thread.currentThread().getName()+":"+"获得资源");
		while(i<100) {
			System.out.println(Thread.currentThread().getName()+":" + i++);
		}
		System.out.println(Thread.currentThread().getName()+":"+"释放资源");
	}
	//调用对象锁
	public void objectLock() {
		final LockTest mythread = new LockTest();
		final LockTest mythread1 = new LockTest();
		Thread test1 = new Thread(new Runnable() {public void run() {mythread.test();}}, "test1");
		Thread test2 = new Thread(new Runnable() {public void run() {mythread1.test();}}, "test2");
		test1.start();
		test2.start();
	}
	//调用类锁
	public void classLock() {
		Thread test1 = new Thread(new Runnable() {public void run() {LockTest.test1();}}, "test1");
		Thread test2 = new Thread(new Runnable() {public void run() {LockTest.test1();}}, "test2");
		test1.start();
		test2.start();
	}

	public static void main(String[] args) {
		LockTest test = new LockTest();
		//测试对象锁
		test.objectLock();
		//测试类锁
//		test.classLock();
	}
	/**
	 *总结：一个类的所有方法都是线程安全的并不代表这个类就是线程安全的，因为对象锁无法阻止其他对象去获取资源
	 */
}
