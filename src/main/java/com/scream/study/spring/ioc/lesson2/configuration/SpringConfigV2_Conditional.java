package com.scream.study.spring.ioc.lesson2.configuration;

import com.scream.study.spring.ioc.lesson2.entity.Windows7EntityV2;
import com.scream.study.spring.ioc.lesson2.ioc_registry_method.condition.MyConditionV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

//@Configuration
public class SpringConfigV2_Conditional {

	@Bean("xxx")//如果注解处指定了name 注册到哦容器的的ID就是xxx而不是windows7EntityV2（方法名）
	//自定义注册条件 条件满足才注册到Spring上下文中
	@Conditional(MyConditionV2.class)
	public Windows7EntityV2 windows7EntityV2() {
		return new Windows7EntityV2();
	}
}
