package com.insurancemantra.rest.controller;

import java.util.ArrayList;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.insurancemantra.controller.data.PolicyBrandResponse;
import com.insurancemantra.controller.data.PolicyCategoryResponse;
import com.insurancemantra.controller.data.PolicyPlanResponse;
import com.insurancemantra.controller.data.PolicySubCategoryResponse;
import com.insurancemantra.controller.data.ResponseError;
import com.insurancemantra.controller.data.RestLogicalErrorException;
import com.insurancemantra.entity.PolicyCategory;
import com.insurancemantra.service.WebsiteFrontService;

@Path("/front")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WebsiteFrontController {
	
	Logger log = Logger.getLogger(AuthenticationController.class);
	
	@Autowired
	private WebsiteFrontService webFrontService;
	
	@GET
	@Path("/categories")
	@PermitAll
		public Response getAllCategories(){
		ArrayList<PolicyCategoryResponse> categories = webFrontService.getAllCategories();		
		if(categories == null){
			log.error("No categories found");
			return Response.ok().status(422).entity(new ResponseError("No Categories Found")).build();
		}
		return Response.ok().entity(categories).build();
	}
	
	@GET
	@Path("/category/sub-category")
	@PermitAll
		public Response getTotalSubCategories(){
		ArrayList<PolicySubCategoryResponse> category = webFrontService.getTotalSubCategories();		
		if(category == null){
			log.error("No sub-categories found");
			return Response.ok().status(422).entity(new ResponseError("No Sub-Categories Found")).build();
		}
		return Response.ok().entity(category).build();
	}
	
	
	@GET
	@Path("/category/{categoryId}")
	@PermitAll
		public Response getAllSubCategories(@PathParam("categoryId") String categoryId){
		PolicyCategoryResponse category = webFrontService.getAllSubCategories(Integer.parseInt(categoryId));		
		if(category == null){
			log.error("No sub-categories found for category "+categoryId);
			return Response.ok().status(422).entity(new ResponseError("No Sub-Categories Found")).build();
		}
		return Response.ok().entity(category).build();
	}
	
	@GET
	@Path("/brand/{brandId}")
	@PermitAll
		public Response getAllPolicyPlanListBrandBased(@PathParam("brandId") String brandId){
		ArrayList<PolicyPlanResponse> plans = webFrontService.getAllPolicyPlanListBrandBased(Integer.parseInt(brandId));		
		if(plans == null){
			log.error("No policy plan found for brand "+brandId);
			return Response.ok().status(422).entity(new ResponseError("No Policy plans Found")).build();
		}
		return Response.ok().entity(plans).build();
	}
	
	@GET
	@Path("/subCategory/{subCategoryId}")
	@PermitAll
		public Response getAllPolicyPlanListSubCategoryBased(@PathParam("subCategoryId") String subCategoryId){
		ArrayList<PolicyPlanResponse> plans = webFrontService.getAllPolicyPlanListSubCategoryBased(Integer.parseInt(subCategoryId));		
		if(plans == null){
			log.error("No policy plan found for sub-category "+subCategoryId);
			return Response.ok().status(422).entity(new ResponseError("No Policy plans Found")).build();
		}
		return Response.ok().entity(plans).build();
	}
	
	@GET
	@Path("/brands")
	@PermitAll
		public Response getAllBrands(){
		ArrayList<PolicyBrandResponse> brands = webFrontService.getAllBrands();		
		if(brands == null){
			log.error("No brands found");
			return Response.ok().status(422).entity(new ResponseError("No Brands Found")).build();
		}
		return Response.ok().entity(brands).build();
	}
	
	@GET
	@Path("/plan/{policyId}")
	@PermitAll
		public Response getPolicyPlanDetail(@PathParam("policyId") String policyId){
		PolicyPlanResponse plan = webFrontService.getPolicyPlanDetail(Integer.parseInt(policyId));		
		if(plan == null){
			log.error("Policy plan "+policyId+" not found");
			return Response.ok().status(422).entity(new ResponseError("No such plan found")).build();
		}
		return Response.ok().entity(plan).build();
	}
	
	@GET
	@Path("/policies")
	@PermitAll
		public Response getAllPolicyPlans(){
		ArrayList<PolicyPlanResponse> plans = webFrontService.getAllPolicyPlans();		
		if(plans == null){
			log.error("No policy plan found");
			return Response.ok().status(422).entity(new ResponseError("No Policy plans Found")).build();
		}
		return Response.ok().entity(plans).build();
	}
	
	@GET
	@Path("/top3policies")
	@PermitAll
		public Response getTop3PolicyPlans(){
		ArrayList<PolicyPlanResponse> plans = webFrontService.getTop3PolicyPlans();		
		if(plans == null){
			log.error("No policy plan found for top 3 policies");
			return Response.ok().status(422).entity(new ResponseError("No Policy plans Found")).build();
		}
		return Response.ok().entity(plans).build();
	}
	
	

}
