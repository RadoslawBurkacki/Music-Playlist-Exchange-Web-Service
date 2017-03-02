package org.radoslawburkacki.MusicExchange.model;

public class Account {

	
	String nickname;
	String password;
	
	
	public Account(){
		
	}
	
	public Account( String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
