package com.wacke.message;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.common.entity.PageInfo;
import com.wacke.entity.Message;
import com.wacke.message.service.MessageService;

public class MessageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4329276508343909030L;

	@Override
	public String execute() throws Exception {
		getPageInfo().title = "小纸条";
		getPageInfo().type = PageInfo.HOME;
		if(getType() == null)
			setType(MessageAction.INBOX);
		if(getType() == MessageAction.INBOX)
		{
			setMessageList((ArrayList<Message>) getMessageService().getMessageDao().getInMessage());
		}
		return super.execute();
	}
	
	public static final String INBOX = "inbox";
	public static final String OUTBOX = "outbox";
	public static final String NOTICE = "notice";
	public static final String CREATE = "create";
	
	private PageInfo pageInfo = new PageInfo();
	private MessageService messageService;
	private ArrayList<Message> messageList;
	private String type;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(ArrayList<Message> messageList) {
		this.messageList = messageList;
	}
	
}
