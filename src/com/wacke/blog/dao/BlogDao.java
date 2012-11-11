package com.wacke.blog.dao;

import java.util.List;

import com.wacke.common.entity.Page;
import com.wacke.entity.Blog;

public interface BlogDao {
	
	public Blog getBlogById(int id);
	
	public Integer saveOrUpdateBlog(Blog blog);
	
	public int getBlogCountByUid(int uid);
	
	public int getBlogCountByFriend(Integer[] uid);
	
	public int getBlogCountByCid(int uid, int cid);
	
	public List<Blog> getBlogListByUid(int uid, Page page);
	
	public List<Blog> getBlogListByFriend(Integer[] uid, Page page);
	
	public List<Blog> getBlogListByCid(int uid, int cid, Page page);
	
	public boolean deleteBlog(int uid, int id);
}
