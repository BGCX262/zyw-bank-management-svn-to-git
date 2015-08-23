package com.bank.entity;


import java.util.Date;

public class Account {
	protected Long id;
	protected Double balance;
	protected char type;
	protected boolean isActive;
	protected Date registeredTime;
	
	protected Account() {}
	protected Account(
			Double balance,
			char type,
			boolean isActive,
			Date registeredTime) {
		this.balance = balance;
		this.type = type;
		this.isActive = isActive;
		this.registeredTime = registeredTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Date getRegisteredTime() {
		return registeredTime;
	}
	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}
}
