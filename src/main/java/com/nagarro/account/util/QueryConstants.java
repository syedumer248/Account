package com.nagarro.account.util;

public class QueryConstants {

	public static final String ACCOUNT_STATEMENT_QUERY = "SELECT * FROM Account ac, Statement st where st.account_id = ac.id and st.account_id=? ";
}
