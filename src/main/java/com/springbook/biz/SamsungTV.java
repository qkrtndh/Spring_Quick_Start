package com.springbook.biz;

public class SamsungTV implements TV {
	public SamsungTV() {
		System.out.println("===> SamsungTV 객체 생성");
	}
	public void powerOn() {
		System.out.println("SamsungTV---전원킴.");
	}
	public void powerOff() {
		System.out.println("SamsungTV---전원끔.");
	}
	public void volumeUp() {
		System.out.println("SamsungTV---소리 업.");
	}
	public void volumeDown() {
		System.out.println("SamsungTV---소리 다운.");
	}
}
