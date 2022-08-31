package com.nagarro.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import com.nagarro.account.model.StatementRequest;
import com.nagarro.account.model.StatementResponse;
import com.nagarro.account.service.IAccountService;
import com.nagarro.account.util.Constant;

@Controller
@Validated
public class AccountController {
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private IAccountService accountService;
	
	@PostMapping(Constant.ACCOUNT_STATEMENT)
	public String getAccountStatement( @Valid StatementRequest statementRequest, BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {
			logger.error("Error in Request Field"+ result);
			model.addAttribute("error", result.getFieldError().getDefaultMessage() );
			return Constant.VIEW_ACCOUNT_STATEMENT;
		}
		logger.debug("statementRequest",statementRequest);
		List<StatementResponse> reponse = accountService.getAccountStatement(statementRequest);
		logger.info("StatementResponse" + reponse);
		model.addAttribute("reponse", reponse);
		return Constant.VIEW_ACCOUNT_STATEMENT;
	}
	
}
