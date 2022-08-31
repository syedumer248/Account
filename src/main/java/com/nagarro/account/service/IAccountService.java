package com.nagarro.account.service;

import java.util.List;

import com.nagarro.account.model.StatementRequest;
import com.nagarro.account.model.StatementResponse;

public interface IAccountService {

	public List<StatementResponse> getAccountStatement(StatementRequest request) throws Exception;
}
