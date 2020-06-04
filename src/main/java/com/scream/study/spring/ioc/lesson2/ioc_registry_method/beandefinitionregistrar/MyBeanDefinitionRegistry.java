package com.scream.study.spring.ioc.lesson2.ioc_registry_method.beandefinitionregistrar;

import com.scream.study.spring.ioc.lesson2.entity.Windows7EntityV2;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar {
	/**
	 * create by: scream
	 * create time: 2020/1/27 20:19
	 * description: TODO
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		/**
		 * 手动注册
		 */
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Windows7EntityV2.class);
		registry.registerBeanDefinition("wbdz", rootBeanDefinition);
	}
}
