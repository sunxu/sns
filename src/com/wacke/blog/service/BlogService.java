package com.wacke.blog.service;

import java.util.ArrayList;
import java.util.List;

import com.wacke.blog.dao.BlogDao;
import com.wacke.category.dao.CategoryDao;
import com.wacke.entity.Blog;
import com.wacke.entity.Category;
import com.wacke.entity.Feed;
import com.wacke.feed.dao.FeedDao;
import com.wacke.user.dao.UserDao;

public class BlogService {

	public int saveOrUpdateBlog(Blog blog){
		if(blog.getId() == 0){
			blog.setUid(com.wacke.common.tool.Tool.getUid());
			blog.setDateline(com.wacke.common.tool.Tool.getDateLine());
			return getBlogDao().saveOrUpdateBlog(blog);
		}
		Blog temp = getBlog(blog.getId());
		if(temp.getUid() == com.wacke.common.tool.Tool.getUid()){
			blog.setViewnum(temp.getViewnum());
			blog.setDateline(temp.getDateline());
			return getBlogDao().saveOrUpdateBlog(blog);
		}
		return 0;
	}
	
	public Blog getBlog(int id){
		return getBlogDao().getBlogById(id);
	}

	public Category getCategory(int id){
		return getCategoryDao().getCategoryById(id);
	}
	
	public List<Category> getCategoryList(ArrayList<Blog> bloglist){
		List<Category> categorylist= new ArrayList<Category>();
		for(Blog blog : bloglist){
			if(blog.getCid() == 1)
				categorylist.add(new Category(1));
			else
				categorylist.add(getCategory(blog.getCid()));
		}
		return categorylist;
	}
	
	public boolean deleteBlog(int id){
		return getBlogDao().deleteBlog(com.wacke.common.tool.Tool.getUid(), id);
	}
	
	public void addFeed(Feed feed){
		getFeedDao().addFeed(feed);
	}
	
	private BlogDao blogDao;
	private CategoryDao categoryDao;
	private UserDao userDao;
	private FeedDao feedDao;
	
	public BlogDao getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public FeedDao getFeedDao() {
		return feedDao;
	}

	public void setFeedDao(FeedDao feedDao) {
		this.feedDao = feedDao;
	}
	
}
