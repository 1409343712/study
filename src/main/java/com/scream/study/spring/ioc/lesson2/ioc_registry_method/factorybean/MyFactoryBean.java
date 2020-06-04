package com.scream.study.spring.ioc.lesson2.ioc_registry_method.factorybean;

import com.scream.study.spring.ioc.lesson2.entity.Windows7EntityV2;
import org.springframework.beans.factory.FactoryBean;

/**
 * create by: scream
 * create time: 2020/1/27 23:32
 * description: FactoryBean：向IOC容器注册对象 BeanFacroty：从IOC容器获取对象
 */
public class MyFactoryBean implements FactoryBean<Windows7EntityV2> {
	@Override
	public Windows7EntityV2 getObject() throws Exception {
		return new Windows7EntityV2();
	}

	@Override
	public Class<?> getObjectType() {
		return Windows7EntityV2.class;
	}

	/**
	 * 默认单例 true
	 * @return
	 */
	@Override
	public boolean isSingleton() {
		return false;
	}
}
