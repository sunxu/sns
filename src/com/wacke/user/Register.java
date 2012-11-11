package com.wacke.user;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wacke.common.entity.PageInfo;
import com.wacke.config.ConfigService;
import com.wacke.user.service.UserService;

public class Register extends ActionSupport {
	private static final long serialVersionUID = -5956461144474805203L;
	
	@Override
	public String execute() throws Exception {
		getPageInfo().title = "学习社区--用户注册";
		getPageInfo().type = PageInfo.REGISTER;
		setUseragreement(getConfigService().getConfigByName("useragreement"));
		return SUCCESS;
	}
	
	public String reg_check() throws Exception{
		if(getType().equals("email"))
		{
			if(getUserService().getUserDao().checkEmail(getValue()))
			{
				result.put("message", "您的Email帐号可用");
				result.put("status", true);
			}
			else
			{
				result.put("message", "该email地址已存在，请重新输入");
				result.put("status", false);
			}
		}
		
		if(getType().equals("veriCode"))
		{
			String veriCode = (String) ActionContext.getContext().getSession().get("veriCode");
			if(getValue().equals(veriCode))
			{
				result.put("message", "验证码填写正确");
				result.put("status", true);
			}
			else
			{
				result.put("message", "您输入的验证码错误");
				result.put("status", false);
			}
		}
		return SUCCESS;
	}
	
	public String registAct() throws Exception{
		String sessionVeriCode = (String) ActionContext.getContext().getSession().get("veriCode");
		if(getUserService().getUserDao().checkEmail(getValue())
				&& user_password.equals(user_repassword)
				&& veriCode.equals(sessionVeriCode))
		{
			getUserService().getUserDao().saveNewUser(user_email,user_name,user_password,user_sex);
			getUserService().UserLogin(getUserService().getUserDao().getUserByEmail(user_email));
			return SUCCESS;
		}
		else
			return INPUT;
	}
	
	private PageInfo pageInfo = new PageInfo();
	
	private ConfigService configService;
	private String useragreement;
	
	private UserService userService;
	private String value;
	private String type;
	private Map<String,Object> result = new HashMap<String,Object>();
	
	private String user_email;
	private String user_name;
	private String user_password;
	private String user_repassword;
	private String user_sex;
	private String veriCode;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public String getUseragreement() {
		return useragreement;
	}

	public void setUseragreement(String useragreement) {
		this.useragreement = useragreement;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String userEmail) {
		user_email = userEmail;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String userPassword) {
		user_password = userPassword;
	}

	public String getUser_repassword() {
		return user_repassword;
	}

	public void setUser_repassword(String userRepassword) {
		user_repassword = userRepassword;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String userSex) {
		user_sex = userSex;
	}

	public String getVeriCode() {
		return veriCode;
	}

	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

}
