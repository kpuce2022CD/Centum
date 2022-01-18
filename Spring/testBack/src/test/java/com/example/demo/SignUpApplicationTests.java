package com.example.demo;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.UserInfoDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SignUpApplication.class)
@SpringBootTest
public class SignUpApplicationTests {
	@Autowired
	private UserInfoMapper uMapper;
	
	@Test
	public void UserSignUp() {
		
		UserInfoDto user = new UserInfoDto();
		SHA256 sha256 = new SHA256();
		
		user.setId("testUser");
		user.setNickName("testNick");
		user.setUserLevel(0);
		try {
			user.setPw(sha256.encrypt("testPw"));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		uMapper.insertUser(user);
		
	}

}
