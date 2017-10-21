package com.insurancemantra.controller.data;

import java.util.ArrayList;
import java.util.Date;

public class PolicyCategoryResponse {
	
	private int categoryId;
	private String categoryName;
	private String creationDate;
	private String categoryDesc;
	private ArrayList<PolicySubCategoryResponse> subCategoryList;
	
	public PolicyCategoryResponse() {
		
	}
	public PolicyCategoryResponse(int categoryId, String categoryName, String categoryDesc) {
		
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		//this.creationDate = creationDate;
		this.categoryDesc = categoryDesc;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public ArrayList<PolicySubCategoryResponse> getSubCategoryList() {
		return subCategoryList;
	}
	public void setSubCategoryList(ArrayList<PolicySubCategoryResponse> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
	
	

}
