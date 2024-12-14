package com.coverter.intelliconverter.datatransferobject;

public class MemberDataTransferObject {

	private String gmail;
	private String password;
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MemberDataTransferObject(String gmail, String password) {
		super();
		this.gmail = gmail;
		this.password = password;
	}
	public MemberDataTransferObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}