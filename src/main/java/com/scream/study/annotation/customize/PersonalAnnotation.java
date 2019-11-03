package com.scream.study.annotation.customize;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersonalAnnotation {
	String value() default "";
	
	String error() default "";
	
	OperationType OperationType() default OperationType.UNKNOWN;
}
