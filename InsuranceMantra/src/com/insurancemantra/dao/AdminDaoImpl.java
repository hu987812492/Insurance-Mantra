package com.insurancemantra.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurancemantra.entity.PolicyBrand;
import com.insurancemantra.entity.PolicyCategory;
import com.insurancemantra.entity.PolicyOrderHistory;
import com.insurancemantra.entity.PolicySubCategory;
import com.insurancemantra.entity.PolicyUser;

@Repository
public class AdminDaoImpl implements IAdminDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public long[] getTotalPolicyCount() {
		long[] totalCounts = new long[10];
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("select count(*) from PolicyPlan");
		List listResult1 = query1.list();
		long policyCount = (long) listResult1.get(0);
		totalCounts[0] = policyCount;
		
		Query query2 = session.createQuery("select count(*) from PolicyCategory");
		List listResult2 = query2.list();
		long categoryCount = (long) listResult2.get(0);
		totalCounts[1] = categoryCount;
		
		Query query3 = session.createQuery("select count(*) from PolicySubCategory");
		List listResult3 = query3.list();
		long subCategoryCount = (long) listResult3.get(0);
		totalCounts[2] = subCategoryCount;
		
		Query query4 = session.createQuery("select count(*) from PolicyBrand");
		List listResult4 = query4.list();
		long sellerCount = (long) listResult4.get(0);
		totalCounts[3] = sellerCount;
		
		Query query5 = session.createQuery("select count(*) from PolicyUser");
		List listResult5 = query5.list();
		long customerCount = (long) listResult5.get(0);
		totalCounts[4] = customerCount;
		
		Query query6 = session.createQuery("select count(*) from PolicyPlan where creationDate=:date");
		Date beginDate1 = new Date();
		query6.setParameter("date",beginDate1);
		List listResult6 = query6.list();
		long todayPolicyCount = (long) listResult6.get(0);
		totalCounts[5] = todayPolicyCount;
		
		Query query7 = session.createQuery("select count(*) from PolicyBrand where creationDate=:date");
		Date beginDate2 = new Date();
		query7.setParameter("date",beginDate2);
		List listResult7 = query7.list();
		long todaySellerCount = (long) listResult7.get(0);
		totalCounts[6] = todaySellerCount;
		
		Query query8 = session.createQuery("select count(*) from PolicyUser where creationDate=:date");
		Date beginDate3 = new Date();
		query8.setParameter("date",beginDate3);
		List listResult8 = query8.list();
		long todayCustomerCount = (long) listResult8.get(0);
		totalCounts[7] = todayCustomerCount;
		
		Query query9 = session.createQuery("select count(*) from PolicyOrderHistory where orderCompletionDate=:date");
		Date beginDate4 = new Date();
		query9.setParameter("date",beginDate4);
		List listResult9 = query9.list();
		long closedOrderCount = (long) listResult9.get(0);
		totalCounts[8] = closedOrderCount;
		
		Query query10 = session.createQuery("select count(*) from PolicyOrderHistory");
		List listResult10 = query10.list();
		long totalOrderCount = (long) listResult10.get(0);
		totalCounts[9] = totalOrderCount;
		//session.close();
		
		return totalCounts;
	}

	@Override
	@Transactional
	public boolean createCategory(PolicyCategory category) {
		Session session = sessionFactory.getCurrentSession();
		session.save(category);
		//session.close();
		return true;
	}
	
	@Override
	@Transactional
	public ArrayList<String> categorynames(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select categoryName from PolicyCategory");
		ArrayList<String> categoryNames = (ArrayList<String>) query.list();
		//session.close();
		return categoryNames;
	}

	@Override
	@Transactional
	public boolean updateCategory(PolicyCategory category) {
		Session session = sessionFactory.getCurrentSession();
		session.update(category);
		//session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean deleteCategory(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		PolicyCategory category = (PolicyCategory) session.get(PolicyCategory.class, categoryId);
		session.delete(category);
		//session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean createSubCategory(PolicySubCategory subCategory, int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		PolicyCategory category = (PolicyCategory) session.get(PolicyCategory.class, categoryId);
		subCategory.setPolicyCategory(category);
		category.getPolicySubCategories().add(subCategory);
		session.save(subCategory);
		//session.close();
		return true;
	}

	@Override
	@Transactional
	public ArrayList<String> subCategorynames() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("categoryName from PolicySubCategory");
		ArrayList<String> subCategoryNames = (ArrayList<String>) query.list();
		//session.close();
		return subCategoryNames;
	}

	@Override
	@Transactional
	public boolean updateSubCategory(int subCategoryId,String creationDate,String subCategoryBenefits, 
			String subCategoryDesc, String subCategoryFeatures,	String subCategoryName) {		
		Session session = sessionFactory.getCurrentSession();
		PolicySubCategory subCategory = (PolicySubCategory) session.get(PolicySubCategory.class, subCategoryId);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			subCategory.setCreationDate(sdf.parse(creationDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		subCategory.setSubCategoryBenefits(subCategoryBenefits);
		subCategory.setSubCategoryDesc(subCategoryDesc);
		subCategory.setSubCategoryFeatures(subCategoryFeatures);
		subCategory.setSubCategoryName(subCategoryName);		
		session.saveOrUpdate(subCategory);
		//session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean deleteSubCategory(int subCategoryId) {
		Session session = sessionFactory.getCurrentSession();
		PolicySubCategory subCategory = (PolicySubCategory) session.get(PolicySubCategory.class, subCategoryId);
		
		session.delete(subCategory);
		//session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean deleteCustomer(int userId) {
		Session session = sessionFactory.getCurrentSession();

		PolicyUser brand = (PolicyUser)session.get(PolicyUser.class, userId);
		session.delete(userId);
		//session.close();
		return true;
	}

		@Override
		@Transactional
		public boolean updateSellerStatus(int brandId, String status) {
			Session session = sessionFactory.getCurrentSession();
			PolicyBrand brand = (PolicyBrand)session.get(PolicyBrand.class, brandId);
			brand.setBrandStatus(status);		
			session.saveOrUpdate(brand);
			//session.close();
			return true;
		}
	


}
