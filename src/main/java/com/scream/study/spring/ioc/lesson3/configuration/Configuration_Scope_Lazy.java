package com.scream.study.spring.ioc.lesson3.configuration;

import com.scream.study.spring.ioc.lesson3.entity.EntityLesson3;
import org.springframework.context.annotation.Bean;

//@Configuration
//@ComponentScan("com.scream.study.spring.ioc.lesson3")
public class Configuration_Scope_Lazy {

	@Bean( name = "lesson2_EntityLesson3", initMethod = "initMethod", destroyMethod = "destroyMethod")
	public EntityLesson3 entityLesson3() {
		return new EntityLesson3();
	}
}
