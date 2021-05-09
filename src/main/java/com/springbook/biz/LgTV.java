package com.springbook.biz;

import org.springframework.stereotype.Component;

@Component("tv")
//@Component
public class LgTV implements TV{
	public LgTV() {
		System.out.println("===> LgTV 객체 생성");
	}
	
	@Override
	public void powerOn() {
		System.out.println("LgTV---전원킴.");
		
	}
	@Override
	public void powerOff() {
		System.out.println("LgTV---전원끔.");
		
	}
	@Override
	public void volumeUp() {
		System.out.println("LgTV---소리 업.");
		
	}
	@Override
	public void volumeDown() {
		System.out.println("LgTV---소리 다운.");
		
	}
}
