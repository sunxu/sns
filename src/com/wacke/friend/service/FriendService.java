package com.wacke.friend.service;

import java.util.ArrayList;

import com.wacke.category.dao.CategoryDao;
import com.wacke.entity.Feed;
import com.wacke.entity.Friend;
import com.wacke.entity.User;
import com.wacke.feed.dao.FeedDao;
import com.wacke.friend.dao.FriendDao;

public class FriendService {

	public ArrayList<Friend> getFriendListByUid(int uid){
		return (ArrayList<Friend>) getFriendDao().getFriendList(uid, 0, null);
	}
	
	public Integer[] getFriendUidByUid(int uid){
		ArrayList<Friend> friendlist = getFriendListByUid(uid);
		Integer[] result = new Integer[friendlist.size()];
		int i = 0;
		for(Friend friend : friendlist){
			result[i] = friend.getFuid();
			i++;
		}
		return result;
	}
	
	public ArrayList<User> searchUser(String where) {
		return (ArrayList<User>) getFriendDao().searchUser(where);
	}
	
	public boolean addFriend(int uid, int friendUid){
		Friend friend = getFriendDao().getFriend(friendUid, uid);
		if(friend != null){
			friend.setStatus(true);
			System.out.println(friend.getId());
			
			getFriendDao().saveOrUpdateFriend(friend);
			System.out.println(friend.getId());
			
			friend = getFriendDao().getFriend(uid, friendUid);
			if(friend != null){
				friend.setStatus(true);
				getFriendDao().saveOrUpdateFriend(friend);
			}else{
				getFriendDao().saveOrUpdateFriend(
					new Friend(uid, friendUid, 1, true, 0, com.wacke.common.tool.Tool.getDateLine()));
			}
			getFeedDao().addFeed(new Feed(uid,
					com.wacke.common.tool.Tool.getUserName(),
					com.wacke.common.tool.Tool.getDateLine(),
					"<a href=space.do?uid="+uid+" target=_blank>"
					+com.wacke.common.tool.Tool.getUserName()+"</a>"+"和"
					+"<a href=space.do?uid="+friendUid+" target=_blank>"
					+getFeedDao().getUsernameByUid(friendUid)
					+"</a>"+"成为了朋友。",
					"",
					Feed.FRIEND,
					0));
			return true;
		}else{
			return false;
		}		
	}
	
	private FriendDao friendDao;
	private CategoryDao categoryDao;
	private FeedDao feedDao;

	public FriendDao getFriendDao() {
		return friendDao;
	}

	public void setFriendDao(FriendDao friendDao) {
		this.friendDao = friendDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public FeedDao getFeedDao() {
		return feedDao;
	}

	public void setFeedDao(FeedDao feedDao) {
		this.feedDao = feedDao;
	}
	
	
}
