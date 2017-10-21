package com.insurancemantra.controller.data;

import java.util.Date;

import com.insurancemantra.entity.PolicyPlan;

public class PolicyOrderHistoryResponse {
	
	private Long orderId;;
	private String requesterName;
	private String requesterEmail;
	private int requesterPhone;
	private String requesterDob;
	private String requesterIncome;
	private String orderStatus;
	private String orderCreationDate;
	private String orderCompletionDate;
	//private PolicyPlan policyPlan;
	public PolicyOrderHistoryResponse(Long orderId, String requesterName, String requesterEmail, int requesterPhone,
			String orderStatus, String orderCreationDate,
			String orderCompletionDate) {
		//this.policyPlan = policyPlan;
		this.orderId = orderId;
		this.requesterName = requesterName;
		this.requesterEmail = requesterEmail;
		this.requesterPhone = requesterPhone;
		this.orderStatus = orderStatus;
		this.orderCreationDate = orderCreationDate;
		this.orderCompletionDate = orderCompletionDate;
	}
	
	
	public PolicyOrderHistoryResponse(Long orderId, String requesterName, String requesterEmail, int requesterPhone,
			String requesterDob, String requesterIncome, String orderStatus, String orderCreationDate,
			String orderCompletionDate) {
		//this.policyPlan = policyPlan;
		this.orderId = orderId;
		this.requesterName = requesterName;
		this.requesterEmail = requesterEmail;
		this.requesterPhone = requesterPhone;
		this.requesterDob = requesterDob;
		this.requesterIncome = requesterIncome;
		this.orderStatus = orderStatus;
		this.orderCreationDate = orderCreationDate;
		this.orderCompletionDate = orderCompletionDate;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public String getRequesterEmail() {
		return requesterEmail;
	}
	public void setRequesterEmail(String requesterEmail) {
		this.requesterEmail = requesterEmail;
	}
	public int getRequesterPhone() {
		return requesterPhone;
	}
	public void setRequesterPhone(int requesterPhone) {
		this.requesterPhone = requesterPhone;
	}
/*	public PolicyPlan getPolicyPlan() {
		return policyPlan;
	}
	public void setPolicyPlan(PolicyPlan policyPlan) {
		this.policyPlan = policyPlan;
	}*/
	public String getRequesterDob() {
		return requesterDob;
	}
	public void setRequesterDob(String requesterDob) {
		this.requesterDob = requesterDob;
	}
	public String getRequesterIncome() {
		return requesterIncome;
	}
	public void setRequesterIncome(String requesterIncome) {
		this.requesterIncome = requesterIncome;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderCreationDate() {
		return orderCreationDate;
	}
	public void setOrderCreationDate(String orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}
	public String getOrderCompletionDate() {
		return orderCompletionDate;
	}
	public void setOrderCompletionDate(String orderCompletionDate) {
		this.orderCompletionDate = orderCompletionDate;
	}
	
	

}
