package com.scream.study.spring.ioc.lesson1.interfaces;

import org.springframework.stereotype.Component;

/**
 * create by: scream
 * create time: 2020/2/14 17:52
 * description: 接口上加@Component并不会讲将接口注册到IOC容器中
 */
@Component("lesson1Interface")
public interface Lesson1Interface {
	void test1();
}
