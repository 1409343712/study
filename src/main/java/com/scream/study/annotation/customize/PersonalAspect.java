package com.scream.study.annotation.customize;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
/**
 * Around与afterThrowing不共存 Around优先级更高
 * Around与@AfterReturning共存时 AfterReturning无法获取返回值
 * 程序异常的情况下不会走@AfterReturning
 * @author 银娣
 *
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class PersonalAspect {

    @Pointcut("@annotation(PersonalAnnotation)")
    public void aopCut() {
    }

	/**
	 * 前置通知
	 * @param joinPoint
	 */
	@Before("aopCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());
        PersonalAnnotation annotation = method.getAnnotation(PersonalAnnotation.class);
        String value = annotation.value();
        System.out.println("@Before"+value);
    }

	/**
	 * 后置通知
	 * @param joinPoint
	 */
	@After("aopCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PersonalAnnotation annotation = method.getAnnotation(PersonalAnnotation.class);
        String value = annotation.value();
        System.out.println("@After"+value);
    }

	/**
	 * 运行通知
	 * @param joinPoint
	 * @param returnValue
	 */
	@AfterReturning(value="aopCut()", returning="returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PersonalAnnotation annotation = method.getAnnotation(PersonalAnnotation.class);
        String value = annotation.value();
        System.out.println("@AfterReturning"+returnValue);
    }

	/**
	 * 环绕通知
	 * @param joinPoint
	 * @return
	 */
	@Around("aopCut()")
    public Object aroudcut(ProceedingJoinPoint joinPoint) {
        Object returnValue = null;
    	System.out.println("@Around开始了");
    	try {
    	     returnValue = joinPoint.proceed();
			 System.out.println(returnValue);
		} catch (Throwable e) {
			e.printStackTrace();
		}
    	System.out.println("@Around结束了");
    	return returnValue;
    }

	/**
	 * 异常通知
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(pointcut="aopCut()", throwing="ex")
    public void exceptionCut(JoinPoint joinPoint, Throwable ex) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PersonalAnnotation annotation = method.getAnnotation(PersonalAnnotation.class);
        String value = annotation.error();
        System.out.println(method.getName()+value);
    }
}
