package com.vurtnec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
	public ModelAndView login(String errorCode) {
		logger.info("login controller");
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorCode", errorCode);
		mv.setViewName("/admin/login");
		return mv;
	}

	@RequestMapping(value = { "/signIn" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView signIn(String userName, String password, HttpSession httpSession) {
		System.out.println("signIn controller");
		ModelAndView mv = new ModelAndView();
		
		if(Strings.isNullOrEmpty(userName)) {
			mv.addObject("errorCode", "username can not be empty.");
			mv.setViewName("redirect:/login");;
			return mv;
		}
		if(Strings.isNullOrEmpty(password)) {
			mv.addObject("errorCode", "password can not be empty.");
			mv.setViewName("redirect:/login");;
			return mv;
		}
		List<User> user = null;
		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			Map<String,String> map = new HashMap<String, String>(); 
			
			map.put("userName", userName);
			map.put("userPassword", password);
			
			user = userMapper.login(map);
			
		} finally {
			sqlSession.close();
		}
		
		if(user == null || user.size() != 1) {
			mv.addObject("errorCode", "username or password is wrong.");
			mv.setViewName("redirect:/login");
			return mv;
		}
		
		httpSession.setAttribute("currentUser", user.get(0));
		
		mv.setViewName("redirect:/admin/home");
		return mv;
	}
	
	
	@RequestMapping(value = { "/admin/home" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView adminHome(HttpSession httpSession) {
		logger.info("adminHome controller");
		User currentUser = (User) httpSession.getAttribute("currentUser");
		ModelAndView mv = new ModelAndView();
		
		if(currentUser == null) {
			mv.setViewName("redirect:/login");
			return mv;
		}
		
		mv.addObject("currentUser", currentUser);
		
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