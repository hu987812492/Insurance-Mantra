package com.insurancemantra.dao;

import java.util.ArrayList;
import java.util.Map;

import com.insurancemantra.entity.PolicyBrand;
import com.insurancemantra.entity.PolicyCategory;
import com.insurancemantra.entity.PolicyPlan;
import com.insurancemantra.entity.PolicySubCategory;

public interface IWebsiteFrontDao {
	
	public ArrayList<PolicyCategory> getAllCategories();
	public PolicyCategory getAllSubCategories(int categoryId);
	public ArrayList<PolicyPlan> getAllPolicyPlanListBrandBased(int brandId);
	public ArrayList<PolicyPlan> getAllPolicyPlanListSubCategoryBased(int subCategoryId);
	public ArrayList<PolicyBrand> getAllBrands();
	public PolicyPlan getPolicyPlanDetail(int policyId);
	public ArrayList<PolicySubCategory> getTotalSubCategories();
	public ArrayList<PolicyPlan> getAllPolicyPlans();
	public ArrayList<Integer > getTop3PolicyPlans();

}
