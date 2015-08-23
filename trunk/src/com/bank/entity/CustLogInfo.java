package com.bank.entity;


import java.util.Date;

public class CustLogInfo {
	private Long id;
	//private Long customerId;
	private Customer customer;
	private String password;
	private Date lastLoggingTime;
	
	protected CustLogInfo() {}
	public CustLogInfo(
			//Long customerId,
			Customer customer,
			String password) {
		//this.customerId = customerId;
		this.customer = customer;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	} */
	public String getPassword() {
		return password;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLoggingTime() {
		return lastLoggingTime;
	}
	public void setLastLoggingTime(Date lastLoggingTime) {
		this.lastLoggingTime = lastLoggingTime;
	}
	@Override
	public String toString() {
		return "CustLogInfo [customer=" + customer + ", id=" + id
				+ ", lastLoggingTime=" + lastLoggingTime + ", password="
				+ password + "]";
	}
}
