package com.springbook.biz;

public class TVUser {

	public static void main(String[] args) {
		//형변환
		TV tv = new SamsungTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}

}
