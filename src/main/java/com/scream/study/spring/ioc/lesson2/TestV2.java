package com.scream.study.spring.ioc.lesson2;

import com.scream.study.spring.ioc.lesson2.configuration.*;
import com.scream.study.spring.ioc.lesson2.entity.Windows7EntityV2;
import com.scream.study.spring.ioc.lesson2.ioc_registry_method.primary_qualifier_resource.QuoteService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class TestV2 {
	private AnnotationConfigApplicationContext annotationConfigApplicationContext;

	@Before
	public void boefore() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfigV2_Conditional.class);
		System.out.println("V2上下文加载完毕...");
	}

	@Test
	public void testCondition() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfigV2_Conditional.class);
		String[] idArrays = annotationConfigApplicationContext.getBeanDefinitionNames();
		Arrays.asList(idArrays).forEach(System.out::println);
	}
	@Test
	public void testImport() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfigV2_Import.class);
		String[] idArrays = annotationConfigApplicationContext.getBeanDefinitionNames();
		Arrays.asList(idArrays).forEach(System.out::println);
	}
	@Test
	public void testImportSelector() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfigV2_ImportSelector.class);
		String[] idArrays = annotationConfigApplicationContext.getBeanDefinitionNames();
		Arrays.asList(idArrays).forEach(System.out::println);
	}
	@Test
	public void testAnnotation() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfigV2_Annotation.class);
		String[] idArrays = annotationConfigApplicationContext.getBeanDefinitionNames();
		Arrays.asList(idArrays).forEach(System.out::println);
	}
	@Test
	public void testBeanDefinitionRegistry() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfigV2_BeanDefinitionRegistry.class);
		String[] idArrays = annotationConfigApplicationContext.getBeanDefinitionNames();
		Arrays.asList(idArrays).forEach(System.out::println);
	}
	@Test
	public void testFactoryBean() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfigV2_FactoryBean.class);
		//这里的com.scream.spring.lesson2.ioc_registry_method.factorybean.MyFactoryBean其实就是注册成了Windows7EntityV2
		Windows7EntityV2 entity1 = annotationConfigApplicationContext.getBean("com.scream.study.spring.ioc.lesson2.ioc_registry_method.factorybean.MyFactoryBean", Windows7EntityV2.class);
		Windows7EntityV2 entity2 = annotationConfigApplicationContext.getBean("com.scream.sstudy.pring.ioc.lesson2.ioc_registry_method.factorybean.MyFactoryBean", Windows7EntityV2.class);
		System.out.println(entity1 == entity2);
	}
	@Test
	public void testPrimary() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfigV2_Primary.class);
		QuoteService quoteService = annotationConfigApplicationContext.getBean("quoteService", QuoteService.class);
		quoteService.testAdd();
	}
	@Test
	public void printIds() {
		String[] idArrays = annotationConfigApplicationContext.getBeanDefinitionNames();
		Arrays.asList(idArrays).forEach(System.out::println);
	}
}
