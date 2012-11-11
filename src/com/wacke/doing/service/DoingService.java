package com.wacke.doing.service;

import com.wacke.doing.dao.DoingDao;
import com.wacke.entity.Doing;
import com.wacke.feed.dao.FeedDao;

public class DoingService {
	
	public Integer save(Doing doing){
		return getDoingDao().saveDoing(doing);
	}

	public Doing getLastDoingByUid(int uid){
		return getDoingDao().getLatestDoingByUid(uid);
	}
	
	private DoingDao doingDao;
	public FeedDao feedDao;

	public DoingDao getDoingDao() {
		return doingDao;
	}

	public void setDoingDao(DoingDao doingDao) {
		this.doingDao = doingDao;
	}

	public FeedDao getFeedDao() {
		return feedDao;
	}

	public void setFeedDao(FeedDao feedDao) {
		this.feedDao = feedDao;
	}
	
}
