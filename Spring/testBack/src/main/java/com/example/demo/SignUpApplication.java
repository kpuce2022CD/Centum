package com.example.demo;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.UserInfoDto;

@SpringBootApplication
public class SignUpApplication {
	private UserInfoMapper uMapper;
	
	public boolean UserSignUp(String id, String nickName, String pw) {
		
		UserInfoDto user = new UserInfoDto();
		SHA256 sha256 = new SHA256();
		
		user.setId(id);
		user.setNickName(nickName);
		user.setUserLevel(0);
		try {
			user.setPw(sha256.encrypt(pw));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		uMapper.insertUser(user);
		
		return true;
	}

}
