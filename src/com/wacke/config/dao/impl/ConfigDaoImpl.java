package com.wacke.config.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.config.dao.ConfigDao;
import com.wacke.entity.Config;

public class ConfigDaoImpl extends HibernateDaoSupport implements ConfigDao {

	public String getConfigByName(String name) {
		String hql = "from Config config where config.var = '"+name+"'";
		return ((Config)this.getHibernateTemplate().find(hql).get(0)).getDatavalue();
	}

}
