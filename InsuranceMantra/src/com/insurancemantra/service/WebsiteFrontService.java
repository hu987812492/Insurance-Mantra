package com.insurancemantra.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancemantra.controller.data.PolicyBrandResponse;
import com.insurancemantra.controller.data.PolicyCategoryResponse;
import com.insurancemantra.controller.data.PolicyOrderHistoryResponse;
import com.insurancemantra.controller.data.PolicyPlanResponse;
import com.insurancemantra.controller.data.PolicySubCategoryResponse;
import com.insurancemantra.dao.IWebsiteFrontDao;
import com.insurancemantra.entity.PolicyBrand;
import com.insurancemantra.entity.PolicyCategory;
import com.insurancemantra.entity.PolicyOrderHistory;
import com.insurancemantra.entity.PolicyPlan;
import com.insurancemantra.entity.PolicySubCategory;

@Service
public class WebsiteFrontService {
	
	@Autowired
	private IWebsiteFrontDao websiteFront;
	
	public ArrayList<PolicyCategoryResponse> getAllCategories(){
		ArrayList<PolicyCategory> categories = websiteFront.getAllCategories();
		
		ArrayList<PolicyCategoryResponse> categoryList = null;
		if(categories != null){
			categoryList = new ArrayList<>();
			for (PolicyCategory category : categories){
				SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
				PolicyCategoryResponse catResponse = new PolicyCategoryResponse();
				catResponse.setCategoryDesc(category.getCategoryDesc());
				catResponse.setCategoryId(category.getCategoryId());
				catResponse.setCategoryName(category.getCategoryName());
				//catResponse.setCreationDate(sdf.format(category.getCreationDate()));
				categoryList.add(catResponse);				
			}			
		}		
		return categoryList;				
	}
	
	public ArrayList<PolicySubCategoryResponse> getTotalSubCategories() {
		ArrayList<PolicySubCategoryResponse> subCategoryList = new ArrayList<>();
		ArrayList<PolicySubCategory> subCategories = websiteFront.getTotalSubCategories();
		if(subCategories != null){
			for(PolicySubCategory subCategory : subCategories){
				PolicySubCategoryResponse subCategoryResponse = new PolicySubCategoryResponse();
				//subCategoryResponse.setCategoryId(subCategory.getPolicyCategory().getCategoryId());
				//subCategoryResponse.setCategoryName(subCategory.getPolicyCategory().getCategoryName());
				/*if(subCategory.getCreationDate()!=null){
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				subCategoryResponse.setCreationDate(sdf.format(subCategory.getCreationDate()));
				}*/
				/*subCategoryResponse.setSubCategoryBenefits(subCategory.getSubCategoryBenefits());
				subCategoryResponse.setSubCategoryDesc(subCategory.getSubCategoryDesc());
				subCategoryResponse.setSubCategoryFeatures(subCategory.getSubCategoryFeatures());*/
				subCategoryResponse.setSubCategoryId(subCategory.getSubCategoryId());
				subCategoryResponse.setSubCategoryName(subCategory.getSubCategoryName());				
				subCategoryList.add(subCategoryResponse);
			}
		}
		return subCategoryList;				
	}
	
	public PolicyCategoryResponse getAllSubCategories(int categoryId) {
		PolicyCategory category = websiteFront.getAllSubCategories(categoryId);
		
		PolicyCategoryResponse categoryResponse = new PolicyCategoryResponse();
		ArrayList<PolicySubCategoryResponse> subCategoryList = null;
		if(category != null){
			subCategoryList = new ArrayList<>();
			categoryResponse.setCategoryDesc(category.getCategoryDesc());
			categoryResponse.setCategoryId(category.getCategoryId());
			categoryResponse.setCategoryName(category.getCategoryName());
			DateFormat datefrmt = new SimpleDateFormat("MM/DD/YYYY");
			//categoryResponse.setCreationDate(datefrmt.format(category.getCreationDate()));
			for (PolicySubCategory subCategory : category.getPolicySubCategories()){				
				//String strDate = datefrmt.format(subCategory.getCreationDate());
				PolicySubCategoryResponse subCatResponse = new PolicySubCategoryResponse(subCategory.getSubCategoryId(), 
						subCategory.getSubCategoryName(), subCategory.getSubCategoryDesc(), 
						subCategory.getSubCategoryFeatures(), subCategory.getSubCategoryBenefits());
				subCategoryList.add(subCatResponse);
			}
			categoryResponse.setSubCategoryList(subCategoryList);
		}
		return categoryResponse;				
	}
	
