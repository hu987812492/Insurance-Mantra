package com.insurancemantra.controller.data;

import java.util.Date;

import com.insurancemantra.entity.PolicyBrand;

public class PolicyUserResponse {
	
	private Integer userId;
	private String userName;
	private String password;
	private String userEmail;
	private String userRole;
	private String userDob;
	private String userAddress;
	private String userCity;
	private String userState;
	private String userCountry;
	private Integer userPhone;
	private String creationDate;
	private String name;
	
	
	public PolicyUserResponse(Integer userId, String userName, String password, String userEmail, String userRole,
			String userDob, String userAddress, String userCity, String userState, String userCountry, Integer userPhone,
			String creationDate, String name) {
		
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userEmail = userEmail;
		this.userRole = userRole;
		this.userDob = userDob;
		this.userAddress = userAddress;
		this.userCity = userCity;
		this.userState = userState;
		this.userCountry = userCountry;
		this.userPhone = userPhone;
		this.creationDate = creationDate;
		this.name = name;
	}
	public PolicyUserResponse() {
		// TODO Auto-generated constructor stub
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserDob() {
		return userDob;
	}
	public void setUserDob(String userDob) {
		this.userDob = userDob;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	public Integer getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(Integer userPhone) {
		this.userPhone = userPhone;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
