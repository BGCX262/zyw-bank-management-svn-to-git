package com.bank.operator;

import java.util.Date;

import com.bank.entity.Account;

public final class OptRecord {
	private Long id;
	private Employee employee;
	private Account account;
	private Date time;
	private String optdesc;

	private String opt;
	private double inAmt;
	private double outAmt;
	private double bal;
	
	public OptRecord() {
		this.inAmt = 0.0;
		this.outAmt = 0.0;
		this.bal = 0.0;
	}
	public OptRecord(
			Employee employee,
			Account account,
			Date time) {
		this.employee = employee;
		this.account = account;
		this.time = time;
		this.inAmt = 0.0;
		this.outAmt = 0.0;
		this.bal = 0.0;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getOptdesc() {
		return optdesc;
	}
	public void setOptdesc(String optdesc) {
		this.optdesc = optdesc;
	}
	public Double getInAmt() {
		return inAmt;
	}
	public void setInAmt(Double inAmt) {
		this.inAmt = inAmt;
	}
	public Double getOutAmt() {
		return outAmt;
	}
	public void setOutAmt(Double outAmt) {
		this.outAmt = outAmt;
	}
	public Double getBal() {
		return bal;
	}
	public void setBal(Double bal) {
		this.bal = bal;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	@Override
	public String toString() {
		return "LOG: [id=" + id + ", employee=" + employee + ", account="
				+ account + ", time=" + time + ", opt=" + opt
				+ ", inAmt=" + inAmt + ", outAmt=" + outAmt + ", bal=" + bal
				+ "]";
	}
	public String output() {
		return optdesc;
	}
}
