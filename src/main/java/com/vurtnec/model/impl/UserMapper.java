package com.vurtnec.model.impl;

import java.util.List;
import java.util.Map;

import com.vurtnec.model.bean.User;

 
public interface UserMapper {  
	
    public User findByName(String userName);  
    
    public void insertUser(User user);  
    
    public List<User> login(Map<String,String> map);
} 