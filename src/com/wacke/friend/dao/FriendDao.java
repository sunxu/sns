package com.wacke.friend.dao;

import java.util.List;

import com.wacke.common.entity.Page;
import com.wacke.entity.Friend;
import com.wacke.entity.User;

public interface FriendDao {
	
	public int getFriendCount(int uid, int cid);
	
	public List<Friend> getFriendList(int uid, int cid, Page page);
	
	public Friend getFriend(int uid, int fuid);
	
	public int saveOrUpdateFriend(Friend friend);

	public List<User> searchUser(String where);
	
	public List<Friend> getRequestFriendListByUid(int uid);
	
	public void deleteFriend(int uid, int fuid);
	
}
