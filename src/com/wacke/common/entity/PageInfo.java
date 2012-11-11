package com.wacke.common.entity;

public class PageInfo {

	public static String baseUrl = "http://localhost:8080/wacke";
	public String title;
	public String type;
	public static final String WELCOME = "welcome";
	public static final String HOME = "home";
	public static final String BLOG = "blog";
	public static final String DOCUMENT = "document";
	public static final String ALBUM = "album";
	public static final String SHARE = "share";
	public static final String GROUP = "group";
	public static final String MSGBOX = "msgbox";
	public static final String REGISTER = "register";
	public static final String FRIEND = "friend";
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
