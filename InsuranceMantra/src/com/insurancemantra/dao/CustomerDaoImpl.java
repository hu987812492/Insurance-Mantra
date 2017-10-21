package com.insurancemantra.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurancemantra.entity.PolicyBrand;
import com.insurancemantra.entity.PolicyOrderHistory;
import com.insurancemantra.entity.PolicyPlan;
import com.insurancemantra.entity.PolicySubCategory;
import com.insurancemantra.entity.PolicyUser;

@Repository
public class CustomerDaoImpl implements ICustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public boolean registerCustomer(PolicyUser user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		////session.close();
		return true;

	}

	@Override
	@Transactional
	public PolicyUser validateCustomerLogin(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		PolicyUser user = null;
		Query query = session.createQuery(" from PolicyUser where userName=:un and password=:pass) ");
		query.setString("un", username);
		query.setString("pass", password);
		
		List<PolicyUser> users = (List<PolicyUser>) query.list();
		if ( users != null && users.size() > 0) {
		user = users.get(0);		
		return user;
		}
		
		////session.close();
		return null;
	}

	@Override
	@Transactional
	public PolicyUser getUserProfileDetail(String username) {
		Session session = sessionFactory.getCurrentSession();
		PolicyUser user = null;
		Query query = session.createQuery(" from PolicyUser where userName=:un ");
		query.setString("un", username);
		
		List<PolicyUser> users = (List<PolicyUser>) query.list();
		if ( users != null && users.size() > 0) {
		user = users.get(0);		
		return user;
		}
		////session.close();
		return null;
	}

	@Override
	@Transactional
	public PolicyOrderHistory getOrder(long orderId) {
		Session session = sessionFactory.getCurrentSession();
		PolicyOrderHistory order = (PolicyOrderHistory) session.get(PolicyOrderHistory.class, orderId);
		////session.close();
		return order;
	}

	@Override
	@Transactional
	public boolean updateCustomerProfile(int userId,String userAddress,
			String userCity,String userCountry, String userEmail,
			int userPhone,	String userState) {
		Session session = sessionFactory.getCurrentSession();
		PolicyUser user = (PolicyUser) session.get(PolicyUser.class, userId);
		user.setUserAddress(userAddress);
		user.setUserCity(userCity);
		user.setUserCountry(userCountry);
		user.setUserEmail(userEmail);
		user.setUserPhone(userPhone);
		user.setUserState(userState);
		
		session.saveOrUpdate(user);
		//session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean createOrder(PolicyOrderHistory order, int userId, int planId, int brandId) {
		Session session = sessionFactory.getCurrentSession();
		//Transaction tr = session.beginTransaction();
		PolicyUser user = (PolicyUser) session.get(PolicyUser.class, userId);
		PolicyPlan plan = (PolicyPlan) session.get(PolicyPlan.class, planId);
		PolicyBrand brand = (PolicyBrand) session.get(PolicyBrand.class, brandId);
		order.setPolicyUser(user);
		order.setPolicyBrand(brand);
		order.setPolicyPlan(plan);
		user.getPolicyOrderHistories().add(order);
		session.save(order);
		//tr.commit();
		
		//session.close();
		return true;

	}

	@Override
	@Transactional
	public ArrayList<String> getUserList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PolicyUser");

		ArrayList<String> userList = new ArrayList<>();

		List<PolicyUser> users = (List<PolicyUser>) query.list();

		for(PolicyUser user : users){
			userList.add(user.getUserName());
		}
		//session.close();
		return userList;
	}
	
	@Override
	@Transactional
	public ArrayList<PolicyOrderHistory> getOrderList(int userId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PolicyOrderHistory where policyUser=:id");
		query.setInteger("id", userId);
		ArrayList<PolicyOrderHistory> orderList = (ArrayList<PolicyOrderHistory>) query.list();
		//session.close();
	
		return orderList;
		
	}

}
