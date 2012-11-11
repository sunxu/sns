package com.wacke.category.dao;

import java.util.List;

import com.wacke.entity.Category;

public interface CategoryDao {
	
	public boolean isExist(Category category);
	
	public Category getCategoryById(int id);
	
	public int saveOrUpdateCategory(Category category);

	public void deleteCategory(int id);
	
	public List<Category> getCategoryList(int uid, String type);
	
}
