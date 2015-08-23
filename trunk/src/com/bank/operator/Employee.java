package com.bank.operator;

import java.util.Date;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

public class Employee {
	protected Long id;
	protected String name;
	protected String password;
	protected Employee superior;
	protected Admin admin;
	//protected Set<OptRecord> records;
	
	protected Employee() {}
	public Employee(
			Employee superior, 
			Admin admin,
			String name,
			String password) {
		this.superior = superior;
		this.admin = admin;
		this.name = name;
		this.password = password;
		//records = new HashSet<OptRecord>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Employee getSuperior() {
		return superior;
	}
	public void setSuperior(Employee superior) {
		this.superior = superior;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
/*	public Set<OptRecord> getRecords() {
		return records;
	}
	public void setRecords(Set<OptRecord> records) {
		this.records = records;
	}	
	public void addRecord(OptRecord record) {
		if(record != null)
			this.records.add(record);
		if(this.admin != null)
			admin.addRecord(record);
		if(this.superior != null)
			superior.addRecord(record);
	}*/
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public List<OptRecord> getDayRecord() {
		return null;
	}
	public List<OptRecord> getMonthRecord() {
		return null;
	}
	public List<OptRecord> getYearRecord() {
		return null;
	}
	public List<OptRecord> getTimeRecord(Date begin, Date end) {
		return null;
	}
}
