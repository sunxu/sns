package com.wacke.message.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ActionContext;
import com.wacke.entity.Message;
import com.wacke.entity.User;
import com.wacke.message.dao.MessageDao;

public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao{
	

	
	public void deleteMessage(int id) {
		
	}

	@SuppressWarnings("unchecked")
	public List<Message> getInMessage() {
		int uid = Integer.parseInt(
				ActionContext.getContext().getSession().get(User.UID).toString()
			);
		String hql = "from Message message where message.touid = "+uid;
		return this.getHibernateTemplate().find(hql);
//		List tt = new ArrayList<Message>();
//		tt.add(new Message());
//		tt.add(new Message());
//		return tt;
	}

	@SuppressWarnings("unchecked")
	public List<Message> getOutMessage() {
		int uid = Integer.parseInt(
				ActionContext.getContext().getSession().get(User.UID).toString()
			);
		String hql = "from Message message where message.fromuid = "+uid;
		return this.getHibernateTemplate().find(hql);
	}
	
	public List<Message> getNotice() {
		// TODO Auto-generated method stub
		return new ArrayList<Message>();
	}

	public void sendMessage(String message, int toUid) {
		// TODO Auto-generated method stub
		
	}

}
