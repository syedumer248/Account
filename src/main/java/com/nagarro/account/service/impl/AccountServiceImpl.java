package com.nagarro.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.account.dao.IAccountStatementDao;
import com.nagarro.account.model.StatementRequest;
import com.nagarro.account.model.StatementResponse;
import com.nagarro.account.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private IAccountStatementDao accountStatementDao;
	
	@Override
	public List<StatementResponse> getAccountStatement(StatementRequest request) throws Exception {
			return accountStatementDao.getStatementByAccountId(request);
		
	}

}
