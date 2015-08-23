package com.bank.entity;


import java.util.Date;

public final class VIPAcc extends IndividualAcc {
	public final static double  INITAMOUNT = 1000000.0;
	public final static double OVERDRAFT = -100000.0;
	public final static int GRACE_DAYS = 30;
	
	private Date debtTime;
	private boolean isFrozen;
	
	public VIPAcc() {
		debtTime = null;
	}
	public VIPAcc(
			Double balance,
			char type,
			boolean isActive,
			Date registeredTime,
			CustLogInfo rootuser) {
		super(balance, type, isActive, registeredTime, rootuser);
		debtTime = null;
		isFrozen = false;
	}
	public Date getDebtTime() {
		return debtTime;
	}
	public void setDebtTime(Date debtTime) {
		this.debtTime = debtTime;
	}
	public boolean getIsFrozen() {
		return isFrozen;
	}
	public void setIsFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}
	public boolean isInDebt() {
		if(debtTime != null) {
			beFreesed();
			return true;
		}
		else 
			return false;
	}
	private boolean beFreesed() {
		Date date = new Date();
		if(debtTime != null && isFrozen == false) {
			long sl = debtTime.getTime();
			long el = date.getTime();
			el = el-sl;
			if(((int)(el/(100*60*60*24))) > VIPAcc.GRACE_DAYS) {
				this.isFrozen = true;		
				return true;
			}
		}  
		isFrozen = true;
		return false;
	}
	public void updateStatus() {
		if(balance < 0) {
			if(debtTime == null) {
				debtTime = new Date();
			} else
				;
		} else {
			debtTime = null;
			isFrozen = false;
		}
	}
}
