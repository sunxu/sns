package com.wacke.document.dao.impl;

import java.util.List;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.common.entity.Page;
import com.wacke.document.dao.DocumentDao;
import com.wacke.entity.Document;

public class DocumentDaoImpl extends HibernateDaoSupport implements DocumentDao {

	public Document getDocumentById(int id) {
		return (Document) this.getHibernateTemplate().get(Document.class, id);
	}

	public int getDocumentCountByCid(int uid, int cid) {
		String hql = "select count(*) from Document document where document.uid =" + uid;
		if(cid > 0)
			hql += " and document.cid = " +cid;
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}

	public int getDocumentCountByFriend(Integer[] uid) {
		String hql = "select count(*) from Document document where document.uid in (" + com.wacke.common.tool.Tool.arrayToString(uid) + ")";
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}

	public int getDocumentCountByUid(int uid) {
		String hql = "select count(*) from Document document where document.uid = " + uid;
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Document> getDocumentListByCid(int uid, int cid, final Page page) {
		String temp;
		if(cid > 0)
			temp = "from Document document where document.uid =" + uid
				+ " and document.cid = " + cid
				+ "order by document.id DESC";
		else
			temp = "from Document document where document.uid =" + uid
				+ "order by document.id DESC";
		final String hql = temp;
		return (List<Document>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	@SuppressWarnings("unchecked")
	public List<Document> getDocumentListByFriend(Integer[] uid, final Page page) {
		final String hql = "from Document document where "
			+ " document.uid in ("
			+ com.wacke.common.tool.Tool.arrayToString(uid)
			+ ") "
			+ "order by document.id DESC";
		
		return (List<Document>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	@SuppressWarnings("unchecked")
	public List<Document> getDocumentListByUid(int uid, final Page page) {
		final String hql = "from Document document where document.uid =" + uid + "order by document.id DESC";
		
		return (List<Document>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	public Integer saveOrUpdateDocument(Document document) {
		this.getHibernateTemplate().saveOrUpdate(document);
		return document.getId();
	}

	public boolean deleteDocument(int uid, int id) {
		Document document = (Document) this.getHibernateTemplate().get(Document.class, id);
		if(document.getUid() == uid){
			this.getHibernateTemplate().delete(document);
			return true;
		}else{
			return false;
		}
	}
}
