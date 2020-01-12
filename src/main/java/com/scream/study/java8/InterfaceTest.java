package com.scream.study.java8;

import org.junit.Test;

public class InterfaceTest {

	@Test
	public void test(){
		Animal qiuqiu = new Qiuqiu();
		qiuqiu.eat();
		qiuqiu.drink();
		Animal.fadeing();
	}

}

@FunctionalInterface
interface  Animal{
	void eat();
	default  void drink(){
		System.out.println("喝水");
	}
	static void fadeing(){
		System.out.println("正在老去");
	}
}

class Qiuqiu implements Animal{
	@Override
	public void eat() {
		System.out.println("吃猫粮，贼贵");
	}

	@Override
	public void drink() {
		System.out.println("喝饮料");
	}
}