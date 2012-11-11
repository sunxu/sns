package com.wacke.message.dao;

import java.util.List;

import com.wacke.entity.Message;

public interface MessageDao {
	public List<Message> getInMessage();
	
	public List<Message> getOutMessage();
	
	public List<Message> getNotice();
	
	public void sendMessage(String message,int toUid);

	public void deleteMessage(int id);
	
}
