package com.scream.study.spring.ioc.lesson2.ioc_registry_method.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * create by: scream
 * create time: 2020/1/26 15:42
 * description: 自定义注册条件 当@Condition()注解引用本类时@Condition(MyConditionV2.class) 那么是否注册到容器的决定权就移交到了 matches方法
 */
public class MyConditionV2 implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//获取当前的运行环境
		Environment environment = context.getEnvironment();
		// win7 win8 win10 linux 苹果系统MAC
		String sys_name = environment.getProperty("os.name");
		//win7环境才注册到上下文中
		if (sys_name.equals("Windows 10")) {
			return true;
		}
		return false;
	}
}
