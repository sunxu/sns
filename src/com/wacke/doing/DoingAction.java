package com.wacke.doing;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.doing.service.DoingService;
import com.wacke.entity.Doing;
import com.wacke.entity.Feed;

public class DoingAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5760895000209872072L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	public String save() throws Exception {
		int uid = com.wacke.common.tool.Tool.getUid();
		String username = com.wacke.common.tool.Tool.getUserName();
		Integer dateline = com.wacke.common.tool.Tool.getDateLine();
		Doing doing = new Doing(uid, username, dateline, getMessage());
		int doingId = getDoingService().save(doing);
		getDoingService().getFeedDao().addFeed(
				new Feed(uid, username, dateline, getMessage(), null, Feed.DOING, doingId));
		result.put("save", true);
		return SUCCESS;
	}
		
	private Map<String,Object> result = new HashMap<String,Object>();
	private DoingService doingService;
	
	private String message;

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public DoingService getDoingService() {
		return doingService;
	}

	public void setDoingService(DoingService doingService) {
		this.doingService = doingService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
