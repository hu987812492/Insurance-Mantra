package com.insurancemantra.dao;

import java.util.ArrayList;

import com.insurancemantra.entity.PolicyOrderHistory;
import com.insurancemantra.entity.PolicyUser;

public interface ICustomerDao {
	
	public boolean registerCustomer(PolicyUser user);
	public ArrayList<String> getUserList();
	public PolicyUser validateCustomerLogin(String username, String password);
	public PolicyUser getUserProfileDetail(String username);
	public PolicyOrderHistory getOrder(long orderId);
	public boolean updateCustomerProfile(int userId,String userAddress,
			String userCity,String userCountry, String userEmail,
			int userPhone,	String userState) ;
	public boolean createOrder(PolicyOrderHistory order, int userId, int planId, int brandId);
	public ArrayList<PolicyOrderHistory> getOrderList(int userId);

}
