package com.wacke.user.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.wacke.entity.User;
import com.wacke.user.dao.UserDao;

public class UserService {

	public void UserLogin(User user){
		ActionContext.getContext().getSession().put(User.UID, user.getUid());
		ActionContext.getContext().getSession().put(User.REALNAME, user.getRealname());
		ActionContext.getContext().getSession().put(User.ALLOWEDACTION, user.getAllowedaction());
		ActionContext.getContext().getSession().put(User.GROUPID, user.getGroupid());
		ActionContext.getContext().getSession().put(User.STATUS, user.getStatus());
		ActionContext.getContext().getSession().put(User.AVATAR, user.getAvatarURLPath());
		HttpServletRequest request = ServletActionContext.getRequest();
		ActionContext.getContext().getSession().put("jsessionid", request.getRequestedSessionId());
	}
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
