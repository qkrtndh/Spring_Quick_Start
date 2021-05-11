package com.springbook.biz.common;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class LogAdvice {
	@Pointcut ("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Before("allPointcut()")
	public void printLLog() {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
	
	@Pointcut ("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
}
