package com.wacke.message.service;

import com.wacke.message.dao.MessageDao;

public class MessageService {
	
	private MessageDao messageDao;

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	

}
