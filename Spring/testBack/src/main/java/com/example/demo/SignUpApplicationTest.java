package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.UserInfoDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SignUpApplicationTest {
    @Autowired
    private UserInfoMapper uMapper;
    
    @Test
    // 테스트를 위한 유저 세팅
 	public void testUserSetting() {
 		UserInfoDto testUser = new UserInfoDto();
 		
 		testUser.setId("testUser");
 		testUser.setPw("testPassword");
 		testUser.setUserLevel(0);
 		testUser.setNickName("testNick");

 		uMapper.insertUser(testUser);
 		
 		System.out.println(uMapper.selectOneUser("testUser"));
 	}
}
