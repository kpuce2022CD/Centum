package com.example.demo;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.UserInfoDto;


@SpringBootApplication
public class LoginApplication {
	private UserInfoMapper uMapper;
	
	public UserInfoDto UserLogin(String id, String pw) {
		
		UserInfoDto user = new UserInfoDto();
		SHA256 sha256 = new SHA256();
		user = uMapper.selectOneUser(id);
		
		try {
			if (!(user.getPw().equals(sha256.encrypt(pw)))) {
				return null;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}