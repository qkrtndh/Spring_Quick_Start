package polymorphism;

import org.springframework.stereotype.Component;
//@Component("sony")
public class SonySpeaker implements Speaker{
	
	public SonySpeaker() {
		System.out.println("===> Sony Speaker 객체 생성");
	}
	
	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("SonySpeaker 소리 올림");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("SonySpeaker 소리 내림");
	}
}
