package com.scream.study.spring.ioc.lesson2.ioc_registry_method.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.scream.study.spring.ioc.lesson2.entity.Windows7EntityV2"};
	}
}
