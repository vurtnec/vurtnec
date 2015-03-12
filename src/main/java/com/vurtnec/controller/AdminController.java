package com.vurtnec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.vurtnec.component.dbconn.DBConnection;
import com.vurtnec.component.logger.LoggerNames;
import com.vurtnec.model.bean.User;
import com.vurtnec.model.impl.UserMapper;

@Controller
public class AdminController {
	
	private final static Logger logger = LoggerFactory.getLogger(LoggerNames.CONTROLLER);

	@Resource(name = "dbConnection")
	private DBConnection dbConnection;
	
	@RequestMapping(value = { "/login" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String login() {
		logger.info("login controller");
		return "/admin/login";
	}

	@RequestMapping(value = { "/signIn" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView signIn(String userName, String password) {
		System.out.println("signIn controller");
		ModelAndView mv = new ModelAndView();
		
		if(Strings.isNullOrEmpty(userName)) {
			mv.addObject("errorCode", "username can not be empty.");
			mv.setViewName("/admin/login");;
			return mv;
		}
		if(Strings.isNullOrEmpty(password)) {
			mv.addObject("errorCode", "password can not be empty.");
			mv.setViewName("/admin/login");;
			return mv;
		}
		
		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			Map<String,String> map = new HashMap<String, String>(); 
			
			map.put("userName", userName);
			map.put("userPassword", password);
			
			List<User> user = userMapper.login(map);
			
			if(user == null || user.size() != 1) {
				mv.addObject("errorCode", "username or password is wrong.");
				mv.setViewName("/admin/login");;
				return mv;
			}
		} finally {
			sqlSession.close();
		}
		mv.addObject("errorCode","login success.");
		mv.setViewName("/admin/home");
		return mv;
	}
	
	public DBConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
}