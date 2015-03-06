package com.vurtnec.model.impl;

import java.util.List;

import com.vurtnec.model.bean.Category;
 
public interface CategoryMapper {
	
	public List<Category> findAllCategory();
    
    public Category findCategoryById(int categoryId);
    
    public void insertCategory(Category category);
    
} 