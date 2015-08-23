package com.bank.service;

//import com.bank.entity.Account;
import com.bank.entity.CustLogInfo;
import com.bank.operator.OptRecord;

public interface IAccountSer {
	
	public OptRecord createAccount(
			Long idNumber,			// ID number
			char type,				// account type
			double initialAmount,	// initial amount deposited
			String password,
			String name);		// customer initial password
	// find out or save customer first
	public CustLogInfo saveCustomer(
			Long idNumber,
			String name);
	
	public OptRecord deposit(
			Long accountId,			// account id number
			String password,		// customer password
			double amount);			// deposit amount
	
	public OptRecord withdraw(
			Long accountId,			// account id number
			String password,		// customer password
			double amount);			// withdraw amount
	
	public OptRecord checkbalance(
			Long idNumber,			// ID number
			Long accountId,			// account id number
			String password);		// customer password
	
	public OptRecord transfer(
			Long accountId,			// account id number transfered
			Long idNumber,			// customer id number transfered
			String password,		// customer password transfered
			String name,			// customer name transfered
			Long inAccountId,		// account id number transfered to
			String inCustomerName,
			double amount);		// customer name transfered to
	
	public OptRecord reset(
			Long idNumber,			// ID number
			Long accountId,			// account id number
			String oldPassword,		// customer old password
			String newPassword);	// customer new password
	
	public OptRecord destory(
			Long idNumber,			// ID number
			Long accountId,			// account id number
			String password);		// customer password
}
