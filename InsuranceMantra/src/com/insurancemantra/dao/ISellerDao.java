package com.insurancemantra.dao;

import java.util.ArrayList;
import java.util.Date;

import com.insurancemantra.entity.PolicyBrand;
import com.insurancemantra.entity.PolicyOrderHistory;
import com.insurancemantra.entity.PolicyPlan;

public interface ISellerDao {
	
	public boolean registerBrand(PolicyBrand brand);
	public ArrayList<String> getBrandList();
	public PolicyBrand validateBrandLogin(String brandName, String password);
	public PolicyBrand getSellerProfileDetail(String brandName);
	public boolean updateSellerProfile(int brandId,  String brandAddress,
			String brandCity,String brandCountry,String brandEmail, 
			int brandPhone, String brandState) ;
	public boolean updateOrderHistory(long orderId, Date completionDate, String status);
	public boolean createPolicyPlan(PolicyPlan plan, int brandId, int subCategoryId);
	public ArrayList<PolicyPlan> getPolicyPlanList(int brandId);
	public boolean updatePolicyPlan(PolicyPlan plan);
	public boolean deletePolicyPlan(int planId);
	public ArrayList<PolicyOrderHistory> getOrderList(int brandId);
	public ArrayList<String> policyNames(int brandId);
	public PolicyBrand getTopPolicyBrand();
	
}
