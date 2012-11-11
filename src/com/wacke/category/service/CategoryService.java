package com.wacke.category.service;

import java.util.List;

import com.wacke.category.dao.CategoryDao;
import com.wacke.entity.Category;

public class CategoryService {
	
	public boolean saveCategory(String type, String title){
		Category category = new Category();
		category.setTitle(title);
		category.setType(type);
		category.setUid(com.wacke.common.tool.Tool.getUid());
		if(getCategoryDao().isExist(category))
			return false;
		getCategoryDao().saveOrUpdateCategory(category);
		return true;
	}
	
	public boolean deleteCategory(int id, String type){
		Category category = getCategoryDao().getCategoryById(id);
		int uid = com.wacke.common.tool.Tool.getUid();
		if(category.getUid() != uid || !category.getType().equals(type))
			return false;
		getCategoryDao().deleteCategory(id);
		return true;
	}
	
	public boolean editCategory(int id, String type, String title){
		Category category = getCategoryDao().getCategoryById(id);
		int uid = com.wacke.common.tool.Tool.getUid();
		if(category.getUid() != uid || !category.getType().equals(type))
			return false;
		category.setTitle(title);
		getCategoryDao().saveOrUpdateCategory(category);
		return true;
	}
	
	public List<Category> getCategoryList(String type){
		return getCategoryDao().getCategoryList(com.wacke.common.tool.Tool.getUid(), type);
	}
	
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	
}
