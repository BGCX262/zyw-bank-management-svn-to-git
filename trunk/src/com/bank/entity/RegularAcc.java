package com.bank.entity;


import java.util.Date;

public final class RegularAcc extends IndividualAcc {
	
	public RegularAcc() {}
	public RegularAcc(
			Double balance,
			char type,
			boolean isActive,
			Date registeredTime,
			CustLogInfo rootuser) {
		super(balance, type, isActive, registeredTime, rootuser);
	}
	@Override
	public String toString() {
		return "RegularAcc [id=" + id + ", balance=" + balance + ", rootuser="
				+ rootuser + ", registeredTime=" + registeredTime + "]";
	}
	
}
