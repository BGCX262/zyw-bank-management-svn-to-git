package com.bank.entity;


import java.util.Date;

public class IndividualAcc extends Account {
	protected CustLogInfo rootuser;
	
	protected IndividualAcc() {}
	protected IndividualAcc(
			Double balance,
			char type,
			boolean isActive,
			Date registeredTime,
			CustLogInfo rootuser) {
		super(balance, type, isActive, registeredTime);
		this.rootuser = rootuser;
	}
	public CustLogInfo getRootuser() {
		return rootuser;
	}
	public void setRootuser(CustLogInfo rootuser) {
		this.rootuser = rootuser;
	}
	
}
