package com.springbook.biz;

public class TVUser {

	public static void main(String[] args) {
		BeanFactory factory = new BeanFactory();
		TV tv = (TV)factory.getBean(args[0]);
			
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}
//실행시 run config~~ 아규먼트윗 상자칸에 lg나 samsung 입력
}
