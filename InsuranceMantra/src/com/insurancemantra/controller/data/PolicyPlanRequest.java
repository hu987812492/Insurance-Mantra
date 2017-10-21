package com.insurancemantra.controller.data;

import com.insurancemantra.entity.PolicySubCategory;

public class PolicyPlanRequest {
	
	private Integer planId;
	private PolicySubCategoryResponse policySubCategoryResponse;
	private String planName;
	private String minEntryAge;
	private String maxEntryAge;
	private String planMaturity;
	private String premiumAmount;
	private String sumAssured;
	private String planDesc;
	private String planFeatures;
	private String creationDate;
	
	
	
	public PolicyPlanRequest() {
		
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	
	public PolicySubCategoryResponse getPolicySubCategoryResponse() {
		return policySubCategoryResponse;
	}
	public void setPolicySubCategoryResponse(PolicySubCategoryResponse policySubCategoryResponse) {
		this.policySubCategoryResponse = policySubCategoryResponse;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getMinEntryAge() {
		return minEntryAge;
	}
	public void setMinEntryAge(String minEntryAge) {
		this.minEntryAge = minEntryAge;
	}
	public String getMaxEntryAge() {
		return maxEntryAge;
	}
	public void setMaxEntryAge(String maxEntryAge) {
		this.maxEntryAge = maxEntryAge;
	}
	public String getPlanMaturity() {
		return planMaturity;
	}
	public void setPlanMaturity(String planMaturity) {
		this.planMaturity = planMaturity;
	}
	public String getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(String premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public String getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(String sumAssured) {
		this.sumAssured = sumAssured;
	}
	public String getPlanDesc() {
		return planDesc;
	}
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	public String getPlanFeatures() {
		return planFeatures;
	}
	public void setPlanFeatures(String planFeatures) {
		this.planFeatures = planFeatures;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
	
	

}
