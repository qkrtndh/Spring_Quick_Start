package com.springbook.biz.common;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
public class LogAdvice {
	@Pointcut ("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut ("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
}
