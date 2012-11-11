package com.wacke.category.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.category.dao.CategoryDao;
import com.wacke.entity.Category;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {

	enum CategoryType {blog, document, album}	

	public boolean isExist(Category category) {
		String hql = "from Category category where "
			+ " category.uid = " + category.getUid() 
			+ " and category.type = '" + category.getType() +"'"
			+ " and category.title = '" + category.getTitle() +"'";
		return this.getHibernateTemplate().find(hql).size() > 0 ? true : false;
	};
	
	public Category getCategoryById(int id) {
		return (Category) this.getHibernateTemplate().get(Category.class, id);
	}
	
	public void deleteCategory(int id) {
		this.getHibernateTemplate().delete(new Category(id));
	}

	public int saveOrUpdateCategory(Category category) {
		this.getHibernateTemplate().saveOrUpdate(category);
		return category.getId();
	}

	@SuppressWarnings("unchecked")
	public List<Category> getCategoryList(int uid, String type) {
		String hql = "from Category category where category.uid = " + uid + " and category.type = '" + type +"'";
		return this.getHibernateTemplate().find(hql);
	}

}
