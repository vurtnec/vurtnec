package com.vurtnec.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vurtnec.component.dbconn.DBConnection;
import com.vurtnec.component.logger.LoggerNames;
import com.vurtnec.component.logger.LoggerUtil;
import com.vurtnec.model.bean.Category;
import com.vurtnec.model.impl.CategoryMapper;

@Controller
public class VurtnecController {
	
	private final static Logger logger = LoggerFactory.getLogger(LoggerNames.CONTROLLER);

	
	@RequestMapping(value = { "/home" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView home() {
		
		LoggerUtil.getInstantce().debug(logger, "welcome page enter.");
		
		SqlSession sqlSession = DBConnection.getSessionFactory().openSession();
		CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
		List<Category> categorys =categoryMapper.findAllCategory();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("test", "test message");
		modelAndView.addObject("categorys", categorys);
		modelAndView.setViewName("/front/home");
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/post" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String post() {
		
		LoggerUtil.getInstantce().debug(logger, "post page enter.");
		
		return "/front/post";
	}
	
	@RequestMapping(value = { "/about" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String about() {
		
		LoggerUtil.getInstantce().debug(logger, "about page enter.");
		
		return "/front/about";
	}
	
	@RequestMapping(value = { "/contact" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String contact() {
		
		LoggerUtil.getInstantce().debug(logger, "contact page enter.");
		
		return "/front/contact";
	}
	
}
