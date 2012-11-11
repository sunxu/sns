package com.wacke;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wacke.common.entity.PageInfo;
import com.wacke.entity.User;

public class Welcome extends ActionSupport {
	private static final long serialVersionUID = 6372499700205451992L;

	public String execute() throws Exception {
		getPageInfo().title = "学习社区";
		getPageInfo().type = PageInfo.HOME;
		if(ActionContext.getContext().getSession().get(User.UID)!=null)
			return "home";
		return SUCCESS;
	}
	
	private PageInfo pageInfo = new PageInfo();

	public PageInfo getPageInfo() {
		return pageInfo;
	}
	
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}
