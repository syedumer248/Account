package com.nagarro.account.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nagarro.account.dao.IAccountStatementDao;
import com.nagarro.account.model.StatementRequest;
import com.nagarro.account.model.StatementResponse;
import com.nagarro.account.util.QueryConstants;

@Service
public class AccountStatementDaoImpl implements IAccountStatementDao {
	private static Logger logger = LoggerFactory.getLogger(AccountStatementDaoImpl.class);

	@Value("${datasource.url}")
	private String datasourceUrl;

	/**
	 * Method to return account statement 
	 */
	@Override
	public List<StatementResponse> getStatementByAccountId(StatementRequest request) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append(QueryConstants.ACCOUNT_STATEMENT_QUERY);
		List<StatementResponse> statementList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(datasourceUrl)) {
			boolean isQueryParameter = false;
			if (!StringUtils.isEmpty(request.getFromAmount())) {
				sb.append(" And amount >= " + request.getFromAmount());
				isQueryParameter = true;
			}

			if (!StringUtils.isEmpty(request.getToAmount())) {
				sb.append(" And amount <= " + request.getToAmount());
				isQueryParameter = true;
			}

			if (!StringUtils.isEmpty(request.getFromDate())) {
				sb.append(" And Format(Replace([dateField],'.','-') ,'yyyy-MM-dd')  >= Format( #" + request.getFromDate()
						+ "#,'yyyy-MM-dd')");
				isQueryParameter = true;
			}

			if (!StringUtils.isEmpty(request.getToDate())) {
				sb.append(
						" And Format(Replace([dateField],'.','-') ,'yyyy-MM-dd')  <= Format( #" + request.getToDate() + "#,'yyyy-MM-dd')");
				isQueryParameter = true;
			}

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!isQueryParameter && auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
				// Default 3 months statement
				Date date = Date.valueOf(LocalDate.now().minus(3, ChronoUnit.MONTHS));
				sb.append(" And Format(Replace([dateField],'.','-') ,'yyyy-MM-dd')  >= Format( #" + date + "#,'yyyy-MM-dd')");
			}
			logger.info("Executing Query :" + sb);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sb.toString());
			preparedStatement.setInt(1, request.getAccountId());

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				StatementResponse response = new StatementResponse();
				response.setAccountNumber(result.getString("account_number"));
				response.setAccountType(result.getString("account_type"));
				response.setAmount(result.getString("amount"));
				response.setDate(result.getString("dateField"));
				statementList.add(response);
			}

			return statementList;
		} catch (SQLException e) {
			logger.error("Error in SQL", e);
			throw new SQLException("Error in executing SQL");
		}
	}
}
