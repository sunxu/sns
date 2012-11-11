package com.wacke.friend.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.common.entity.Page;
import com.wacke.entity.Friend;
import com.wacke.entity.User;
import com.wacke.friend.dao.FriendDao;

public class FriendDaoImpl extends HibernateDaoSupport implements FriendDao {
	
	public int getFriendCount(int uid, int cid) {
		String hql = "select count(*) from Friend friend where friend.status = 1 and friend.uid = " + uid ;
		if(cid != 0)
			hql += " and friend.cid = " + cid;
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Friend> getFriendList(int uid, int cid, final Page page) {
		String hql = "select new Friend(friend.uid, friend.fuid, friend.cid, user.realname, user.hasavatar, user.sex, friend.requesttime) "
			+ "from Friend friend, User user "
			+ "where user.uid = friend.fuid and friend.status = 1 and friend.uid = " + uid ;
		if(cid != 0)
			hql += " and friend.cid = " + cid;
		if(page == null)
			return this.getHibernateTemplate().find(hql);

		final String finalhql = hql + " order by friend.id ASC";
		return (List<Friend>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(finalhql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	@SuppressWarnings("unchecked")
	public Friend getFriend(int uid, int fuid) {
		String hql = "from Friend friend where friend.uid = " + uid + " and friend.fuid = " + fuid;
		List<Friend> list = this.getHibernateTemplate().find(hql);
		if(list.size() == 0)
			return null;
		return list.get(0);
	}
	
	public int saveOrUpdateFriend(Friend friend) {
		this.getHibernateTemplate().saveOrUpdate(friend);
		return friend.getId();
	}

	@SuppressWarnings("unchecked")
	public List<User> searchUser(String where) {
		String hql = "from User user where " + where ;
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Friend> getRequestFriendListByUid(int uid) {
		String hql = "select new Friend(friend.uid, friend.fuid, friend.cid, user.realname, user.hasavatar, user.sex, friend.requesttime) "
					+ "from Friend friend, User user "
					+ "where user.uid = friend.uid and friend.status = 0 and friend.fuid = "
					+ uid;
		return this.getHibernateTemplate().find(hql);
	}

	public void deleteFriend(int uid, int fuid) {
		this.getHibernateTemplate().delete(getFriend(uid, fuid));
	}

}
