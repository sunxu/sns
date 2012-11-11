package com.wacke.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wacke.entity.User;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1863365976184067374L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(ActionContext.getContext().getSession().get(User.UID) == null){
			return Action.LOGIN;
		}
		return invocation.invoke();
	}
}
