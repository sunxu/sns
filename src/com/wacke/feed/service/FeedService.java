package com.wacke.feed.service;

import com.wacke.feed.dao.FeedDao;

public class FeedService {

	private FeedDao feedDao;

	public FeedDao getFeedDao() {
		return feedDao;
	}

	public void setFeedDao(FeedDao feedDao) {
		this.feedDao = feedDao;
	}
}
