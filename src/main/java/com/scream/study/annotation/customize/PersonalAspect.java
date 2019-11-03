package com.scream.study.annotation.customize;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
/**
 * Around与afterThrowing不共存 Around优先级更高
 * Around与@AfterReturning共存时 AfterReturning无法获取返回值
 * @author 银娣
 *
 */
@Aspect
@Component
public class PersonalAspect {

    @Pointcut("@annotation(PersonalAnnotation)")
    public void aopCut() {
    }

    @Before("aopCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());
        PersonalAnnotation annotation = method.getAnnotation(PersonalAnnotation.class);
        String value = annotation.value();
        System.out.println("@Before"+value);
    }

    @After("aopCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PersonalAnnotation annotation = method.getAnnotation(PersonalAnnotation.class);
        String value = annotation.value();
        System.out.println("@After"+value);
    }
    @AfterReturning(value="aopCut()", returning="returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PersonalAnnotation annotation = method.getAnnotation(PersonalAnnotation.class);
        String value = annotation.value();
        System.out.println("@AfterReturning"+returnValue);
    }
//    @Around("aopCut()")
//    public void aroudcut(ProceedingJoinPoint joinPoint) {
//    	System.out.println("@Around开始了");
//    	try {
//			 Object returnValue = joinPoint.proceed();
//			 System.out.println(returnValue);
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//    	System.out.println("@Around结束了");
//    }
//    @AfterThrowing(pointcut="aopCut()", throwing="ex")
//    public void exceptionCut(JoinPoint joinPoint, Throwable ex) {
//        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        PersonalAnnotation annotation = method.getAnnotation(PersonalAnnotation.class);
//        String value = annotation.error();
//        System.out.println(method.getName()+value);
//    }
}
