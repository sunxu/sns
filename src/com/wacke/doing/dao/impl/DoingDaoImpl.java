package com.wacke.doing.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.doing.dao.DoingDao;
import com.wacke.entity.Doing;

public class DoingDaoImpl extends HibernateDaoSupport implements DoingDao {

	public Doing getDoingById(int id) {
		return (Doing)this.getHibernateTemplate().get(Doing.class, id);
	}

	@SuppressWarnings("unchecked")
	public Doing getLatestDoingByUid(int uid) {
		final String hql = "from Doing doing where doing.uid = " + uid + " order by doing.dateline DESC";
		List<Doing> doing = getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult(0)   
				.setMaxResults(1)   
				.list();   
			}   
		});
		if(doing.size()>0)
			return doing.get(0);
		else
			return new Doing();
	}
	
	public Integer saveDoing(Doing doing) {
		this.getHibernateTemplate().save(doing);
		return doing.getId();
	}
}
