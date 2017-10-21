package com.insurancemantra.dao;

import java.util.ArrayList;
import java.util.Date;

import com.insurancemantra.entity.PolicyCategory;
import com.insurancemantra.entity.PolicySubCategory;
import com.insurancemantra.entity.PolicyUser;

public interface IAdminDao {
	
	public long[] getTotalPolicyCount();
	public boolean createCategory(PolicyCategory category);
	public ArrayList<String> categorynames();
	public boolean updateCategory(PolicyCategory category);
	public boolean deleteCategory(int categoryId);
	public boolean createSubCategory(PolicySubCategory subCategory, int categoryId);
	public ArrayList<String> subCategorynames();
	public boolean updateSubCategory(int subCategoryId,String creationDate,String subCategoryBenefits, 
			String subCategoryDesc, String subCategoryFeatures,	String subCategoryName);
	public boolean deleteSubCategory(int subcategoryId);
	public boolean deleteCustomer(int userId);
	public boolean updateSellerStatus(int brandId, String sellerStatus);
	

}
