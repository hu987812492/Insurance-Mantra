package com.insurancemantra.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancemantra.controller.data.PolicyBrandRequest;
import com.insurancemantra.controller.data.PolicyBrandResponse;
import com.insurancemantra.controller.data.PolicyOrderHistoryRequest;
import com.insurancemantra.controller.data.PolicyOrderHistoryResponse;
import com.insurancemantra.controller.data.PolicyPlanResponse;
import com.insurancemantra.controller.data.PolicyUserRequest;
import com.insurancemantra.controller.data.PolicyUserResponse;
import com.insurancemantra.controller.data.RestLogicalErrorException;
import com.insurancemantra.dao.ISellerDao;
import com.insurancemantra.entity.PolicyBrand;
import com.insurancemantra.entity.PolicyOrderHistory;
import com.insurancemantra.entity.PolicyPlan;
import com.insurancemantra.entity.PolicyUser;

@Service
public class SellerService {
	
	@Autowired 
	private ISellerDao sellerDao;
	
	public boolean registerBrand(PolicyBrandRequest brandRequest) throws RestLogicalErrorException{
		if(brandRequest.getBrandName() ==null || brandRequest.getPassword() ==null){

			throw new RestLogicalErrorException("Registration Parameters incomplete.");
		}else{
			PolicyBrand brand = new PolicyBrand();
			brand.setBrandAddress(brandRequest.getBrandAddress());
			brand.setBrandCity(brandRequest.getBrandCity());
			brand.setBrandCountry(brandRequest.getBrandCountry());
			brand.setBrandEmail(brandRequest.getBrandEmail());
			brand.setBrandName(brandRequest.getBrandName());
			brand.setBrandPhone(brandRequest.getBrandPhone());
			brand.setBrandState(brandRequest.getBrandState());
			brand.setBrandStatus("Pending");
			Date creationDate = new Date();
			brand.setCreationDate(creationDate);
			
			 try {
					TrippleDes td= new TrippleDes();
					brand.setPassword(td.encrypt(brandRequest.getPassword()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					throw new RestLogicalErrorException("password may not set properly ");
				}
			
			//brand.setPassword(brandRequest.getPassword());
			if(sellerDao.getBrandList().contains(brandRequest.getBrandName())){
				throw new RestLogicalErrorException("Duplicate brand");
			}else{if(!sellerDao.registerBrand(brand)){
				throw new RestLogicalErrorException("Seller registration failed");
			}
			}
		}
		return true;
	}
	
	public ArrayList<String> getBrandList(){
		ArrayList<String> brands = sellerDao.getBrandList();
		ArrayList<String> brandList = null;
		if(brands!=null){
			brandList = brands;
		}
		return brandList;
	}
	
	/*public PolicyBrandResponse validateBrandLogin(String brandName, String password) throws RestLogicalErrorException{

		PolicyBrand brand = sellerDao.validateBrandLogin(brandName, password);

		PolicyBrandResponse sellerSession = null;

		if(brand != null){
			sellerSession = new PolicyBrandResponse();
			sellerSession.setBrandAddress(brand.getBrandAddress());
			sellerSession.setBrandCity(brand.getBrandCity());
			sellerSession.setBrandCountry(brand.getBrandCountry());
			sellerSession.setBrandEmail(brand.getBrandEmail());
			sellerSession.setBrandId(brand.getBrandId());
			sellerSession.setBrandName(brand.getBrandName());
			sellerSession.setBrandPhone(brand.getBrandPhone());
			sellerSession.setBrandState(brand.getBrandState());
			sellerSession.setBrandStatus(brand.getBrandStatus());
			sellerSession.setPassword(brand.getPassword());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(brand.getCreationDate()!=null){
			sellerSession.setCreationDate(sdf.format(brand.getCreationDate()));
			}

		}else{
			RestLogicalErrorException authResponseErr = new RestLogicalErrorException("Invalid User");
			throw authResponseErr;
		}	
		return sellerSession;
	}*/
	
	public PolicyBrandResponse getSellerProfileDetail(String brandName){
		PolicyBrand brand = sellerDao.getSellerProfileDetail(brandName);
		PolicyBrandResponse sellerSession = null;
		if(brand!=null){
			sellerSession = new PolicyBrandResponse();
			sellerSession.setBrandAddress(brand.getBrandAddress());
			sellerSession.setBrandCity(brand.getBrandCity());
			sellerSession.setBrandCountry(brand.getBrandCountry());
			sellerSession.setBrandEmail(brand.getBrandEmail());
			sellerSession.setBrandId(brand.getBrandId());
			sellerSession.setBrandName(brand.getBrandName());
			sellerSession.setBrandPhone(brand.getBrandPhone());
			sellerSession.setBrandState(brand.getBrandState());
			sellerSession.setBrandStatus(brand.getBrandStatus());
			sellerSession.setPassword(brand.getPassword());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(brand.getCreationDate()!=null){
			sellerSession.setCreationDate(sdf.format(brand.getCreationDate()));
			}
		}
		return sellerSession;
	}
	

public PolicyBrandResponse getTopPolicyBrand() {
	PolicyBrand brand = sellerDao.getTopPolicyBrand();
	PolicyBrandResponse sellerSession = new PolicyBrandResponse();
	if(brand != null){
		sellerSession = new PolicyBrandResponse();
		sellerSession.setBrandAddress(brand.getBrandAddress());
		sellerSession.setBrandCity(brand.getBrandCity());
		sellerSession.setBrandCountry(brand.getBrandCountry());
		sellerSession.setBrandEmail(brand.getBrandEmail());
		sellerSession.setBrandId(brand.getBrandId());
		sellerSession.setBrandName(brand.getBrandName());
		sellerSession.setBrandPhone(brand.getBrandPhone());
		sellerSession.setBrandState(brand.getBrandState());
		sellerSession.setBrandStatus(brand.getBrandStatus());
		sellerSession.setPassword(brand.getPassword());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if(brand.getCreationDate()!=null){
		sellerSession.setCreationDate(sdf.format(brand.getCreationDate()));
		}
	}
	return sellerSession;
}
	
	public boolean updateSellerProfile(PolicyBrandRequest brandRequest) throws RestLogicalErrorException{
		PolicyBrand brand = new PolicyBrand();
		brand.setBrandId(brandRequest.getBrandId());
		brand.setBrandAddress(brandRequest.getBrandAddress());
		brand.setBrandCity(brandRequest.getBrandCity());
		brand.setBrandCountry(brandRequest.getBrandCountry());
		brand.setBrandEmail(brandRequest.getBrandEmail());
		brand.setBrandName(brandRequest.getBrandName());
		brand.setBrandPhone(brandRequest.getBrandPhone());
		brand.setBrandState(brandRequest.getBrandState());
		brand.setBrandStatus(brandRequest.getBrandStatus());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if(brandRequest.getCreationDate()!=null){
			try {
				brand.setCreationDate(sdf.parse(brandRequest.getCreationDate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		brand.setPassword(brandRequest.getPassword());
		if(!sellerDao.updateSellerProfile(brandRequest.getBrandId(), brandRequest.getBrandAddress(),
				brandRequest.getBrandCity(),brandRequest.getBrandCountry(),brandRequest.getBrandEmail(), 
				brandRequest.getBrandPhone(), brandRequest.getBrandState())){
			throw new RestLogicalErrorException("Seller Profile can not be Updated!");
		}
		return true;	
	}
	
	public boolean updateOrderHistory(long orderId) throws RestLogicalErrorException{
		Date completiondate = new Date();
		String orderStatus = "Completed";
		if(!sellerDao.updateOrderHistory(orderId, completiondate, orderStatus)){
			throw new RestLogicalErrorException("Order can not be created!");
		}
		return true;

	}
	
	public boolean createPolicyPlan(PolicyPlanResponse planRequest, int brandId, int subCategoryId) throws RestLogicalErrorException{
		PolicyPlan plan = new PolicyPlan();
		Date creationDate = new Date();
		plan.setCreationDate(creationDate);		
		plan.setMaxEntryAge(planRequest.getMaxEntryAge());
		plan.setMinEntryAge(planRequest.getMinEntryAge());
		plan.setPlanDesc(planRequest.getPlanDesc());
		plan.setPlanFeatures(planRequest.getPlanFeatures());
		plan.setPlanMaturity(planRequest.getPlanMaturity());
		plan.setPlanName(planRequest.getPlanName());
		//plan.setPolicySubCategory(planRequest.getPolicySubCategory());
		plan.setPremiumAmount(planRequest.getPremiumAmount());
		plan.setSumAssured(planRequest.getSumAssured());
		if(sellerDao.getPolicyPlanList(brandId) != null && sellerDao.policyNames(brandId).contains(planRequest.getPlanName())){
			throw new RestLogicalErrorException("plan already exists!!");
		}else{
		if(!sellerDao.createPolicyPlan(plan, brandId, subCategoryId)){
			throw new RestLogicalErrorException("plan can not be created!");
		}
		}
		return true;

	}
	
	public ArrayList<PolicyPlanResponse> getPolicyPlanList(int brandId){
		ArrayList<PolicyPlan> plans = sellerDao.getPolicyPlanList(brandId);
		ArrayList<PolicyPlanResponse> planList = new ArrayList<PolicyPlanResponse>();
		if(plans!=null && plans.size()>0){
			for(PolicyPlan plan : plans){
				PolicyPlanResponse planResponse = new PolicyPlanResponse();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				if(plan.getCreationDate()!=null){
				planResponse.setCreationDate(sdf.format(plan.getCreationDate()));
				}
				planResponse.setMaxEntryAge(plan.getMaxEntryAge());
				planResponse.setMinEntryAge(plan.getMinEntryAge());
				planResponse.setPlanDesc(plan.getPlanDesc());
				planResponse.setPlanId(plan.getPlanId());
				planResponse.setPlanMaturity(plan.getPlanMaturity());
				planResponse.setPlanFeatures(plan.getPlanFeatures());
				planResponse.setPlanName(plan.getPlanName());
				planResponse.setPremiumAmount(plan.getPremiumAmount());
				planResponse.setSumAssured(plan.getSumAssured());
				planResponse.setSubCategoryId(plan.getPolicySubCategory().getSubCategoryId());
				planList.add(planResponse);
			}
		}
		return planList;
	}
	
	public boolean updatePolicyPlan(PolicyPlanResponse planRequest) throws RestLogicalErrorException{
		PolicyPlan plan = new PolicyPlan();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if(planRequest.getCreationDate()!=null){
		try {
			plan.setCreationDate(sdf.parse(planRequest.getCreationDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		plan.setMaxEntryAge(planRequest.getMaxEntryAge());
		plan.setMinEntryAge(planRequest.getMinEntryAge());
		plan.setPlanDesc(planRequest.getPlanDesc());
		plan.setPlanFeatures(planRequest.getPlanFeatures());
		plan.setPlanMaturity(planRequest.getPlanMaturity());
		plan.setPlanName(planRequest.getPlanName());
		//plan.setPolicySubCategory(planRequest.getPolicySubCategory());
		plan.setPremiumAmount(planRequest.getPremiumAmount());
		plan.setSumAssured(planRequest.getSumAssured());
		if(!sellerDao.updatePolicyPlan(plan)){
			throw new RestLogicalErrorException("plan can not be updated!");
		}
		return true;	
	}
	
	public boolean deletePolicyPlan(int planId) throws RestLogicalErrorException{
		/*PolicyPlan plan = new PolicyPlan();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if(planRequest.getCreationDate()!=null){
		try {
			plan.setCreationDate(sdf.parse(planRequest.getCreationDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		plan.setMaxEntryAge(planRequest.getMaxEntryAge());
		plan.setMinEntryAge(planRequest.getMinEntryAge());
		plan.setPlanDesc(planRequest.getPlanDesc());
		plan.setPlanFeatures(planRequest.getPlanFeatures());
		plan.setPlanMaturity(planRequest.getPlanMaturity());
		plan.setPlanName(planRequest.getPlanName());
		//plan.setPolicySubCategory(planRequest.getPolicySubCategory());
		plan.setPremiumAmount(planRequest.getPremiumAmount());
		plan.setSumAssured(planRequest.getSumAssured());*/
		if(!sellerDao.deletePolicyPlan(planId)){
			throw new RestLogicalErrorException("plan can not be deleted!");
		}
		return true;	
	}
		
	public ArrayList<PolicyOrderHistoryResponse> getOrderList(int brandId){
		ArrayList<PolicyOrderHistory> orderList = sellerDao.getOrderList(brandId);
		ArrayList<PolicyOrderHistoryResponse> orderListResponse =new ArrayList<PolicyOrderHistoryResponse>();
		if(orderList!=null && orderList.size()>0){
			for (PolicyOrderHistory order : orderList){
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
				PolicyOrderHistoryResponse orderResponse = new PolicyOrderHistoryResponse(order.getOrderId(),order.getRequesterName(),
						order.getRequesterEmail(), order.getRequesterPhone(), order.getOrderStatus(),
						order.getOrderCreationDate()!= null ? sdf.format(order.getOrderCreationDate()) :null
								, order.getOrderCompletionDate() !=null ? sdf.format(order.getOrderCompletionDate()) : null );
				orderListResponse.add(orderResponse);
			}
		}
		return orderListResponse;
	}

}
