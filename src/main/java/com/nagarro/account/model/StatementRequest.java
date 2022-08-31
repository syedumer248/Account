package com.nagarro.account.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StatementRequest {
	
	@NotNull(message = "AccountId is mandatory")
	private Integer accountId;
	private String fromDate;
	private String toDate;
	private String fromAmount;
	private String toAmount;
	
}
