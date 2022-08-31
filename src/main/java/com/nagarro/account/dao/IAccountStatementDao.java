package com.nagarro.account.dao;

import java.util.List;

import com.nagarro.account.model.StatementRequest;
import com.nagarro.account.model.StatementResponse;

public interface IAccountStatementDao {

	public List<StatementResponse> getStatementByAccountId(StatementRequest request) throws Exception; 
}
