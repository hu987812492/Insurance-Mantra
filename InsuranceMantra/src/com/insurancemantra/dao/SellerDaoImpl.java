package com.insurancemantra.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurancemantra.entity.PolicyBrand;
import com.insurancemantra.entity.PolicyOrderHistory;
import com.insurancemantra.entity.PolicyPlan;
import com.insurancemantra.entity.PolicySubCategory;
import com.insurancemantra.entity.PolicyUser;

@Repository
public class SellerDaoImpl implements ISellerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public boolean registerBrand(PolicyBrand brand) {
		Session session = sessionFactory.getCurrentSession();
		session.save(brand);
		//session.close();
		return true;
	}

	@Override
	@Transactional
	public ArrayList<String> getBrandList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PolicyBrand");

		ArrayList<String> brandList = new ArrayList<>();

		List<PolicyBrand> brands = (List<PolicyBrand>) query.list();

		for(PolicyBrand brand : brands){
			brandList.add(brand.getBrandName());
		}
		//session.close();
		return brandList;
	}

	@Override
	@Transactional
	public PolicyBrand validateBrandLogin(String brandName, String password) {
		Session session = sessionFactory.getCurrentSession();
		PolicyBrand brand = null;
		Query query = session.createQuery(" from PolicyBrand where brandName=:un and password=:pass) ");
		query.setString("un", brandName);
		query.setString("pass", password);
		
		List<PolicyBrand> brands = (List<PolicyBrand>) query.list();
		if ( brands != null && brands.size() > 0) {
			brand = brands.get(0);	
			//session.close();
		return brand;
		}
		//session.close();
		return null;
	}

	@Override
	@Transactional
	public PolicyBrand getSellerProfileDetail(String brandName) {
		Session session = sessionFactory.getCurrentSession();
		PolicyBrand brand = null;
		Query query = session.createQuery(" from PolicyBrand where brandName=:un ");
		query.setString("un", brandName);
		
		List<PolicyBrand> brands = (List<PolicyBrand>) query.list();
		if ( brands != null && brands.size() > 0) {
			brand = brands.get(0);	
			//session.close();
		return brand;
		}
		return null;
	}

	@Override
	@Transactional
	public boolean updateSellerProfile(int brandId,  String brandAddress,
			String brandCity,String brandCountry,String brandEmail, 
			int brandPhone, String brandState) {
		Session session = sessionFactory.getCurrentSession();
		PolicyBrand brand = (PolicyBrand)session.get(PolicyBrand.class, brandId);
		brand.setBrandId(brandId);
		brand.setBrandAddress(brandAddress);
		brand.setBrandCity(brandCity);
		brand.setBrandCountry(brandCountry);
		brand.setBrandEmail(brandEmail);
		brand.setBrandPhone(brandPhone);
		brand.setBrandState(brandState);
		session.saveOrUpdate(brand);
		//session.close();
		return true;		
	}

	@Override
	@Transactional
	public boolean updateOrderHistory(long orderId, Date completionDate, String status) {
		Session session = sessionFactory.getCurrentSession();
		PolicyOrderHistory order = (PolicyOrderHistory)session.get(PolicyOrderHistory.class, orderId);
		order.setOrderCompletionDate(completionDate);
		order.setOrderStatus(status);		
		session.saveOrUpdate(order);
		//session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean createPolicyPlan(PolicyPlan plan, int brandId, int subCategoryId) {
		Session session = sessionFactory.getCurrentSession();
		PolicyBrand seller = (PolicyBrand) session.load(PolicyBrand.class, brandId);
		PolicySubCategory subCategory = (PolicySubCategory) session.load(PolicySubCategory.class, subCategoryId);
		plan.setPolicyBrand(seller);
		plan.setPolicySubCategory(subCategory);
		subCategory.getPolicyPlans().add(plan);
		seller.getPolicyPlans().add(plan);
		session.save(plan);
		//session.close();
		return true;
	}

	@Override
	@Transactional
	public ArrayList<PolicyPlan> getPolicyPlanList(int brandId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PolicyPlan where policyBrand=:id");
		query.setInteger("id", brandId);
		ArrayList<PolicyPlan> plans = (ArrayList<PolicyPlan>) query.list();	
		//session.close();
		return plans;
	}
	
	@Override
	@Transactional
	public ArrayList<String> policyNames(int brandId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" select planName from PolicyPlan where policyBrand=:id");
		query.setInteger("id", brandId);
		ArrayList<String> plans = (ArrayList<String>) query.list();	
		//session.close();
		return plans;
	}

	@Override
	@Transactional
	public boolean updatePolicyPlan(PolicyPlan plan) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(plan);
		//session.close();
		return true;

	}

	@Override
	@Transactional
	public boolean deletePolicyPlan(int planId) {
		Session session = sessionFactory.getCurrentSession();
		PolicyPlan plan = (PolicyPlan) session.load(PolicyPlan.class, planId);
		session.delete(plan);
		//session.close();
		return true;

	}
	
	@Override
	@Transactional
	public ArrayList<PolicyOrderHistory> getOrderList(int brandId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PolicyOrderHistory where policyBrand=:id");
		query.setInteger("id", brandId);
		ArrayList<PolicyOrderHistory> orderList = (ArrayList<PolicyOrderHistory>) query.list();
		//session.close();
		return orderList;
		
	}

	@Override
	@Transactional
	public PolicyBrand getTopPolicyBrand() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT brand_id,count(*) FROM policy_order_history GROUP BY brand_id LIMIT 1 ");		
		List plans =  query.list();
		Integer brandName = null;
		
		for (Iterator it = plans.iterator(); it.hasNext(); ) {
		    Object[] myResult = (Object[]) it.next();
		    brandName = (Integer) myResult[0];		   
		}
		
		PolicyBrand brand = (PolicyBrand)session.get(PolicyBrand.class, brandName);
		/*Query query1 = session.createQuery(" from PolicyBrand where brandId=:id ");
		query1.setInteger("id", brandName);	
		List<PolicyBrand> brands =  (List<PolicyBrand>) query.list();
		if(brands!=null && brands.size()>0){
			brand = brands.get(0);
			return brand;
		}*/
		//session.close();
		return brand;
	}

}
