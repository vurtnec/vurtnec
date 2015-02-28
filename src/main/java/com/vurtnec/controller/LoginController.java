package com.vurtnec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vurtnec.component.logger.LoggerNames;

@Controller
public class LoginController {
	
	private final static Logger logger = LoggerFactory.getLogger(LoggerNames.CONTROLLER);

	@RequestMapping(value = { "/login" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String login() {
		logger.info("login controller");
		return "/login/login";
	}

	@RequestMapping(value = { "/signIn" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String signIn() {
		System.out.println("signIn controller");
		return "/welcome";
	}
}