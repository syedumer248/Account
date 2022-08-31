package com.nagarro.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagarro.account.model.StatementRequest;

@Controller
public class Applicationcontroller {
	private static Logger logger = LoggerFactory.getLogger(Applicationcontroller.class);

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/search")
	public String search(Model model) {
		model.addAttribute("statementRequest", new StatementRequest());
		return "search";
	}
}
