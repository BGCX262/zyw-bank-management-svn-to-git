package com.bank.entity;


import java.util.Date;

public class Customer {
	private Long id;
	private Long idNumber;
	private String name;
	private Date registeredTime;
	
	protected Customer(){}
	public Customer(
			Long idNumber,
			String name,
			Date registeredTime)
	{
		this.idNumber = idNumber;
		this.name = name;
		this.registeredTime = registeredTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegisteredTime() {
		return registeredTime;
	}
	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", idNumber=" + idNumber + ", name="
				+ name + ", registeredTime=" + registeredTime + "]";
	}
}
