package com.nagarro.account.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper<T> {

	private T data;
	private ErrorResponse error;
	
}
