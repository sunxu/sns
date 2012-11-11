package com.wacke.feed.dao;

import java.util.List;

import com.wacke.common.entity.Page;
import com.wacke.entity.Feed;

public interface FeedDao {
	public void addFeed(Feed feed);
	
	public void deleteFeedById(int uid ,int id);
	
	public List<Feed> getFeedByUid(int uid , Page page);
	
	public List<Feed> getFeedByPage(Integer[] frendId ,Page page ,Integer enddate);
	
	public String getUsernameByUid(int uid);
	
	public List<Feed> getFeedByPage(Integer[] frendId, Integer enddate);
}