	public ArrayList<PolicyPlanResponse> getAllPolicyPlanListBrandBased(int brandId) {
		
		ArrayList<PolicyPlan> plans = websiteFront.getAllPolicyPlanListBrandBased(brandId);
		
		ArrayList<PolicyPlanResponse> planList = null;
		if(plans != null){
			planList = new ArrayList<>();
			for (PolicyPlan plan : plans){
				SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
				PolicyPlanResponse planResponse = new PolicyPlanResponse(plan.getPlanId(), plan.getPlanName(), 
						plan.getMinEntryAge(), plan.getMaxEntryAge(), sdf.format(plan.getCreationDate()),
						plan.getPlanMaturity(),plan.getPremiumAmount(), plan.getSumAssured(), plan.getPlanDesc(), 
						plan.getPlanFeatures(), plan.getPolicyBrand().getBrandId() , plan.getPolicySubCategory().getSubCategoryId());
				planList.add(planResponse);
			}			
		}
		return planList;
	}
	
public ArrayList<PolicyPlanResponse> getAllPolicyPlanListSubCategoryBased(int subCategoryId) {
		
		ArrayList<PolicyPlan> plans = websiteFront.getAllPolicyPlanListSubCategoryBased(subCategoryId);
		
		ArrayList<PolicyPlanResponse> planList = null;
		if(plans != null){
			planList = new ArrayList<>();
			for (PolicyPlan plan : plans){
				SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
				PolicyPlanResponse planResponse = new PolicyPlanResponse(plan.getPlanId(), plan.getPlanName(), 
						plan.getMinEntryAge(), plan.getMaxEntryAge(), sdf.format(plan.getCreationDate()),
						plan.getPlanMaturity(),plan.getPremiumAmount(), plan.getSumAssured(), plan.getPlanDesc(), plan.getPlanFeatures(), 
						plan.getPolicyBrand().getBrandId(), plan.getPolicySubCategory().getSubCategoryId() );
				planList.add(planResponse);
			}			
		}
		return planList;
	}

public ArrayList<PolicyBrandResponse> getAllBrands(){
	ArrayList<PolicyBrand> brands = websiteFront.getAllBrands();
	
	ArrayList<PolicyBrandResponse> brandList = null;
	if(brands != null){
		brandList = new ArrayList<>();
		for (PolicyBrand brand : brands){
			SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
			PolicyBrandResponse brandResponse = new PolicyBrandResponse(brand.getBrandId(),brand.getBrandName(), 
				sdf.format(brand.getCreationDate()), brand.getBrandEmail(), brand.getPassword(), brand.getBrandAddress(), brand.getBrandCity(),
				brand.getBrandState(), brand.getBrandCountry(), brand.getBrandPhone(), brand.getBrandStatus());
			/*for(PolicyOrderHistory order : brand.getPolicyOrderHistories()){
				PolicyOrderHistoryResponse orderResponse = new PolicyOrderHistoryResponse(order.getOrderId(),order.getRequesterName(),
						order.getRequesterEmail(), order.getRequesterPhone(), sdf.format(order.getRequesterDob()), order.getRequesterIncome(), order.getOrderStatus(),
						sdf.format(order.getOrderCreationDate()), sdf.format(order.getOrderCompletionDate()), order.getPolicyPlan());
				brandResponse.getPolicyOrderHistories().add(orderResponse);
			}*/
			/*for(PolicyPlan plan : brand.getPolicyPlans()){
				PolicyPlanResponse planResponse = new PolicyPlanResponse(plan.getPlanId(), plan.getPlanName(), plan.getMinEntryAge(),
						plan.getMaxEntryAge(), sdf.format(plan.getCreationDate()),plan.getPlanMaturity(), plan.getPremiumAmount(), 
						plan.getSumAssured(), plan.getPlanDesc(),plan.getPlanFeatures(), plan.getPolicyBrand());
				brandResponse.getPolicyPlans().add(planResponse);
			}*/
			brandList.add(brandResponse);		
		}			
	}		
	return brandList;				
}

public PolicyPlanResponse getPolicyPlanDetail(int policyId){
	PolicyPlan plan = websiteFront.getPolicyPlanDetail(policyId);
	PolicyPlanResponse planResponse = null;
	if(plan!=null){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
		planResponse = new PolicyPlanResponse(plan.getPlanId(), plan.getPlanName(), plan.getMinEntryAge(),
						plan.getMaxEntryAge(), plan.getCreationDate()!=null?sdf.format(plan.getCreationDate()):null,plan.getPlanMaturity(), plan.getPremiumAmount(), 
						plan.getSumAssured(), plan.getPlanDesc(),plan.getPlanFeatures(), plan.getPolicyBrand().getBrandId(), plan.getPolicySubCategory().getSubCategoryId());
	}
	return planResponse;
}

public ArrayList<PolicyPlanResponse> getAllPolicyPlans() {
	ArrayList<PolicyPlan> plans = websiteFront.getAllPolicyPlans();
	
	ArrayList<PolicyPlanResponse> planList = null;
	if(plans != null){
		planList = new ArrayList<>();
		for (PolicyPlan plan : plans){
			SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
			PolicyPlanResponse planResponse = new PolicyPlanResponse(plan.getPlanId(), plan.getPlanName(), 
					plan.getMinEntryAge(), plan.getMaxEntryAge(), plan.getCreationDate()!=null?sdf.format(plan.getCreationDate()):null,
					plan.getPlanMaturity(),plan.getPremiumAmount(), plan.getSumAssured(), plan.getPlanDesc(), plan.getPlanFeatures(), 
					plan.getPolicyBrand().getBrandId(), plan.getPolicySubCategory().getSubCategoryId() );
			planList.add(planResponse);
		}			
	}
	return planList;
}

public ArrayList<PolicyPlanResponse> getTop3PolicyPlans() {
	ArrayList<Integer> top3Plans = websiteFront.getTop3PolicyPlans();
	PolicyPlan plan  = null;
	ArrayList<PolicyPlanResponse> planList = new ArrayList<PolicyPlanResponse>();
	if(top3Plans != null){
		planList = new ArrayList<>();
		for (Object planId : top3Plans){
			if(planId != null){
				
				System.out.println(planId);
				int i = (Integer) planId;
				
				plan = websiteFront.getPolicyPlanDetail(i);
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
				PolicyPlanResponse planResponse = new PolicyPlanResponse(plan.getPlanId(), plan.getPlanName(), 
						plan.getMinEntryAge(), plan.getMaxEntryAge(), plan.getCreationDate()!=null?sdf.format(plan.getCreationDate()):null,
						plan.getPlanMaturity(),plan.getPremiumAmount(), plan.getSumAssured(), plan.getPlanDesc(), plan.getPlanFeatures(), 
						plan.getPolicyBrand().getBrandId(), plan.getPolicySubCategory().getSubCategoryId() );
				planList.add(planResponse);
				
			}
		}			
	}
	return planList;
}

}
