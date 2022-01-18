package com.example.demo.dto;

public class UserInfoDto {
	private String id;
	private String nickName;
	private int userLevel;
	private String pw;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String toString() {
		return "id: " + this.id + "\nnickName: " + this.nickName + "\nlevel: " + this.userLevel;
	}
}
