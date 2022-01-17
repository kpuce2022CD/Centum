package com.example.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.dto.UserInfoDto;

public interface UserInfoMapper {
	public void insertUser (UserInfoDto user);
	public void updateUser (UserInfoDto user);
	public void deleteUser (String userId);
	public UserInfoDto selectOneUser (String userId);
	public List<UserInfoDto> selectAllUser();
}
