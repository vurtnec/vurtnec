package com.vurtnec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vurtnec.component.logger.LoggerNames;
import com.vurtnec.component.logger.LoggerUtil;

@Controller
public class VurtnecController {
	
	private final static Logger logger = LoggerFactory.getLogger(LoggerNames.CONTROLLER);

	@RequestMapping(value = { "/welcome" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String welcome() {
		
		LoggerUtil.getInstantce().debug(logger, "welcome page enter.");
		
		return "/welcome";
	}
	
}
