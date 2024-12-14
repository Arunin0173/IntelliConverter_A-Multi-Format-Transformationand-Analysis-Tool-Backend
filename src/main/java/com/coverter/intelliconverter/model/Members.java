package com.coverter.intelliconverter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="members")
public class Members {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="name",nullable = false)
	private String name;
	@Column(name="gmail",nullable = false)
	private String gmail;
	@Column(name="country",nullable = false)
	private String country;
	@Column(name="phoneNumber",nullable = false)
	private long phoneNumber;
	@Column(name="password",nullable = false)
	private String password;

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Members() {
		super();
	}
	public Members(long id, String name, String gmail, String country, long phoneNumber, String password) {
		super();
		this.id = id;
		this.name = name;
		this.gmail = gmail;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	
}