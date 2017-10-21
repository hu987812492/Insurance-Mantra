package com.insurancemantra.rest.controller;

import java.util.ArrayList;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.insurancemantra.controller.data.PolicyBrandRequest;
import com.insurancemantra.controller.data.PolicyBrandResponse;
import com.insurancemantra.controller.data.PolicyOrderHistoryRequest;
import com.insurancemantra.controller.data.PolicyOrderHistoryResponse;
import com.insurancemantra.controller.data.PolicyPlanResponse;
import com.insurancemantra.controller.data.ResponseError;
import com.insurancemantra.controller.data.RestLogicalErrorException;
import com.insurancemantra.entity.PolicyPlan;
import com.insurancemantra.interceptor.SecurityInterceptor;
import com.insurancemantra.service.SellerService;

@Path("/seller")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SellerController {
	
	Logger log = Logger.getLogger(SellerController.class);
	
	@Autowired 
	private SellerService sellerService;
	
	@POST
	@Path("/registration")
	//@RolesAllowed({SecurityInterceptor.ROLE_MERCHANT})
	@PermitAll
		public Response registerBrand(PolicyBrandRequest brandRequest){
		try {
			if(sellerService.registerBrand(brandRequest)){
				log.debug("Policy Brand Registered Successfully");
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			log.error("Policy Brand registration failed");
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}
		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();		
	}
	
	@GET
	@Path("/brands")
	@PermitAll
	public Response getBrandList() {

		ArrayList<String> brands = sellerService.getBrandList();
		
		if (brands == null) {
			log.error("No sellers found");
			return Response.ok().status(422).entity(new ResponseError("sellers Not Found")).build();

		}
		return Response.ok().entity(brands).build();

	}
	
	/*@POST
	@Path("/brandAuth")
	public Response validateBrandLogin(String username,String password){
		
		PolicyBrandResponse sellerSession = null;
		try {
			sellerSession = sellerService.validateBrandLogin(username,password);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			return Response.ok().entity(e.getResponseError()).build();

		}
		return Response.ok().entity(sellerSession).build();
	}*/
	
	@GET
	@Path("/{brandName}")
	@RolesAllowed({SecurityInterceptor.ROLE_MERCHANT, SecurityInterceptor.ROLE_ADMIN})
	public Response getSellerProfileDetail(@PathParam("brandName") String brandName) {

		PolicyBrandResponse brand = sellerService.getSellerProfileDetail(brandName);

		if (brand == null) {
			log.error("Seller profile not found");
			return Response.ok().status(422).entity(new ResponseError("seller Profile Not Found")).build();

		}
		return Response.ok().entity(brand).build();
	}
	
	@PUT
	@Path("/update")
	@RolesAllowed({SecurityInterceptor.ROLE_MERCHANT, SecurityInterceptor.ROLE_ADMIN})
		public Response updateSellerProfile(PolicyBrandRequest brandRequest){
		boolean flag = false;
		try {
			flag = sellerService.updateSellerProfile(brandRequest);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			log.debug("Seller profile updated");
			return Response.ok().build();
		}else{
			log.error("seller profile not updated");
			return Response.ok().status(422).entity(new ResponseError("Seller Profile updation Failed")).build();

		}
	}
	
	@POST
	@Path("/{orderId}/update")
	@RolesAllowed({SecurityInterceptor.ROLE_MERCHANT, SecurityInterceptor.ROLE_MERCHANT})
		public Response updateOrderHistory(@PathParam("orderId") String orderId){
		boolean flag = false;
		try {
			flag = sellerService.updateOrderHistory(Long.parseLong(orderId));
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			log.debug(orderId+" Order updated successfully");
			return Response.ok().build();
		}else{
			log.error("Order updation Failed");
			return Response.ok().status(422).entity(new ResponseError("Order updation Failed")).build();

		}		
	}
	
	@POST
	@Path("/{brandId}/sub-category/{subCategoryId}/plan")
	@RolesAllowed({SecurityInterceptor.ROLE_MERCHANT, SecurityInterceptor.ROLE_ADMIN})
		public Response createPolicyPlan(PolicyPlanResponse planRequest,@PathParam("brandId") String brandId, @PathParam("subCategoryId") String subCategoryId){
		boolean flag = false;
		try {

			flag = sellerService.createPolicyPlan(planRequest, Integer.parseInt(brandId), Integer.parseInt(subCategoryId));
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			log.debug("Policy plan created");
			return Response.ok().build();
		}else{
			log.error("Plan creation failed");
			return Response.ok().status(422).entity(new ResponseError("Plan creation Failed")).build();

		}	
	}
	
	@GET
	@Path("/{brandId}/plans")
	@RolesAllowed({ SecurityInterceptor.ROLE_MERCHANT, SecurityInterceptor.ROLE_ADMIN})
	public Response getPolicyPlanList(@PathParam("brandId") String brandId) {

		ArrayList<PolicyPlanResponse> plans = sellerService.getPolicyPlanList(Integer.parseInt(brandId));		
		if (plans == null) {
			log.error("No plans found");
			return Response.ok().status(422).entity(new ResponseError("plans Not Found")).build();
		}
		return Response.ok().entity(plans).build();
	}
	
	@PUT
	@Path("/{planId}/update")
	@RolesAllowed({SecurityInterceptor.ROLE_MERCHANT, SecurityInterceptor.ROLE_ADMIN})
	public Response updatePolicyPlan(PolicyPlanResponse planRequest){
	boolean flag = false;
	try {
		flag = sellerService.updatePolicyPlan(planRequest);
	} catch (RestLogicalErrorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(flag){
		log.debug("Policy Plan updated");
		return Response.ok().build();
	}else{
		log.error("plan updation failed");
		return Response.ok().status(422).entity(new ResponseError("Plan updation Failed")).build();
	}	
}
	
	@POST
	@Path("/{planId}")
	@PermitAll
	//@RolesAllowed({SecurityInterceptor.ROLE_MERCHANT, SecurityInterceptor.ROLE_ADMIN})
		public Response deletePolicyPlan(@PathParam("planId") String planId){
		boolean flag = false;
		try {
			flag = sellerService.deletePolicyPlan(Integer.parseInt(planId));
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			log.debug("Policy plan deleted");
			return Response.ok().build();
		}else{
			log.error("policy plan deletion failed");
			return Response.ok().status(422).entity(new ResponseError("Plan deletion Failed")).build();
		}			
	}
	
	@GET
	@Path("/{brandId}/orders")
	@RolesAllowed({ SecurityInterceptor.ROLE_MERCHANT})
		public Response getOrderList(@PathParam("brandId") String brandId){
		ArrayList<PolicyOrderHistoryResponse> orderList = sellerService.getOrderList(Integer.parseInt(brandId));		
		if(orderList == null){
			log.error("No order list found");
			return Response.ok().status(422).entity(new ResponseError("No orderlist found")).build();
		}
		return Response.ok().entity(orderList).build();
	}
	
	@GET
	@Path("/topBrand")
	@PermitAll
		public Response getTopPolicyBrand(){
		PolicyBrandResponse brand = sellerService.getTopPolicyBrand();		
		if(brand == null){
			log.error("policy brand table is empty");
			return Response.ok().status(422).entity(new ResponseError("No Policy brands Found")).build();
		}
		return Response.ok().entity(brand).build();
	}

}
