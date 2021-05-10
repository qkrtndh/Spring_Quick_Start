package com.springbook.biz.common;

import org.aopalliance.intercept.Joinpoint;

public class LogAdvice {
	public void printLog(Joinpoint jp) {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}
