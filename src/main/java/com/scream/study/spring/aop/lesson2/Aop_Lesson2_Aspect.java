package com.scream.study.spring.aop.lesson2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

@Aspect
@Component
//@EnableAspectJAutoProxy
@Scope("prototype")
public class Aop_Lesson2_Aspect {

	@Autowired
	private PersonalTransactional transactionalUtils;

	@Pointcut("execution (* com.scream.study.spring.aop.lesson2.Aop_Lesson2_Service.*(..))")
	public void pointCut() {

	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		TransactionStatus status = transactionalUtils.begin();
		try {
			result = joinPoint.proceed();// 执行目标方案 并拿到返回值
			transactionalUtils.commit(status);
		} catch (Exception e) {
			System.out.println("Around注解捕获到异常并进行回滚...");
			transactionalUtils.rollback(status);
		}
		return result;
	}
}
