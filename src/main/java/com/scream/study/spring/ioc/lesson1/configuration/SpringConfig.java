package com.scream.study.spring.ioc.lesson1.configuration;

import com.scream.study.spring.ioc.lesson1.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * create by: scream
 * create time: 2020/1/23 1:53
 * description: @ComponentScan注解 value指定的路径下的 @Service @Component ...等 会被注册到IOC容器中
 * value：指定扫描范围
 * includeFilters：包含的范围
 * excludeFilters: 排除的范围
 */

@Configuration
@ComponentScan("com.scream.study.spring.ioc.lesson1")
//@ComponentScan(value = "com.scream.spring", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class)}, useDefaultFilters = false)//只扫描 com.scream.spring 包下 @Service注解的类
//@ComponentScan(value = "com.scream.spring", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class)}, useDefaultFilters = true)//不扫描 com.scream.spring 包下 @Service注解的类
public class SpringConfig {
//	@Configuration 等同于 applicationContext.xml

	/**
	 * create by: scream
	 * create time: 2020/1/22 22:43
	 * description: 通过Configuration + @Bean 注册User
	 *
	 * @return com.scream.spring.ioc.lesson1.entity.UserEntity
	 * @Bean 等同于 <bean class="com.scream.spring.ioc.lesson1.entity.UserEntity" id="方法名称：user"></bean>
	 * 注意：一般在引入外部项目的类的时候才会使用@Configuration 例如：引入druid
	 */
	@Bean
	public UserEntity userEntity() {
		return new UserEntity("刘冶", 21);

	}
}
