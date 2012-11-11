package com.wacke.comment.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.comment.dao.CommentDao;
import com.wacke.entity.Comment;
import com.wacke.entity.User;

public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

	public boolean deleteComment(int id, int fromuid) {
		Comment comment = (Comment) this.getHibernateTemplate().get(Comment.class, id);
		if(comment.getFromuid() == fromuid){
			this.getHibernateTemplate().delete(comment);
			return true;
		}else{
			return false;
		}
	}

	public void saveComment(Comment comment) {
		this.getHibernateTemplate().save(comment);
	}

	public int getCommentCount(int articleid, String type, int beginid) {
		String hql = "select count(*) from Comment comment where comment.type = '" + type + "' and comment.articleid = " + articleid;
		if(beginid > 0)
			hql += " and comment.id < " + beginid;
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Comment> getCommentListByBeginId(int articleid, String type, int beginid) {
		String temp = "from Comment comment where comment.type = '" 
						+ type + "' and comment.articleid = "
						+ articleid;
		if(beginid > 0)
			temp += " and comment.id < " + beginid;
		temp += " order by comment.id DESC";
		final String hql = temp;
		return (List<Comment>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult(0)   
				.setMaxResults(11)   
				.list();   
			}   
		});
	}

	@SuppressWarnings("unchecked")
	public List<Comment> getCommentListByLastId(int articleid, String type,	int lastid) {
		String hql = "from Comment comment where comment.type = '" 
			+ type + "' and comment.articleid = "
			+ articleid + " and comment.id > " + lastid + " order by comment.id DESC";
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public Map<Integer,User> getCommentUserList(Integer[] uid) {
		String hql = "select new User(uid, realname, hasavatar,sex) from User user where user.id in ("
			+ com.wacke.common.tool.Tool.arrayToString(uid)
			+ ") ";
		List<User> userlist = this.getHibernateTemplate().find(hql);
		Map<Integer,User> usermap = new HashMap<Integer,User>();
		for(User user : userlist){
			usermap.put(user.getUid(), user);
		}
		return usermap;
	}

}
