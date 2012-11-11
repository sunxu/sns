package com.wacke;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wacke.common.entity.PageInfo;
import com.wacke.doing.service.DoingService;
import com.wacke.entity.Doing;
import com.wacke.entity.Feed;
import com.wacke.entity.User;
import com.wacke.feed.service.FeedService;
import com.wacke.friend.service.FriendService;
import com.wacke.user.service.UserService;

public class Home extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9208689670454888647L;

	@Override
	public String execute() throws Exception {
		getPageInfo().title = "学习社区";
		getPageInfo().type = PageInfo.HOME;
		
		int uid = Integer.parseInt(
				ActionContext.getContext().getSession().get(User.UID).toString()
		);
		int dateline = com.wacke.common.tool.Tool.getDateLine() - 24*60*60;
		
		setUser(getUserService().getUserDao().getUserByUid(uid));
		setLatestDoing(getDoingService().getLastDoingByUid(uid));
		setFeedlist((ArrayList<Feed>) getFeedService().getFeedDao().getFeedByPage(
				getFriendService().getFriendUidByUid(uid), dateline));
		
		setFriendlist((ArrayList<User>) getUserService().getUserDao().getuserListByUid(getFriendService().getFriendUidByUid(uid)));
		return super.execute();
	}
	
	private PageInfo pageInfo = new PageInfo();
	private FeedService feedService;
	private DoingService doingService;
	private FriendService friendService;
	private UserService userService;
	
	private ArrayList<Feed> feedlist;
	private ArrayList<User> friendlist;
	private Doing latestDoing;
	private User user;

	public PageInfo getPageInf o() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public FeedService getFeedService() {
		return feedService;
	}

	public void setFeedService(FeedService feedService) {
		this.feedService = feedService;
	}

	public DoingService getDoingService() {
		return doingService;
	}

	public void setDoingService(DoingService doingService) {
		this.doingService = doingService;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ArrayList<Feed> getFeedlist() {
		return feedlist;
	}

	public void setFeedlist(ArrayList<Feed> feedlist) {
		this.feedlist = feedlist;
	}

	public Doing getLatestDoing() {
		return latestDoing;
	}

	public ArrayList<User> getFriendlist() {
		return friendlist;
	}

	public void setFriendlist(ArrayList<User> friendlist) {
		this.friendlist = friendlist;
	}

	public void setLatestDoing(Doing latestDoing) {
		this.latestDoing = latestDoing;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
