package com.nagarro.account.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StatementResponse {

	private String accountNumber;
	private String date;
	private String amount;
	private String accountType;
	
	public String getAccountNumber() {
		int hash = 7;
		for (int i = 0; i < accountNumber.length(); i++) {
		    hash = hash*31 + accountNumber.charAt(i);
		}
		return String.valueOf(hash);
	}
}
