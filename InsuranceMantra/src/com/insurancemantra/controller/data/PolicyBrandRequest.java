package com.insurancemantra.controller.data;

import java.util.Date;

public class PolicyBrandRequest {
	
	private Integer brandId;
	private String brandName;
	private String creationDate;
	private String brandEmail;
	private String password;
	private String brandAddress;
	private String brandCity;
	private String brandState;
	private String brandCountry;
	private Integer brandPhone;
	private String brandStatus;
	
	
	
	public PolicyBrandRequest() {
		
	}
	public PolicyBrandRequest(Integer brandId, String brandName, String creationDate, String brandEmail,
			String password, String brandAddress, String brandCity, String brandState, String brandCountry,
			Integer brandPhone, String brandStatus) {
		this.brandId = brandId;
		this.brandName = brandName;
		this.creationDate = creationDate;
		this.brandEmail = brandEmail;
		this.password = password;
		this.brandAddress = brandAddress;
		this.brandCity = brandCity;
		this.brandState = brandState;
		this.brandCountry = brandCountry;
		this.brandPhone = brandPhone;
		this.brandStatus = brandStatus;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getBrandEmail() {
		return brandEmail;
	}
	public void setBrandEmail(String brandEmail) {
		this.brandEmail = brandEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBrandAddress() {
		return brandAddress;
	}
	public void setBrandAddress(String brandAddress) {
		this.brandAddress = brandAddress;
	}
	public String getBrandCity() {
		return brandCity;
	}
	public void setBrandCity(String brandCity) {
		this.brandCity = brandCity;
	}
	public String getBrandState() {
		return brandState;
	}
	public void setBrandState(String brandState) {
		this.brandState = brandState;
	}
	public String getBrandCountry() {
		return brandCountry;
	}
	public void setBrandCountry(String brandCountry) {
		this.brandCountry = brandCountry;
	}
	public Integer getBrandPhone() {
		return brandPhone;
	}
	public void setBrandPhone(Integer brandPhone) {
		this.brandPhone = brandPhone;
	}
	public String getBrandStatus() {
		return brandStatus;
	}
	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}
	
	

}
