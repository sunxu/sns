package com.wacke.feed.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.common.entity.Page;
import com.wacke.entity.Feed;
import com.wacke.feed.dao.FeedDao;

public class FeedDaoImpl extends HibernateDaoSupport implements FeedDao {

	public void addFeed(Feed feed) {
		this.getHibernateTemplate().save(feed);
	}

	public void deleteFeedById(int uid ,int id) {
		String hql = "delete from Feed feed where feed.id = " + id + " and feed.uid = " + uid;
		this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Feed> getFeedByPage(Integer[] frendId, Integer enddate) {
		String hql = "from Feed feed where "
			+ "feed.dateline > " + enddate
			+ " and feed.uid in ("
			+ com.wacke.common.tool.Tool.arrayToString(frendId)
			+ ") "
			+ "order by feed.id ";
		return this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<Feed> getFeedByPage(Integer[] frendId, final Page page ,Integer enddate) {
		final String hql = "from Feed feed where "
			+ "feed.dateline > " + enddate
			+ " and feed.uid in ("
			+ com.wacke.common.tool.Tool.arrayToString(frendId)
			+ ") "
			+ "order by feed.id ";
		
		return (List<Feed>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	@SuppressWarnings("unchecked")
	public List<Feed> getFeedByUid(int uid, final Page page) {
		final String hql = "from Feed feed where feed.uid = " + uid + "order by feed.id ";
		
		return (List<Feed>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	public String getUsernameByUid(int uid) {
		String hql = "select user.realname from User user where user.uid = "+uid;
		return (String) this.getHibernateTemplate().find(hql).listIterator().next();
	}

}
