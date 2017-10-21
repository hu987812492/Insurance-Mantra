package com.insurancemantra.rest.controller;

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

import com.insurancemantra.controller.data.AdminTotalCounts;
import com.insurancemantra.controller.data.PolicyCategoryResponse;
import com.insurancemantra.controller.data.PolicySubCategoryResponse;
import com.insurancemantra.controller.data.PolicyUserRequest;
import com.insurancemantra.controller.data.ResponseError;
import com.insurancemantra.controller.data.RestLogicalErrorException;
import com.insurancemantra.interceptor.SecurityInterceptor;
import com.insurancemantra.service.AdminService;

@Path("/admin")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminController {
	
	Logger log = Logger.getLogger(AdminController.class);
	
	@Autowired AdminService adminService;
	
	@GET
	@Path("/totalCounts")
	@RolesAllowed({SecurityInterceptor.ROLE_ADMIN})
	public Response getTotalCounts() {
		AdminTotalCounts totalAdminCounts = adminService.getTotalCounts();
		log.debug("Preparing Admin dashboard");
		
		return Response.ok().entity(totalAdminCounts).build();

	}
		
	@POST
	@Path("/categories")
	@RolesAllowed({SecurityInterceptor.ROLE_ADMIN})
	public Response createCategory(PolicyCategoryResponse categoryRequest) {
		boolean flag = false;
		try {
			flag = adminService.createCategory(categoryRequest);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(flag){
			log.debug("Policy category created successfull!!");
			return Response.ok().build();
		}else{
			log.error("Policy Category creation failed.");
			return Response.ok().status(422).entity(new ResponseError("Category Creation Failed")).build();
		}

	}
	
	@PUT
	@Path("/categories/update")
	@RolesAllowed({SecurityInterceptor.ROLE_ADMIN})
	public Response updateCategory(PolicyCategoryResponse categoryRequest) {
		boolean flag = false;
		try {
			flag = adminService.updateCategory(categoryRequest);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(flag){
			log.debug("Policy category updated.");
			return Response.ok().build();
		}else{
			log.error("Category updation Failed");
			return Response.ok().status(422).entity(new ResponseError("Category updation Failed")).build();
		}

	}
	
	
	@POST
	@Path("/categories/{categoryId}/delete")
	@PermitAll
	//@RolesAllowed({SecurityInterceptor.ROLE_ADMIN})
	public Response deleteCategory(@PathParam("categoryId") int categoryId) {
		boolean flag = false;
		try {
			flag = adminService.deleteCategory(categoryId);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(flag){
			log.debug("Policy category deleted.");
			return Response.ok().build();
		}else{
			log.error("Category updation Failed");
			return Response.ok().status(422).entity(new ResponseError("Category updation Failed")).build();
		}

	}
	
	@POST
	@Path("/{categoryId}/subCategories")
	@RolesAllowed({SecurityInterceptor.ROLE_ADMIN})
	public Response createSubCategory(PolicySubCategoryResponse subCategoryRequest, @PathParam("categoryId") int categoryId) {
		boolean flag = false;
		try {
			flag = adminService.createSubCategory(subCategoryRequest,categoryId);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(flag){
			log.debug("Policy sub-category created successfully.");
			return Response.ok().build();
		}else{
			log.error("Policy sub-category creation failed.");
			return Response.ok().status(422).entity(new ResponseError("SubCategory Creation Failed")).build();
		}

	}
	
	@PUT
	@Path("/subCategories/update")
	@RolesAllowed({SecurityInterceptor.ROLE_ADMIN})
	public Response updateSubCategory(PolicySubCategoryResponse subCategoryRequest) {
		boolean flag = false;
		try {
			flag = adminService.updateSubCategory(subCategoryRequest);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(flag){
			log.debug("Policy sub-category updated.");
			return Response.ok().build();
		}else{
			log.error("Policy sub-category not updated.");
			return Response.ok().status(422).entity(new ResponseError("SubCategory updation Failed")).build();
		}

	}
	
	@POST
	@Path("/subCategories/{subCategoryId}/delete")
	@RolesAllowed({SecurityInterceptor.ROLE_ADMIN})
	public Response deleteSubCategory(@PathParam("subCategoryId") int subCategoryId) {
		boolean flag = false;
		try {
			flag = adminService.deleteSubCategory(subCategoryId);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(flag){
			log.debug("Policy sub-category deleted.");
			return Response.ok().build();
		}else{
			log.error("Policy sub-category not deleted");
			return Response.ok().status(422).entity(new ResponseError("SubCategory deletion Failed")).build();
		}

	}
	
	@DELETE
	@Path("/customer/{userId}/delete")
	//@RolesAllowed({SecurityInterceptor.ROLE_ADMIN})
	public Response deleteCustomer(@PathParam("userId") int userId) {
		boolean flag = false;
		try {
			flag = adminService.deleteCustomer(userId);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(flag){
			log.debug("Customer deleted successfully.");
			return Response.ok().build();
		}else{
			log.error("Customer not deleted");
			return Response.ok().status(422).entity(new ResponseError("customer deletion Failed")).build();
		}

	}
	
	@POST
	@Path("/{brandId}/update")
	@RolesAllowed({SecurityInterceptor.ROLE_ADMIN, SecurityInterceptor.ROLE_MERCHANT})
		public Response updateSellerStatus(@PathParam("brandId") String orderId){
		boolean flag = false;
		try {
			flag = adminService.updateSellerStatus(Integer.parseInt(orderId));
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			log.debug("Policy Plan updated.");
			return Response.ok().build();
		}else{
			log.error("Policy Plan not updated.");
			return Response.ok().status(422).entity(new ResponseError("Order updation Failed")).build();

		}		
	}

}
