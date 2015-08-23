package com.bank.service;

//import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bank.dao.AccountDAO;
import com.bank.dao.CustLogInfoDAO;
import com.bank.dao.CustomerDAO;
import com.bank.dao.EnterpriseAccDAO;
import com.bank.dao.OptRecordDAO;
import com.bank.dao.RegularAccDAO;
import com.bank.dao.VIPAccDAO;

import com.bank.entity.Account;
import com.bank.entity.Customer;
import com.bank.operator.Employee;

public abstract class AAccountSer {
	
	protected SessionFactory sessionfac;
	
	protected CustomerDAO custDao;
	protected CustLogInfoDAO custloginfoDao;
	protected EnterpriseAccDAO entaccDao;
	protected RegularAccDAO regaccDao;
	protected VIPAccDAO vipaccDao;
	protected OptRecordDAO optrecordDao;
	protected AccountDAO accountDao;
	
	protected Employee employee;
	
	public Account registerAccount(Account account) {
		entaccDao.getHibernateTemplate().save(account);
		return account;
	}
	
	public Customer registerCustomer(Customer customer) {
		custDao.save(customer);
		return customer;
	}
	
	public CustomerDAO getCustDao() {
		return custDao;
	}
	public void setCustDao(CustomerDAO custDao) {
		this.custDao = custDao;
	}
	public CustLogInfoDAO getCustloginfoDao() {
		return custloginfoDao;
	}
	public void setCustloginfoDao(CustLogInfoDAO custloginfoDao) {
		this.custloginfoDao = custloginfoDao;
	}
	public EnterpriseAccDAO getEntaccDao() {
		return entaccDao;
	}
	public void setEntaccDao(EnterpriseAccDAO entaccDao) {
		this.entaccDao = entaccDao;
	}
	public RegularAccDAO getRegaccDao() {
		return regaccDao;
	}
	public void setRegaccDao(RegularAccDAO regaccDao) {
		this.regaccDao = regaccDao;
	}
	public VIPAccDAO getVipaccDao() {
		return vipaccDao;
	}
	public void setVipaccDao(VIPAccDAO vipaccDao) {
		this.vipaccDao = vipaccDao;
	}
	public OptRecordDAO getOptrecordDao() {
		return optrecordDao;
	}
	public void setOptrecordDao(OptRecordDAO optrecordDao) {
		this.optrecordDao = optrecordDao;
	}

	public AccountDAO getAccountDao() {
		return accountDao;
	}
	public void setAccountDao(AccountDAO accountDao) {
		this.accountDao = accountDao;
	}
	public SessionFactory getSessionfac() {
		return sessionfac;
	}
	public void setSessionfac(SessionFactory sessionfac) {
		this.sessionfac = sessionfac;
	}

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}	
}
