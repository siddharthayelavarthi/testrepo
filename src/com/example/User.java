package com.example;

import com.cognizant.idw.nosqldb.DBObject;

public class User extends DBObject{
	String email;
	String nickname;
	String buddyname;
	String mobileno;
	
	public String getEmail() {
		return email;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getBuddyname() {
		return buddyname;
	}
	
	public String getMobileno() {
		return mobileno;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setBuddyname(String buddyname) {
		this.buddyname = buddyname;
	}
	
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
}
