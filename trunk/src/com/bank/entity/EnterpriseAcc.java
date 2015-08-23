package com.bank.entity;


import java.util.Date;
import java.util.Set;

public final class EnterpriseAcc extends Account {
	public static double MIN_BAL = 0.00;
	
	public static int MAX_SUP = 2;
	
	private Set<CustLogInfo> supervisors;
	private Set<CustLogInfo> commonusers;
	//private EnCustLogInfo customers;
	
	public EnterpriseAcc() {}
	public EnterpriseAcc(
			Double balance,
			char type,
			boolean isActive,
			Date registeredTime,
			Set<CustLogInfo> supervisors,
			Set<CustLogInfo> commonusers
			//EnCustLogInfo customers,
			) {
		super(balance, type, isActive, registeredTime);
		//this.customers = customers;
		this.supervisors = supervisors;
		this.commonusers = commonusers;
	}
	public EnterpriseAcc(
			Double balance,
			char type,
			boolean isActive,
			Date registeredTime) {
		super(balance, type, isActive, registeredTime);
	}
	public Set<CustLogInfo> getSupervisors() {
		return supervisors;
	}
	public void setSupervisors(Set<CustLogInfo> supervisors) {
		this.supervisors = supervisors;
	}
	public Set<CustLogInfo> getCommonusers() {
		return commonusers;
	}
	public void setCommonusers(Set<CustLogInfo> commonusers) {
		this.commonusers = commonusers;
	}
	@Override
	public String toString() {
		return "EnterpriseAcc [commonusers=" + commonusers + ", supervisors="
				+ supervisors + ", balance=" + balance + ", id=" + id
				+ ", isActive=" + isActive + ", registeredTime="
				+ registeredTime + ", type=" + type + "]";
	}
}
