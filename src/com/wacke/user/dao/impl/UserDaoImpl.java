package com.wacke.user.dao.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.entity.User;
import com.wacke.user.dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public String MD5(String password) {   
		MessageDigest md;   
		try {   
			md = MessageDigest.getInstance("MD5");   
			md.update(password.getBytes());   
			return new BigInteger(1, md.digest()).toString(16);   
		} catch (Exception e) {   
			e.printStackTrace();
			return password;
		}   
	}
	
	public boolean checkEmail(String email) {
		String hql = "from User user where user.email = '"+email+"'";
		if(this.getHibernateTemplate().find(hql).isEmpty())
			return true;
		else
			return false;
	}

	public void saveNewUser(String userEmail, String userName,
			String userPassword, String userSex) {
		// TODO Auto-generated method stub
		User temp = new 
				User( userName, MD5(userPassword), userEmail, 0, (byte)Integer.parseInt(userSex), 0, 0, 2048, false, 0, (byte)9, null, false, (int)(System.currentTimeMillis()/1000), null, null, null, null);
		this.getHibernateTemplate().save(temp);	
	}

	public void updateUser(User user) {
		this.getHibernateTemplate().update(user);
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) {
		String hql = "from User user where user.email = '"+email+"'";
		List<User> list = this.getHibernateTemplate().find(hql);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public User getUserByUid(Integer uid) {
		String hql = "from User user where user.uid = "+uid;
		List<User> list = this.getHibernateTemplate().find(hql);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<User> getuserListByUid(Integer[] uid) {
		String hql = "from User user where user.uid in ("
			+ com.wacke.common.tool.Tool.arrayToString(uid)
			+ ") ";
		return this.getHibernateTemplate().find(hql);
	}

}
