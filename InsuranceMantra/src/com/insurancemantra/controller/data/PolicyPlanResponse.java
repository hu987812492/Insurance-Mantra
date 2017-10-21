package com.insurancemantra.controller.data;

import com.insurancemantra.entity.PolicyBrand;

public class PolicyPlanResponse {
	
	private Integer planId;
	private String planName;
	private String minEntryAge;
	private String maxEntryAge;
	private String planMaturity;
	private String premiumAmount;
	private String sumAssured;
	private String creationDate;
	private String planDesc;
	private String planFeatures;
	private Integer brandId;
	private Integer subCategoryId;
	
	public PolicyPlanResponse() {
		
	}
	
	public PolicyPlanResponse(Integer planId, String planName, String minEntryAge, String maxEntryAge,String creationDate,
			String planMaturity, String premiumAmount, String sumAssured, String planDesc, String planFeatures, Integer brandId, Integer subCategoryId) {
		
		this.planId = planId;
		this.planName = planName;
		this.minEntryAge = minEntryAge;
		this.maxEntryAge = maxEntryAge;
		this.creationDate = creationDate;
		this.planMaturity = planMaturity;
		this.premiumAmount = premiumAmount;
		this.sumAssured = sumAssured;
		this.planDesc = planDesc;
		this.planFeatures = planFeatures;
		this.brandId = brandId;
		this.subCategoryId = subCategoryId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
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


	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	
	
}
