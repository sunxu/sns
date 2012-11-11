package com.wacke.category;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.category.service.CategoryService;

public class CategoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -438184103703333889L;
	private static final Logger logger = Logger.getLogger(CategoryAction.class);
	
	@Override
	public String execute() throws Exception {
		if(getType() != null && getAction() != null){
			if(getAction().equals(CategoryAction.NEW)){
				result.put("status", 
						getCategoryService().saveCategory(getType(), getTitle()));
				if(result.get("status").equals(true))
					result.put("categorys", getCategoryService().getCategoryList(getType()));
			}
			if(getAction().equals(CategoryAction.DELETE)){
				getCategoryService().deleteCategory(getId(), getType());
			}
			if(getAction().equals(CategoryAction.EDIT)){
				getCategoryService().editCategory(getId(), getType(), getTitle());
			}
			logger.info("Category:"+"  "+getType()+"  "+getAction()+"  "+getTitle());
		}
		return SUCCESS;
	}
	
	public static final String BLOG = "blog";
	public static final String FRIEND = "friend";
	public static final String DOCUMENT = "document";
	
	public static final String EDIT = "edit";
	public static final String DELETE = "delete";
	public static final String NEW = "new";
	
	private CategoryService categoryService;
	private Map<String,Object> result = new HashMap<String,Object>();
	
	private int id;
	private String type;
	private String title;
	private String action;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
}
