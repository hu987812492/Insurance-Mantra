package com.insurancemantra.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancemantra.controller.data.AdminTotalCounts;
import com.insurancemantra.controller.data.PolicyCategoryResponse;
import com.insurancemantra.controller.data.PolicySubCategoryResponse;
import com.insurancemantra.controller.data.PolicyUserRequest;
import com.insurancemantra.controller.data.RestLogicalErrorException;
import com.insurancemantra.dao.IAdminDao;
import com.insurancemantra.entity.PolicyCategory;
import com.insurancemantra.entity.PolicySubCategory;
import com.insurancemantra.entity.PolicyUser;

@Service
public class AdminService {
	
	@Autowired IAdminDao admindao;
	
	public AdminTotalCounts getTotalCounts(){
		long totalCounts[] = admindao.getTotalPolicyCount();

		AdminTotalCounts totalAdminCounts = new AdminTotalCounts(totalCounts[0], totalCounts[1], totalCounts[2], totalCounts[3],
				totalCounts[4], totalCounts[5], totalCounts[6], totalCounts[7], totalCounts[8], totalCounts[9]);
		return totalAdminCounts;
	}
	
	public boolean createCategory(PolicyCategoryResponse categoryRequest) throws RestLogicalErrorException{
		PolicyCategory category = new PolicyCategory();
		category.setCategoryDesc(categoryRequest.getCategoryDesc());
		category.setCategoryName(categoryRequest.getCategoryName());
		Date creationDate = new Date();
		category.setCreationDate(creationDate);
		
		if(admindao.categorynames().contains(categoryRequest.getCategoryName())){
			throw new RestLogicalErrorException("category already exists");
		}else{
		if(!admindao.createCategory(category)){
			throw new RestLogicalErrorException("category can not be created!");
		}
		}
		return true;		
	}
	
	public boolean updateCategory(PolicyCategoryResponse categoryRequest) throws RestLogicalErrorException{
		PolicyCategory category = new PolicyCategory();
		category.setCategoryId(categoryRequest.getCategoryId());
		category.setCategoryDesc(categoryRequest.getCategoryDesc());
		category.setCategoryName(categoryRequest.getCategoryName());
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");
		try {
			category.setCreationDate(sdf.parse(categoryRequest.getCreationDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!admindao.updateCategory(category)){
			throw new RestLogicalErrorException("category can not be updated!");
		}
		return true;		
	}
	public boolean deleteCategory(int categoryId) throws RestLogicalErrorException{
		/*PolicyCategory category = new PolicyCategory();
		category.setCategoryId(categoryRequest.getCategoryId());
		category.setCategoryDesc(categoryRequest.getCategoryDesc());
		category.setCategoryName(categoryRequest.getCategoryName());
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");
		try {
			category.setCreationDate(sdf.parse(categoryRequest.getCreationDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(!admindao.deleteCategory(categoryId)){
			throw new RestLogicalErrorException("category can not be deleted!");
		}
		return true;
	}
	public boolean createSubCategory(PolicySubCategoryResponse subCategoryRequest, int categoryId) throws RestLogicalErrorException{
		PolicySubCategory subCategory = new PolicySubCategory();
		Date creationDate = new Date();
		subCategory.setCreationDate(creationDate);
		
		subCategory.setSubCategoryBenefits(subCategoryRequest.getSubCategoryBenefits());
		subCategory.setSubCategoryDesc(subCategoryRequest.getSubCategoryDesc());
		subCategory.setSubCategoryFeatures(subCategoryRequest.getSubCategoryFeatures());
		subCategory.setSubCategoryName(subCategoryRequest.getSubCategoryName());
		if(!admindao.createSubCategory(subCategory, categoryId)){
			throw new RestLogicalErrorException("SubCategory can not be created!");
		}
		return true;
	}
	public boolean updateSubCategory(PolicySubCategoryResponse subCategoryRequest) throws RestLogicalErrorException{
				
		if(!admindao.updateSubCategory(subCategoryRequest.getSubCategoryId(),subCategoryRequest.getCreationDate(),
				subCategoryRequest.getSubCategoryBenefits(), subCategoryRequest.getSubCategoryDesc(), subCategoryRequest.getSubCategoryFeatures(),
				subCategoryRequest.getSubCategoryName())){
			throw new RestLogicalErrorException("SubCategory can not be updated!");
		}
		return true;
	}
	public boolean deleteSubCategory(int subCategoryId) throws RestLogicalErrorException{
		/*PolicySubCategory subCategory = new PolicySubCategory();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			subCategory.setCreationDate(sdf.parse(subCategoryRequest.getCreationDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//subCategory.setPolicyCategory(subCategoryRequest.getPolicyCategory());
		subCategory.setSubCategoryId(subCategoryRequest.getSubCategoryId());
		subCategory.setSubCategoryBenefits(subCategoryRequest.getSubCategoryBenefits());
		subCategory.setSubCategoryDesc(subCategoryRequest.getSubCategoryDesc());
		subCategory.setSubCategoryFeatures(subCategoryRequest.getSubCategoryFeatures());
		subCategory.setSubCategoryName(subCategoryRequest.getSubCategoryName());*/
		if(!admindao.deleteSubCategory(subCategoryId)){
			throw new RestLogicalErrorException("SubCategory can not be deleted!");
		}
		return true;
	}
	public boolean deleteCustomer(int userId) throws RestLogicalErrorException{
		/*PolicyUser customer = new PolicyUser();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			customer.setCreationDate(sdf.parse(userRequest.getCreationDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer.setUserId(userRequest.getUserId());
		customer.setName(userRequest.getName());
		customer.setPassword(userRequest.getPassword());
		customer.setUserAddress(userRequest.getUserAddress());
		customer.setUserCity(userRequest.getUserCity());
		customer.setUserCountry(userRequest.getUserCountry());
		try {
			customer.setUserDob(sdf.parse(userRequest.getUserDob()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer.setUserEmail(userRequest.getUserEmail());
		customer.setUserName(userRequest.getUserName());
		customer.setUserPhone(userRequest.getUserPhone());
		customer.setUserRole("customer");
		customer.setUserState(userRequest.getUserState());*/
		if(!admindao.deleteCustomer(userId)){
			throw new RestLogicalErrorException("Customer can not be deleted!");
		}
		return true;
	}

	public boolean updateSellerStatus(int brandId) throws RestLogicalErrorException{
		String sellerStatus = "Active";
		if(!admindao.updateSellerStatus(brandId, sellerStatus)){
			throw new RestLogicalErrorException("status cannot be updated!");
		}
		return true;
	}

}
