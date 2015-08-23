package com.bank.service;

import java.util.Date;
import java.util.List;

import com.bank.entity.Account;
import com.bank.entity.CustLogInfo;
import com.bank.entity.Customer;
//import com.bank.entity.EnterpriseAcc;
import com.bank.entity.VIPAcc;
import com.bank.operator.OptRecord;

public class VIPAccSer extends AAccountSer implements IIndividualAccSer {

	OptRecord record;
	
	@Override
	public OptRecord checkbalance(Long idNumber, Long accountId, String password) {
		VIPAcc vipacc = vipaccDao.findById(accountId);
		CustLogInfo custloginfo = vipacc.getRootuser();
		Customer customer = custloginfo.getCustomer();
		
		// loging
		record = new OptRecord();
		record.setAccount(vipacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("Check");
		
		if(vipacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		if(	!vipacc.getIsActive() || custloginfo == null || customer == null ||
				!(custloginfo.getPassword().equals(password)) ||
				!(customer.getIdNumber().equals(idNumber)))	
			//return -1.0;	// 抛出异常
		{
			record.setOptdesc(new String("Acc & pwd are not matched."));
			optrecordDao.save(record);
			return record;
		}

		record.setOptdesc(new String("The balance of acc: ")
			+vipacc.getId()+" is "+vipacc.getBalance().toString());
		
		record.setInAmt(0.0);
		record.setOutAmt(0.0);
		record.setBal(vipacc.getBalance().doubleValue());
		
		optrecordDao.save(record);
		return record;
	}

	@Override
	public OptRecord createAccount(Long idNumber, char type,
			double initialAmount, String password, String name) {

		CustLogInfo custloginfo = saveCustomer(idNumber,name);
		custloginfo.setPassword(password);
		custloginfo.setLastLoggingTime(new Date());
		custloginfoDao.attachDirty(custloginfo);
		
		// loging
		record = new OptRecord();
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("OpenAcc");
		
		VIPAcc vipacc = new VIPAcc();
		vipacc.setBalance(new Double(initialAmount));
		vipacc.setIsActive(true);
		vipacc.setRegisteredTime(new Date());
		vipacc.setType(type);
		vipacc.setRootuser(custloginfo);
		
		if( initialAmount < VIPAcc.INITAMOUNT ) {
			record.setOptdesc(new String("Initial Amount must be more than ")+
					String.valueOf(VIPAcc.INITAMOUNT));
			optrecordDao.save(record);
			return record;
		}		
				
		vipaccDao.save(vipacc);
		record.setAccount(vipacc);
		record.setOptdesc(new String("The new Acc is registered for ID: ")
			+ vipacc.getId());
		
		record.setInAmt(initialAmount);
		record.setBal(vipacc.getBalance().doubleValue());
		
		optrecordDao.save(record);
		return record;
	}

	@Override
	public OptRecord deposit(Long accountId, String password, double amount) {
		VIPAcc vipacc = vipaccDao.findById(accountId);
		//vipacc.updateStatus();
		CustLogInfo custloginfo = vipacc.getRootuser();
		
		// loging
		record = new OptRecord();
		record.setAccount(vipacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("Deposit");
		
		if(vipacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		if(!vipacc.getIsActive() || custloginfo == null || 
				!(custloginfo.getPassword().equals(password))){
			record.setOptdesc(new String("Acc & pwd are not matched."));
			optrecordDao.save(record);
			return record;
		}
		vipacc.updateStatus();
		
		vipacc.setBalance(vipacc.getBalance()+Double.valueOf(amount));
		vipacc.updateStatus();
		vipaccDao.attachDirty(vipacc);		
		record.setOptdesc(new String("Deposit amount ")+
				amount+", and total is: "+vipacc.getBalance().toString());
		
		record.setInAmt(amount);
		record.setBal(vipacc.getBalance().doubleValue());
		
		optrecordDao.save(record);
		
		return record;
	}

	@Override
	public OptRecord destory(Long idNumber, Long accountId, String password) {
		VIPAcc vipacc = vipaccDao.findById(accountId);
		CustLogInfo custloginfo = vipacc.getRootuser();
		Customer customer = custloginfo.getCustomer();
		
		// loging
		record = new OptRecord();
		record.setTime(new Date());
		record.setAccount(vipacc);
		record.setEmployee(employee);
		record.setOpt("DestoryACC");
		
		if(vipacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		if(!vipacc.getIsActive() || custloginfo == null || customer == null ||
				!(custloginfo.getPassword().equals(password)) ||
				!(customer.getIdNumber().equals(idNumber)))	{
			record.setOptdesc(new String("Acc, id number & pwd are not matched."));
			optrecordDao.save(record);
			return record;
		}

		vipacc.updateStatus();
		
		vipacc.setIsActive(false);
		double t = vipacc.getBalance();
		vipacc.setBalance(new Double(0.0));
		vipaccDao.attachDirty(vipacc);
		if(t > 0) {
			record.setOptdesc(new String("Acc: ")+vipacc.getId()+
				" is canceled."+" and you can take "+String.valueOf(t));
			record.setOutAmt(t);
		} else {
			record.setOptdesc(new String("Acc: ")+vipacc.getId()+
				" is canceled."+" and You must pay: "+String.valueOf(-t)+"in debt.");
			record.setInAmt(-t);
		}
		
		record.setBal(vipacc.getBalance().doubleValue());		
		optrecordDao.save(record);
		return record;
	}

	@Override
	public OptRecord reset(Long idNumber, Long accountId, String oldPassword,
			String newPassword) {
		VIPAcc vipacc = vipaccDao.findById(accountId);
		CustLogInfo custloginfo = vipacc.getRootuser();
		Customer customer = custloginfo.getCustomer();
		
		// loging
		record = new OptRecord();
		record.setTime(new Date());
		record.setAccount(vipacc);
		record.setEmployee(employee);
		record.setOpt("ResetPWD");
		
		if(vipacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		if(!vipacc.getIsActive() || custloginfo == null || customer == null ||
				!(custloginfo.getPassword().equals(oldPassword)) ||
				!(customer.getIdNumber().equals(idNumber)))	{
			record.setOptdesc(new String("Acc, id number & pwd are not matched."));
			optrecordDao.save(record);
			return record;
		}
		custloginfo.setPassword(newPassword);
		custloginfoDao.attachDirty(custloginfo);
		
		record.setOptdesc(new String("Password is set to be: ")+custloginfo.getPassword());
		optrecordDao.save(record);
		return record;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustLogInfo saveCustomer(Long idNumber, String name) {
		Customer customer;
		List<Customer> customers = 
			(List<Customer>)custDao.findByProperty("idNumber", idNumber);
		if(customers.size() < 1) {
			customer = new Customer(idNumber,name,new Date());
			this.registerCustomer(customer);
		} else
			customer = customers.iterator().next();
		
		CustLogInfo custloginfo = new CustLogInfo(customer, null);
		custloginfoDao.attachDirty(custloginfo);
		
		// loging
		record = new OptRecord();
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOptdesc(new String("Register Customer: ")+custloginfo.getId());
		optrecordDao.save(record);
		//System.out.println(record);
		
		return custloginfo;
	}

	@Override
	public OptRecord transfer(Long accountId, Long idNumber, String password,
			String name, Long inAccountId, String inCustomerName, double amount) {

		VIPAcc vipacc = vipaccDao.findById(accountId);
		CustLogInfo custloginfo = vipacc.getRootuser();
		Customer customer = custloginfo.getCustomer();
		
		// loging
		record = new OptRecord();
		record.setAccount(vipacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("Transfer");
		
		if(vipacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		// so ugly
		// 转账给regular Acc
		if(!vipacc.getIsActive() || custloginfo == null || customer == null ||
				!(custloginfo.getPassword().equals(password)) ||
				!(customer.getIdNumber().equals(idNumber)) ||
				!(customer.getName().equals(name)) )	{
			record.setOptdesc(new String("Acc, id number & pwd are not matched."));
			optrecordDao.save(record);
			return record;
		}
		vipacc.updateStatus();
		if(vipacc.getIsFrozen()) {
			record.setOptdesc(new String("Account is frozen."));
			optrecordDao.save(record);
			return record;
		}
		
		Double remain = new Double(vipacc.getBalance()-Double.valueOf(amount));
		if(remain < VIPAcc.OVERDRAFT) {
			record.setOptdesc(new String("Balance is not enough.")+
					vipacc.getBalance()+" - "+String.valueOf(amount));
			optrecordDao.save(record);
			return record;
		}	
		Account acc = accountDao.findById(inAccountId);
		if(acc != null && acc.getIsActive()) {
			acc.setBalance(acc.getBalance()+Double.valueOf(amount));
			vipacc.setBalance(remain);
			accountDao.attachDirty(acc);
			vipaccDao.attachDirty(vipacc);
			
			record.setOptdesc(new String("transfer amount from id-")+vipacc.getId().toString() 
					+ " to "+ acc.getId().toString() + " " + String.valueOf(amount));
			record.setOutAmt(amount);
			record.setBal(vipacc.getBalance().doubleValue());
			optrecordDao.save(record);	
			
			return record;
		} else {
			record.setOptdesc(new String("The Acc: ")+acc.getId().toString() 
					+ " does not exist.");
			optrecordDao.save(record);
			return record;
		}		
	}

	@Override
	public OptRecord withdraw(Long accountId, String password, double amount) {

		VIPAcc vipacc = vipaccDao.findById(accountId);
		CustLogInfo custloginfo = vipacc.getRootuser();
		
		// loging
		record = new OptRecord();
		record.setAccount(vipacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("WithDraw");
		
		if(!vipacc.getIsActive() || custloginfo == null ||
				!(custloginfo.getPassword().equals(password))) {
			record.setOptdesc(new String("Acc is not active or Password is wrong."));
			optrecordDao.save(record);
			return record;
		}
		Double remain = vipacc.getBalance()-Double.valueOf(amount);
		vipacc.updateStatus();
		if(vipacc.getIsFrozen()) {
			record.setOptdesc(new String("Account is frozen."));
			optrecordDao.save(record);
			return record;
		}
		if(remain < VIPAcc.OVERDRAFT) {
			record.setOptdesc(new String("Balance is not enough.")+
					vipacc.getBalance()+" - "+String.valueOf(amount));
			optrecordDao.save(record);
			return record;
		}
		vipacc.setBalance(remain);
		vipaccDao.attachDirty(vipacc);
		
		record.setOptdesc(new String("Draw balance: ")+
				String.valueOf(amount)+" and remain: "+vipacc.getBalance());
		record.setOutAmt(amount);
		record.setBal(vipacc.getBalance().doubleValue());
		optrecordDao.save(record);
		//System.out.println(record);
		
		return record;
	}
}
