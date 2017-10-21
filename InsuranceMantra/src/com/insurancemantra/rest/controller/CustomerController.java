package com.insurancemantra.rest.controller;

import java.util.ArrayList;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
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

import com.insurancemantra.controller.data.PolicyOrderHistoryRequest;
import com.insurancemantra.controller.data.PolicyOrderHistoryResponse;
import com.insurancemantra.controller.data.PolicyUserRequest;
import com.insurancemantra.controller.data.PolicyUserResponse;
import com.insurancemantra.controller.data.ResponseError;
import com.insurancemantra.controller.data.RestLogicalErrorException;
import com.insurancemantra.interceptor.SecurityInterceptor;
import com.insurancemantra.service.CustomerService;

@Path("/customers")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {
	
	Logger log = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@POST
	@Path("/registration")	
	//@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
	@PermitAll
		public Response registerCustomer(PolicyUserRequest userRequest){
		try {
			if(customerService.registerCustomer(userRequest)){
				log.debug(userRequest.getUserName() +"registered as customer");
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			log.error("Customer registration failed");
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}
		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();		
	}
	
	/*@POST
	@Path("/customerAuth")
	public Response validateUser(String username,String password){
		
		PolicyUserResponse userSession = null;
		try {
			userSession = customerService.validateUser(username,password);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			return Response.ok().entity(e.getResponseError()).build();

		}
		return Response.ok().entity(userSession).build();
	}*/
	
	@GET
	@Path("/customers")
	@PermitAll
	public Response getUserList() {

		ArrayList<String> users = customerService.getUserList();
		
		if (users == null) {
			log.error("No customer found");
			return Response.ok().status(422).entity(new ResponseError("Customers Not Found")).build();

		}
		return Response.ok().entity(users).build();

	}
	
	@GET
	@Path("/{username}")
	@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
	public Response getUserProfileDetail(@PathParam("username") String username) {

		PolicyUserResponse user = customerService.getUserProfileDetail(username);

		if (user == null) {
			log.error("Customer profile not found");
			return Response.ok().status(422).entity(new ResponseError("Customer Profile Not Found")).build();
		}
		return Response.ok().entity(user).build();
	}
	
	@POST
	@Path("/{userId}/plan/{planId}/seller/{brandId}/order")
	@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
		public Response createOrder(PolicyOrderHistoryRequest orderRequest,@PathParam("userId") int userId, 
				@PathParam("planId")int planId, @PathParam("brandId") int brandId){
		boolean flag = false;
		try {
			flag = customerService.createOrder(orderRequest, userId, planId, brandId);
		} catch (RestLogicalErrorException e) {
			e.printStackTrace();
		}
		if(flag){
			log.debug("Policy order created");
			return Response.ok().build();
		}else{
			log.error("Order creation Failed");
			return Response.ok().status(422).entity(new ResponseError("Order creation Failed")).build();

		}	
	}
	
	@GET
	@Path("/{username}/orders/{orderId}")
	@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER, SecurityInterceptor.ROLE_MERCHANT})
		public Response getOrder(@PathParam("orderId") long orderId){
		PolicyOrderHistoryResponse order = customerService.getOrder(orderId);		
		if(order == null){
			log.error("No such order found");
			return Response.ok().status(422).entity(new ResponseError("No such order found")).build();
		}
		return Response.ok().entity(order).build();
	}
	
	@PUT
	@Path("/update")
	@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
		public Response updateCustomerProfile(PolicyUserRequest userRequest){
		boolean flag = false;
		try {
			flag = customerService.updateCustomerProfile(userRequest);
		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			log.debug("Customer profile updated");
			return Response.ok().build();
		}else{
			log.error("Customer profile updation failed");
			return Response.ok().status(422).entity(new ResponseError("customer updation Failed")).build();

		}
	}
	
	@GET
	@Path("/{userId}/orders")
	@RolesAllowed({SecurityInterceptor.ROLE_CUSTOMER})
		public Response getOrderList(@PathParam("userId") int userId){
		ArrayList<PolicyOrderHistoryResponse> orderList = customerService.getOrderList(userId);		
		if(orderList == null){
			log.error("No order list for user"+userId);
			return Response.ok().status(422).entity(new ResponseError("No orderlist found")).build();
		}
		return Response.ok().entity(orderList).build();
	}

}
