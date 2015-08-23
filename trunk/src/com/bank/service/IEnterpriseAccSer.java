package com.bank.service;

import com.bank.operator.OptRecord;

public interface IEnterpriseAccSer extends IAccountSer {
	public OptRecord addAccOperator(
			Long accId,				// Enterprise Account Id
			//Long supId,				// Superior Id
			String supPwd,			// Superior Password
			Long idNumber,			// ID number
			String name,
			String password);			// customer initial password
}
