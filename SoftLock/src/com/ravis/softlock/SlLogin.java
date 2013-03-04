package com.ravis.softlock;

public class SlLogin {

	private long id;
	private String userName;
	private byte[] userPassword;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public byte[] getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(byte[] userPassword) {
		this.userPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "User Name:" + userName;
	}
	
}
