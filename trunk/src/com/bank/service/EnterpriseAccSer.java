package com.bank.service;

//import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bank.entity.Account;
import com.bank.entity.CustLogInfo;
import com.bank.entity.Customer;
import com.bank.entity.EnterpriseAcc;
import com.bank.operator.OptRecord;

public class EnterpriseAccSer extends AAccountSer implements IEnterpriseAccSer {
	OptRecord record;
	
	
	private CustLogInfo checkAccIdentWithoutIdNum(
			Long accountId,
			String password) {
		EnterpriseAcc entacc = entaccDao.findById(accountId);
		if(!entacc.getIsActive())
			return null;
		Set<CustLogInfo> users = entacc.getSupervisors();
		if(users == null) return null;
		Iterator<CustLogInfo> it = users.iterator();
		while(it.hasNext()) {
			CustLogInfo cli = it.next();
			if(cli.getPassword().equals(password)) {
				return cli;
			}
		}
		users = entacc.getCommonusers();
		if(users == null) return null;
		it = users.iterator();
		while(it.hasNext()) {
			CustLogInfo cli = it.next();
			if(cli.getPassword().equals(password)) {
				return cli;			
			}
		}
		return null;
	}
	private CustLogInfo checkAccIdent(
			Long idNumber, 
			Long accountId, 
			String password) {
		EnterpriseAcc entacc = entaccDao.findById(accountId);
		if(!entacc.getIsActive())
			return null;
		Set<CustLogInfo> users = entacc.getSupervisors();
		if(users == null) return null;
		Iterator<CustLogInfo> it = users.iterator();
		while(it.hasNext()) {
			CustLogInfo cli = it.next();
			if(cli.getPassword().equals(password)) {
				Customer customer = cli.getCustomer();
				if(customer != null 
						&& customer.getIdNumber().equals(idNumber))
					return cli;
					
			}
		}
		users = entacc.getCommonusers();
		if(users == null) return null;
		it = users.iterator();
		while(it.hasNext()) {
			CustLogInfo cli = it.next();
			if(cli.getPassword().equals(password)) {
				Customer customer = cli.getCustomer();
				if(customer != null 
						&& customer.getIdNumber().equals(idNumber))
					return cli;
					
			}
		}
		return null;
	}
	@Override
	public OptRecord checkbalance(Long idNumber, Long accountId, String password) {
		EnterpriseAcc entacc = entaccDao.findById(accountId);
		// loging
		record = new OptRecord();
		record.setAccount(entacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		
		if(entacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		CustLogInfo cli = checkAccIdent(idNumber, accountId, password);
		if(cli == null) {
			record.setOptdesc(new String("No access to the Acc."));
			optrecordDao.save(record);
			return record;
		}
		
		record.setOptdesc(new String("The Acc: ")+
				entacc.getId()+" balance is "+entacc.getBalance());
		optrecordDao.save(record);
		//System.out.println(record);
		
		return record;
	}

	@Override
	public OptRecord createAccount(Long idNumber, char type,
			double initialAmount, String password, String name) {
		CustLogInfo custloginfo = saveCustomer(idNumber,name);
		custloginfo.setPassword(password);
		custloginfo.setLastLoggingTime(new Date());
		custloginfoDao.save(custloginfo);
		
		Set<CustLogInfo> custloginfos = new HashSet<CustLogInfo>();
		custloginfos.add(custloginfo);
		EnterpriseAcc entacc = new EnterpriseAcc();
		
		entacc.setBalance(new Double(initialAmount));
		entacc.setIsActive(true);
		entacc.setRegisteredTime(new Date());
		entacc.setType(type);
		entacc.setSupervisors(custloginfos);
		
		entaccDao.save(entacc);
		// loging
		record = new OptRecord();
		record.setAccount(entacc);
		record.setEmployee(employee);
		record.setTime(new Date());
		record.setOptdesc(new String("Register an EnterpriseAcc: ")+entacc.getId());
		//record.setEmployee(employee);
		
		record.setOpt("OpenAcc");
		record.setInAmt(initialAmount);
		record.setOutAmt(0.0);
		record.setBal(entacc.getBalance().doubleValue());
		
		optrecordDao.save(record);
		//System.out.println(record);
		
		return record;
	}

	@Override
	public OptRecord deposit(Long accountId, String password, double amount) {
		
		EnterpriseAcc entacc = entaccDao.findById(accountId);
		

		// loging
		record = new OptRecord();
		record.setAccount(entacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("Deposit");
		
		if(entacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}	
		
		CustLogInfo cli = checkAccIdentWithoutIdNum(accountId, password);
		if(cli == null) {
			record.setOptdesc(new String("No access to the Acc."));
			optrecordDao.save(record);
			return record;
		}
		entacc.setBalance(entacc.getBalance() + Double.valueOf(amount));
		entaccDao.attachDirty(entacc);
		
		record.setOptdesc(new String("Deposit amount ")+
				amount+", and total is: "+entacc.getBalance().toString());
		
		record.setInAmt(amount);
		record.setOutAmt(0.0);
		record.setBal(entacc.getBalance().doubleValue());
		
		optrecordDao.save(record);
		
		return record;
	}

	@Override
	public OptRecord destory(Long idNumber, Long accountId, String password) {
		EnterpriseAcc entacc = entaccDao.findById(accountId);
		
		// loging
		record = new OptRecord();
		record.setAccount(entacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("DestoryAcc");
		
		if(entacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		CustLogInfo cli = checkAccIdent(idNumber, accountId, password);
		if(cli == null) {
			record.setOptdesc(new String("No access to the Acc."));
			optrecordDao.save(record);
			return record;
		}
		
		entacc.setIsActive(false);
		double t = entacc.getBalance();
		entacc.setBalance(new Double(0.0));
		entaccDao.attachDirty(entacc);
		record.setOptdesc(new String("Acc: ")+entacc.getId()+
				" is canceled."+" and Balance is token: "+String.valueOf(t));
		
		record.setInAmt(0.0);
		record.setOutAmt(t);
		record.setBal(entacc.getBalance().doubleValue());
		
		optrecordDao.save(record);
		//System.out.println(record);
		return record;
	}

	@Override
	public OptRecord reset(Long idNumber, Long accountId, String oldPassword,
			String newPassword) {
		EnterpriseAcc entacc = entaccDao.findById(accountId);
		
		// loging
		record = new OptRecord();
		record.setAccount(entacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("ResetPWD");
		
		if(entacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		CustLogInfo cli = checkAccIdent(idNumber, accountId, oldPassword);
		if(cli == null) {
			record.setOptdesc(new String("No access to the Acc."));
			optrecordDao.save(record);
			return record;
		}
		
		cli.setPassword(newPassword);
		custloginfoDao.attachDirty(cli);
		
		record.setOptdesc(new String("Password is set to be: ")+cli.getPassword());
		optrecordDao.save(record);
		//System.out.println(record);
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
			String name, Long inAccountId,
			String inCustomerName, double amount) {
		EnterpriseAcc entacc = entaccDao.findById(accountId);
		
		// loging
		record = new OptRecord();
		record.setAccount(entacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("Transfer");
		
		if(entacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		CustLogInfo cli = checkAccIdent(idNumber, accountId, password);
		if(cli == null) {
			record.setOptdesc(new String("No access to the Acc."));
			optrecordDao.save(record);
			return record;
		}
		Customer customer = cli.getCustomer();
		if(customer == null 
				|| !customer.getName().equals(name)) {
			record.setOptdesc(new String("No access to the Acc."));
			optrecordDao.save(record);
			return record;
		}
		
		Double remain = new Double(entacc.getBalance()-Double.valueOf(amount));
		if(remain < EnterpriseAcc.MIN_BAL) {
			record.setOptdesc(new String("Balance is not enough.")+
					entacc.getBalance()+" - "+String.valueOf(amount));
			optrecordDao.save(record);
			return record;
		}	
		Account acc = accountDao.findById(inAccountId);
		
		if(acc != null && acc.getIsActive()) {
			acc.setBalance(acc.getBalance()+Double.valueOf(amount));
			entacc.setBalance(remain);
			accountDao.attachDirty(acc);
			entaccDao.attachDirty(entacc);
			
			record.setOptdesc(new String("transfer amount from id-")+entacc.getId().toString() 
					+ " to "+ acc.getId().toString() + " " + String.valueOf(amount));
			
			record.setInAmt(0.0);
			record.setOutAmt(amount);
			record.setBal(entacc.getBalance().doubleValue());
			optrecordDao.save(record);
			
			record.setAccount(acc);
			record.setInAmt(amount);
			record.setOutAmt(0.0);
			record.setBal(acc.getBalance().doubleValue());
			optrecordDao.save(record);
			
			//System.out.println(record);		
			
			return record;
		} else {
			record.setOptdesc(new String("The Acc: ")+
					inAccountId + " does not exist.");
			optrecordDao.save(record);
			return record;
		}		
		//return false;
	}

	@Override
	public OptRecord withdraw(Long accountId, String password, double amount) {
		EnterpriseAcc entacc = entaccDao.findById(accountId);
		
		// loging
		record = new OptRecord();
		record.setAccount(entacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("WithDraw");
		
		if(entacc == null) {
			record.setOptdesc(new String("Acc: ")+accountId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		CustLogInfo cli = checkAccIdentWithoutIdNum(accountId,password);
		if(cli == null){
			record.setOptdesc(new String("No access to the Acc."));
			optrecordDao.save(record);
			return record;
		}
		Double remain = entacc.getBalance()-Double.valueOf(amount);
		if(remain < EnterpriseAcc.MIN_BAL) {
			record.setOptdesc(new String("Balance is not enough.")+
					entacc.getBalance()+" - "+String.valueOf(amount));
			optrecordDao.save(record);
			return record;
		}	
		entacc.setBalance(remain);
		entaccDao.attachDirty(entacc);
		
		record.setOptdesc(new String("Draw balance: ")+
				String.valueOf(amount)+" and remain: "+entacc.getBalance());
		record.setInAmt(0.0);
		record.setOutAmt(amount);
		record.setBal(entacc.getBalance().doubleValue());
		optrecordDao.save(record);
		//System.out.println(record);
		
		return record;
	}
	@Override
	public OptRecord addAccOperator(Long accId, String supPwd, 
			Long idNumber, String name, String password) {
		
		EnterpriseAcc entacc = entaccDao.findById(accId);
		CustLogInfo superior = null;
		// loging
		record = new OptRecord();
		record.setAccount(entacc);
		record.setTime(new Date());
		record.setEmployee(employee);
		record.setOpt("Add Operator");
		
		if(entacc == null) {
			record.setOptdesc(new String("Acc: ")+accId+" does not exist");
			optrecordDao.save(record);
			return record;
		}
		
		if(!entacc.getIsActive()) {
			record.setOptdesc(new String("The Enterprise Acc: ") + 
					entacc.getId() + " is Canceled.");
			optrecordDao.save(record);
			return record;
		}
		Set<CustLogInfo> users = entacc.getSupervisors();
		if(users != null) {
			Iterator<CustLogInfo> it = users.iterator();
			while(it.hasNext()) {
				CustLogInfo cli = it.next();;
				if(cli.getPassword().equals(supPwd)) {
					superior = cli;
					break;
				}
			}
		}else {
			record.setOptdesc(new String("You must have the Supervisor Level."));
			optrecordDao.save(record);
			return record;
		}
		if (superior == null) {
			record.setOptdesc(new String("You must have the Supervisor Level."));
			optrecordDao.save(record);
			return record;
		}
		CustLogInfo custloginfo = saveCustomer(idNumber,name);
		custloginfo.setPassword(password);
		custloginfo.setLastLoggingTime(new Date());
		custloginfoDao.attachDirty(custloginfo);
		
		Set<CustLogInfo> s = entacc.getCommonusers();
		s.add(custloginfo);
		entacc.setCommonusers(s);
		entaccDao.attachDirty(entacc);
		record.setOptdesc(new String("New operator: ")+
				custloginfo.getId()+" is added to Acc: "+entacc.getId());
		optrecordDao.save(record);
		return record;
	}

}
