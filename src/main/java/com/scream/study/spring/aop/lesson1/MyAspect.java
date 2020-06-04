package com.scream.study.spring.aop.lesson1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * create by: scream
 * create time: 2020/2/1 23:58
 * description: @Aspect：切面类 @Component：切面类需要交给IOC容器进行管理 @EnableAspectJAutoProxy：开启切面
 * Around与afterThrowing不共存 Around优先级更高
 * Around与@AfterReturning共存时 AfterReturning无法获取返回值
 */
////@Aspect
//@Component
//@EnableAspectJAutoProxy
public class MyAspect {
	//定义切入点(AOP要切入哪些地方)
	@Pointcut("execution (* com.scream.spring.aop.lesson1..*.*(..))")
	//对com.scream.spring.aop.lesson1下的所有类、所有方法、所有参数进行拦截
	public void pointCut() {

	}

	/**
	 * 前置通知
	 */
//	@Before("pointCut()")
//	public void doBefore(JoinPoint joinPoint) {
//		System.out.println(">>>>>>>前置通知<<<<<<<<<<< ");
//	}

	/**
	 * 后置通知
	 */
//	@After("pointCut()")
//	public void doAfter(JoinPoint joinPoint) {
//		System.out.println(">>>>>>>>后置通知<<<<<<<<<");
//	}

	/**
	 * 环绕通知
	 */
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		String methodName = methodSignature.getMethod().getName();
		System.out.println(methodName + ">>>>环绕通知之前执行...>>>>>>");
		Object obj = joinPoint.proceed();// 执行目标方案 并拿到返回值
		System.out.println(methodName + ">>>>环绕通知之后执行...>>>>>>");
//		return "1111";//串改返回值
		return obj;
	}

	/**
	 * 运行通知 可以拿到返回值
	 */
//	@AfterReturning(value = "pointCut()" ,returning = "returnValue")
//	public void afterReturning(JoinPoint joinPoint, Object returnValue) throws Throwable {
//		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//		String methodName = methodSignature.getMethod().getName();
//		System.out.println(methodName + "方法返回：" + returnValue);
//	}

	/**
	 * 异常通知
	 *
	 * @param joinPoint
	 */
	@AfterThrowing("pointCut()")
	public void afterThrowing(JoinPoint joinPoint) {
		System.out.println(">>>>>异常通知");
	}
}
