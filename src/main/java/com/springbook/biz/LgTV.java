package com.springbook.biz;

public class LgTV implements TV{
	
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