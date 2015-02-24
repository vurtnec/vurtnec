package com.vurtnec;
 
public interface UserMapper {  
	
    public User findByName(String name);  
    
    public void insertUser(User user);  
} 