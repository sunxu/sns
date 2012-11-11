package com.wacke.blog.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.blog.dao.BlogDao;
import com.wacke.common.entity.Page;
import com.wacke.entity.Blog;

public class BlogDaoImpl extends HibernateDaoSupport implements BlogDao {

	public Blog getBlogById(int id) {
		return (Blog) this.getHibernateTemplate().get(Blog.class, id);
	}
	
	public Integer saveOrUpdateBlog(Blog blog) {
		this.getHibernateTemplate().saveOrUpdate(blog);
		return blog.getId();
	}
	
	public int getBlogCountByUid(int uid){
		String hql = "select count(*) from Blog blog where blog.uid = " + uid;
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}
	
	public int getBlogCountByFriend(Integer[] uid){
		String hql = "select count(*) from Blog blog where blog.uid in (" + com.wacke.common.tool.Tool.arrayToString(uid) + ")";
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}


	public int getBlogCountByCid(int uid, int cid) {
		String hql = "select count(*) from Blog blog where blog.uid =" + uid;
		if(cid > 0)
			hql += " and blog.cid = " +cid;
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Blog> getBlogListByUid(int uid, final Page page) {
		final String hql = "from Blog blog where blog.uid =" + uid + "order by blog.id DESC";
		
		return (List<Blog>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	@SuppressWarnings("unchecked")
	public List<Blog> getBlogListByFriend(Integer[] uid, final Page page) {
		final String hql = "from Blog blog where "
			+ " blog.uid in ("
			+ com.wacke.common.tool.Tool.arrayToString(uid)
			+ ") "
			+ "order by blog.id DESC";
		
		return (List<Blog>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	@SuppressWarnings("unchecked")
	public List<Blog> getBlogListByCid(int uid, int cid, final Page page) {
		String temp;
		if(cid > 0)
			temp = "from Blog blog where blog.uid =" + uid
				+ " and blog.cid = " + cid
				+ "order by blog.id DESC";
		else
			temp = "from Blog blog where blog.uid =" + uid
				+ "order by blog.id DESC";
		final String hql = temp;
		return (List<Blog>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}
	
	public boolean deleteBlog(int uid, int id) {
		Blog blog = (Blog) this.getHibernateTemplate().get(Blog.class, id);
		if(blog.getUid() == uid){
			this.getHibernateTemplate().delete(blog);
			return true;
		}else{
			return false;
		}
	}

}
