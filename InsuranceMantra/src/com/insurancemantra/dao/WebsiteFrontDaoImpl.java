package com.insurancemantra.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurancemantra.entity.PolicyBrand;
import com.insurancemantra.entity.PolicyCategory;
import com.insurancemantra.entity.PolicyPlan;
import com.insurancemantra.entity.PolicySubCategory;

@Repository
public class WebsiteFrontDaoImpl implements IWebsiteFrontDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public ArrayList<PolicyCategory> getAllCategories() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PolicyCategory");

		ArrayList<PolicyCategory> categoryList = new ArrayList<>();

		List<PolicyCategory> categories = (List<PolicyCategory>) query.list();

		for(PolicyCategory category : categories){
			PolicyCategory ctgry = new PolicyCategory();
			ctgry.setCategoryDesc(category.getCategoryDesc());
			ctgry.setCategoryId(category.getCategoryId());
			ctgry.setCategoryName(category.getCategoryName());
			ctgry.setCreationDate(category.getCreationDate());
			categoryList.add(ctgry);
		}
		//session.close();
		return categoryList;
	}

	@Override
	@Transactional
	public PolicyCategory getAllSubCategories(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		PolicyCategory category = (PolicyCategory) session.get(PolicyCategory.class, categoryId);
		
		/*Set<PolicySubCategory> subCategories = category.getPolicySubCategories();

		ArrayList<PolicySubCategory> categoryList = new ArrayList<>();

		for(PolicySubCategory subCategory : subCategories){
			PolicySubCategory subCat = new PolicySubCategory();
			subCat.setCreationDate(subCategory.getCreationDate());
			subCat.setSubCategoryBenefits(subCategory.getSubCategoryBenefits());
			subCat.setSubCategoryDesc(subCategory.getSubCategoryDesc());
			subCat.setSubCategoryId(subCategory.getSubCategoryId());
			subCat.setSubCategoryName(subCategory.getSubCategoryName());
			categoryList.add(subCat);
		}
		return categoryList;*/
		////session.close();
		return category;
	}

	@Override
	@Transactional
	public ArrayList<PolicyPlan> getAllPolicyPlanListBrandBased(int brandId) {
		Session session = sessionFactory.getCurrentSession();
		PolicyBrand brand = (PolicyBrand) session.get(PolicyBrand.class, brandId);
		
		Set<PolicyPlan> plans = brand.getPolicyPlans();

		ArrayList<PolicyPlan> planList = new ArrayList<>();

		for(PolicyPlan plan : plans){
			planList.add(plan);
		}
		//session.close();
		return planList;
	}

	@Override
	@Transactional
	public ArrayList<PolicyPlan> getAllPolicyPlanListSubCategoryBased(int subCategoryId) {
		Session session = sessionFactory.getCurrentSession();
		if(session == null){
			session = sessionFactory.openSession();
		}
		PolicySubCategory subCategory = (PolicySubCategory) session.get(PolicySubCategory.class, subCategoryId);
		
		Set<PolicyPlan> plans = subCategory.getPolicyPlans();

		ArrayList<PolicyPlan> planList = new ArrayList<>();

		for(PolicyPlan plan : plans){
			planList.add(plan);
		}
		//session.close();
		return planList;
	}

	@Override
	@Transactional
	public ArrayList<PolicyBrand> getAllBrands() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PolicyBrand");

		ArrayList<PolicyBrand> brandList = new ArrayList<>();

		List<PolicyBrand> brands = (List<PolicyBrand>) query.list();

		for(PolicyBrand brand : brands){
			brandList.add(brand);
		}
		//session.close();
		return brandList;
	}

	@Override
	@Transactional
	public PolicyPlan getPolicyPlanDetail(int policyId) {
		Session session = sessionFactory.getCurrentSession();
		if(session == null){
			session = sessionFactory.openSession();
		}
		PolicyPlan plan = (PolicyPlan) session.get(PolicyPlan.class, policyId);
		////session.close();
		return plan;
	}

	@Override	
	public ArrayList<PolicySubCategory> getTotalSubCategories() {
		//Session session = sessionFactory.getCurrentSession();
		//if(session == null){
		Session session = sessionFactory.openSession();
		//}
		Query query = session.createQuery("from PolicySubCategory");

		ArrayList<PolicySubCategory> categoryList = (ArrayList<PolicySubCategory>) query.list();
		session.close();
		return categoryList;
	}

	@Override
	@Transactional
	public ArrayList<PolicyPlan> getAllPolicyPlans() {
		Session session = sessionFactory.getCurrentSession();
		if(session == null){
			session = sessionFactory.openSession();
		}
		Query query = session.createQuery("from PolicyPlan");

		ArrayList<PolicyPlan> planList = (ArrayList<PolicyPlan>) query.list();
		//session.close();
		return planList;
	}

	@Override
	@Transactional
	public ArrayList<Integer > getTop3PolicyPlans() {
		Session session = sessionFactory.getCurrentSession();
		if(session == null){
			session = sessionFactory.openSession();
		}
		Query query = session.createSQLQuery("SELECT plan_id,count(*) FROM policy_order_history GROUP BY plan_id LIMIT 3 ");
		
		List plans =  query.list();
		ArrayList<Integer > val = new ArrayList<Integer>();
		
		for (Iterator it = plans.iterator(); it.hasNext(); ) {
		    Object[] myResult = (Object[]) it.next();
		    Integer id = (Integer) myResult[0];
		   // String lastname = (String) myResult[1];
		    val.add(id);

		   
		}
		//session.close();
		return val;
		
	}

}
