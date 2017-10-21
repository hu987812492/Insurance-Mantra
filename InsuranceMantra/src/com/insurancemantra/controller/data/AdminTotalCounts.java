package com.insurancemantra.controller.data;

public class AdminTotalCounts {
	
	private long totalPolicy;
	private long totalCategory;
	private long totalSubCategory;
	private long totalSeller;
	private long totalCustomer;
	private long todayPolicy;
	private long todaySeller;
	private long todayCustomer;
	private long todayClosedOrder;
	private long totalOrderCount;
	
	
	
	public AdminTotalCounts() {
		
	}
	
	
	public AdminTotalCounts(long totalPolicy, long totalCategory, long totalSubCategory, long totalSeller,
			long totalCustomer, long todayPolicy, long todaySeller, long todayCustomer, long todayClosedOrder,
			long totalOrderCount) {
		super();
		this.totalPolicy = totalPolicy;
		this.totalCategory = totalCategory;
		this.totalSubCategory = totalSubCategory;
		this.totalSeller = totalSeller;
		this.totalCustomer = totalCustomer;
		this.todayPolicy = todayPolicy;
		this.todaySeller = todaySeller;
		this.todayCustomer = todayCustomer;
		this.todayClosedOrder = todayClosedOrder;
		this.totalOrderCount = totalOrderCount;
	}


	public long getTotalPolicy() {
		return totalPolicy;
	}
	public void setTotalPolicy(long totalPolicy) {
		this.totalPolicy = totalPolicy;
	}
	public long getTotalCategory() {
		return totalCategory;
	}
	public void setTotalCategory(long totalCategory) {
		this.totalCategory = totalCategory;
	}
	public long getTotalSubCategory() {
		return totalSubCategory;
	}
	public void setTotalSubCategory(long totalSubCategory) {
		this.totalSubCategory = totalSubCategory;
	}
	public long getTotalSeller() {
		return totalSeller;
	}
	public void setTotalSeller(long totalSeller) {
		this.totalSeller = totalSeller;
	}
	public long getTotalCustomer() {
		return totalCustomer;
	}
	public void setTotalCustomer(long totalCustomer) {
		this.totalCustomer = totalCustomer;
	}
	public long getTodayPolicy() {
		return todayPolicy;
	}
	public void setTodayPolicy(long todayPolicy) {
		this.todayPolicy = todayPolicy;
	}
	public long getTodaySeller() {
		return todaySeller;
	}
	public void setTodaySeller(long todaySeller) {
		this.todaySeller = todaySeller;
	}
	public long getTodayCustomer() {
		return todayCustomer;
	}
	public void setTodayCustomer(int todayCustomer) {
		this.todayCustomer = todayCustomer;
	}
	public long getTodayClosedOrder() {
		return todayClosedOrder;
	}
	public void setTodayClosedOrder(long todayClosedOrder) {
		this.todayClosedOrder = todayClosedOrder;
	}
	public long getTotalOrderCount() {
		return totalOrderCount;
	}
	public void setTotalOrderCount(long totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}
	
	
}
