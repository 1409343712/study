package com.scream.study.spring.ioc.lesson2.ioc_registry_method.enable;

import com.scream.study.spring.ioc.lesson2.entity.Windows7EntityV2;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(Windows7EntityV2.class)//引入@EnableEntity注解将自动将Windows7EntityV2注册到spring容器中 ID=类全路径
public @interface EnableEntity {
	String value() default "";
}
