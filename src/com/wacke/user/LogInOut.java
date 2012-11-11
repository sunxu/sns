package com.wacke.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wacke.entity.User;
import com.wacke.user.service.UserService;

public class LogInOut extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6531226397448031856L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		User logUser = getUserService().getUserDao().getUserByEmail(login_email);
		if(logUser == null)
		{
			getResult().put("status",false);
			getResult().put("emailerrer", "登录帐号错误，请重试");
			return SUCCESS;
		}
		if(!getUserService().getUserDao().MD5(login_pws).equals(logUser.getPassword()))
		{
			getResult().put("status",false);
			getResult().put("pwserrer", " 用户密码错误!");
			return SUCCESS;
		}
		getUserService().UserLogin(logUser);
		getResult().put("status",true);
		return SUCCESS;
	}
	
	public String logout() throws Exception{
		ActionContext.getContext().getSession().clear();
		ActionContext.getContext().getParameters().clear();
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);   
		response.setHeader("Expires", "Thu, 19 Nov 1981 08:52:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        return SUCCESS;
	}
	
	private String login_email;
	private String login_pws;
	private Map<String,Object> result = new HashMap<String,Object>();
	
	private UserService userService;
	
	public String getLogin_email() {
		return login_email;
	}
	public void setLogin_email(String loginEmail) {
		login_email = loginEmail;
	}
	public String getLogin_pws() {
		return login_pws;
	}
	public void setLogin_pws(String loginPws) {
		login_pws = loginPws;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
}
