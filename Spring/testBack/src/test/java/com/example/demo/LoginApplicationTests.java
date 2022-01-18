package com.example.demo;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.UserInfoDto;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LoginApplication.class)
@SpringBootTest
public class LoginApplicationTests {
	@Autowired
	private UserInfoMapper uMapper;
	
	@Test
	public void UserLogin() {
		
		UserInfoDto user = new UserInfoDto();
		SHA256 sha256 = new SHA256();
		user = uMapper.selectOneUser("testUser");
		
		try {
			if ((user.getPw().equals(sha256.encrypt("testPw")))) {
				System.out.print(user);
			}
			else {
				System.out.print("login failed\n");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}

}