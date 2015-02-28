package com.vurtnec.model.impl;

import com.vurtnec.model.bean.User;

 
public interface UserMapper {  
	
    public User findByName(String userName);  
    
    public void insertUser(User user);  
} 