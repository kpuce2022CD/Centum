package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.UserInfoDto;


@SpringBootApplication
public class LoginApplication {
	private UserInfoMapper uMapper;
	
	// 테스트를 위한 유저 세팅
	public void testUserSetting() {
		UserInfoDto testUser = new UserInfoDto();
		
		testUser.setId("testUser");
		testUser.setPw("testPassword");
		testUser.setUserLevel(0);
		testUser.setNickName("testNickName");
	}

}
