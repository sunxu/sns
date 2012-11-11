package com.wacke.blog;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.blog.service.BlogService;
import com.wacke.category.service.CategoryService;
import com.wacke.common.entity.PageInfo;
import com.wacke.entity.Blog;
import com.wacke.entity.Category;
import com.wacke.entity.Feed;

public class BlogEditAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3551169512206958363L;

	@Override
	public String execute() throws Exception {
		getPageInfo().type = PageInfo.BLOG;
		setCategoryList((ArrayList<Category>) getCategoryService().getCategoryList("blog"));
		if(getId() == 0){
			getPageInfo().title = "新建日志";
			setBlog(new Blog());
		}
		else{
			getPageInfo().title = "编辑日志";
			setBlog(getBlogService().getBlogDao().getBlogById(getId()));
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		if(blog.getId() == 0){
			setId(getBlogService().saveOrUpdateBlog(blog));
			getBlogService().addFeed(new Feed(com.wacke.common.tool.Tool.getUid(), 
											com.wacke.common.tool.Tool.getUserName(),
											com.wacke.common.tool.Tool.getDateLine(),
											blog.getTitle(),
											blog.getIntro(),
											Feed.BLOG,
											getId()));
		}else{
			getBlogService().saveOrUpdateBlog(blog);
		}
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		getBlogService().deleteBlog(getId());
		return SUCCESS;
	}
	
	private PageInfo pageInfo = new PageInfo();
	private BlogService blogService;
	private CategoryService categoryService;

	private int id;
	private Blog blog;
	private ArrayList<Category> categoryList;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public BlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}

}
