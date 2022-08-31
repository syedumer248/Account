package com.nagarro.account.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nagarro.account.model.StatementResponse;

public class AccountStatementMapper implements RowMapper<StatementResponse> {

	@Override
	public StatementResponse mapRow(ResultSet result, int rowNum) throws SQLException {
		StatementResponse response = new StatementResponse();
		response.setAccountNumber(result.getString("account_number"));
		response.setAccountType(result.getString("account_type"));
		response.setAmount(result.getString("amount"));
		response.setDate(result.getString("dateField"));
		return response;
	}

}
