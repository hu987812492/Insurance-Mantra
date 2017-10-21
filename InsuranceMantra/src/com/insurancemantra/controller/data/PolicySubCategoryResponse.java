package com.insurancemantra.controller.data;

import java.util.Date;

import com.insurancemantra.entity.PolicyCategory;

public class PolicySubCategoryResponse {
	
	private Integer subCategoryId;
	//private PolicyCategory policyCategory;
	private String subCategoryName;
	private String creationDate;
	private String subCategoryDesc;
	private String subCategoryFeatures;
	private String subCategoryBenefits;
	private int categoryId;
	private String categoryName;
	
	
	public PolicySubCategoryResponse() {
		super();
	}

	public PolicySubCategoryResponse(Integer subCategoryId, String subCategoryName, String subCategoryDesc, String subCategoryFeatures, String subCategoryBenefits) {
	
		this.subCategoryId = subCategoryId;
		//this.policyCategory = policyCategory;
		this.subCategoryName = subCategoryName;
		//this.creationDate = creationDate;
		this.subCategoryDesc = subCategoryDesc;
		this.subCategoryFeatures = subCategoryFeatures;
		this.subCategoryBenefits = subCategoryBenefits;
	}
	
	public Integer getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
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

	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getSubCategoryDesc() {
		return subCategoryDesc;
	}
	public void setSubCategoryDesc(String subCategoryDesc) {
		this.subCategoryDesc = subCategoryDesc;
	}
	public String getSubCategoryFeatures() {
		return subCategoryFeatures;
	}
	public void setSubCategoryFeatures(String subCategoryFeatures) {
		this.subCategoryFeatures = subCategoryFeatures;
	}
	public String getSubCategoryBenefits() {
		return subCategoryBenefits;
	}
	public void setSubCategoryBenefits(String subCategoryBenefits) {
		this.subCategoryBenefits = subCategoryBenefits;
	}
	
	

}
