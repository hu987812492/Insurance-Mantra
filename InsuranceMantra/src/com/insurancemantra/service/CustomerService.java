package com.insurancemantra.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancemantra.controller.data.PolicyOrderHistoryRequest;
import com.insurancemantra.controller.data.PolicyOrderHistoryResponse;
import com.insurancemantra.controller.data.PolicyUserRequest;
import com.insurancemantra.controller.data.PolicyUserResponse;
import com.insurancemantra.controller.data.RestLogicalErrorException;
import com.insurancemantra.dao.ICustomerDao;
import com.insurancemantra.entity.PolicyOrderHistory;
import com.insurancemantra.entity.PolicyUser;

@Service
public class CustomerService {

	@Autowired 
	private ICustomerDao customerDao;

	public boolean registerCustomer(PolicyUserRequest userRequest) throws RestLogicalErrorException{

		if(userRequest.getUserName() ==null || userRequest.getPassword() ==null){

			throw new RestLogicalErrorException("Registration Parameters incomplete.");
		}else{
			
			//Simulation a database Request
			PolicyUser user = new PolicyUser();
			Date creationDate = new Date();
			user.setCreationDate(creationDate);
			user.setName(userRequest.getName());
			
			 try {
				TrippleDes td= new TrippleDes();
				user.setPassword(td.encrypt(userRequest.getPassword()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new RestLogicalErrorException("password may not set properly ");
			}
			 
			
			user.setUserAddress(userRequest.getUserAddress());
			user.setUserCity(userRequest.getUserCity());
			user.setUserCountry(userRequest.getUserCountry());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			try {
				user.setUserDob(sdf.parse(userRequest.getUserDob()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setUserEmail(userRequest.getUserEmail());
			user.setUserName(userRequest.getUserName());
			user.setUserPhone(userRequest.getUserPhone());
			user.setUserRole("customer");
			user.setUserState(userRequest.getUserState());
			if(customerDao.getUserList().contains(userRequest.getUserName())){
				throw new RestLogicalErrorException("Duplicate User");
			}else{
			if(!customerDao.registerCustomer(user)){
				throw new RestLogicalErrorException("User Registration failed");

			}
			}
		}
		return true;

	}

	/*public PolicyUserResponse validateUser(String username,String password) throws RestLogicalErrorException{

		PolicyUser user = customerDao.validateCustomerLogin(username, password);

		PolicyUserResponse userSession = null;

		if(user != null){

			userSession = new PolicyUserResponse();
			userSession.setUserId(user.getUserId());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			userSession.setCreationDate(sdf.format(user.getCreationDate()));
			userSession.setName(user.getName());
			userSession.setPassword(user.getPassword());
			userSession.setUserAddress(user.getUserAddress());
			userSession.setUserCity(user.getUserCity());
			userSession.setUserCountry(user.getUserCountry());
			userSession.setUserDob(sdf.format(user.getUserDob()));
			userSession.setUserEmail(user.getUserEmail());
			userSession.setUserName(user.getUserName());
			userSession.setUserPhone(user.getUserPhone());
			userSession.setUserRole(user.getUserRole());
			userSession.setUserState(user.getUserState());

		}else{
			RestLogicalErrorException authResponseErr = new RestLogicalErrorException("Invalid User");
			throw authResponseErr;
		}	
		return userSession;
	}*/
	
	public ArrayList<String> getUserList(){
		ArrayList<String> users = customerDao.getUserList();
		ArrayList<String> userList = null;
		if(users!=null){
			userList = users;
		}
		return userList;
	}

	public PolicyUserResponse getUserProfileDetail(String username){
		PolicyUser user = customerDao.getUserProfileDetail(username);
		PolicyUserResponse userResponse = null;
		if(user!=null){
			String dob = null;
			String creationDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
			if(user.getUserDob()!=null){
				dob = sdf.format(user.getUserDob());				
			}
			if(user.getCreationDate()!=null){
				creationDate = sdf.format(user.getCreationDate());
			}
			userResponse = new PolicyUserResponse(user.getUserId(), user.getUserName(), user.getPassword(), user.getUserEmail(),
					user.getUserRole(), dob, user.getUserAddress(), user.getUserCity(), user.getUserState(), user.getUserCountry(),
					user.getUserPhone(), creationDate, user.getName());
		}
		return userResponse;
	}
	
	public boolean createOrder(PolicyOrderHistoryRequest orderRequest, int userId, int planId, int brandId) throws RestLogicalErrorException{
		PolicyOrderHistory order = new PolicyOrderHistory();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			order.setOrderCompletionDate(sdf.parse(orderRequest.getOrderCompletionDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date creationDate = new Date();
		order.setOrderCreationDate(creationDate);
		order.setOrderStatus("created");
		try {
			order.setRequesterDob(sdf.parse(orderRequest.getRequesterDob()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order.setRequesterEmail(orderRequest.getRequesterEmail());
		order.setRequesterIncome(orderRequest.getRequesterIncome());
		order.setRequesterName(orderRequest.getRequesterName());
		order.setRequesterPhone(orderRequest.getRequesterPhone());
		//order.setPolicyPlan(orderRequest.getPolicyPlan());
		if(!customerDao.createOrder(order, userId, planId, brandId)){
			throw new RestLogicalErrorException("Order can not be created!");
		}
		return true;

	}
	
	public PolicyOrderHistoryResponse getOrder(long orderId){
		PolicyOrderHistory order = customerDao.getOrder(orderId);
		PolicyOrderHistoryResponse orderResponse = null;
		if(order!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
			orderResponse = new PolicyOrderHistoryResponse(order.getOrderId(),order.getRequesterName(),
					order.getRequesterEmail(), order.getRequesterPhone(), sdf.format(order.getRequesterDob()), order.getRequesterIncome(), order.getOrderStatus(),
					sdf.format(order.getOrderCreationDate()), sdf.format(order.getOrderCompletionDate()));
		}
		return orderResponse;
	}
	
	public boolean updateCustomerProfile(PolicyUserRequest userRequest) throws RestLogicalErrorException{
		PolicyUser user = new PolicyUser();
		user.setUserId(userRequest.getUserId());
		user.setUserAddress(userRequest.getUserAddress());
		user.setUserCity(userRequest.getUserCity());
		user.setUserCountry(userRequest.getUserCountry());
		user.setUserEmail(userRequest.getUserEmail());
		user.setUserPhone(userRequest.getUserPhone());
		user.setUserState(userRequest.getUserState());
		if(!customerDao.updateCustomerProfile(userRequest.getUserId(),userRequest.getUserAddress(),
				userRequest.getUserCity(),userRequest.getUserCountry(), userRequest.getUserEmail(),
				userRequest.getUserPhone(),	userRequest.getUserState())){
			throw new RestLogicalErrorException("User Profile can not be updated!");
		}
		return true;	
	}
	
	public ArrayList<PolicyOrderHistoryResponse> getOrderList(int userId){
		ArrayList<PolicyOrderHistory> orderList = customerDao.getOrderList(userId);
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
