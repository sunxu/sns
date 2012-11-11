package com.wacke.comment.dao;

import java.util.List;
import java.util.Map;

import com.wacke.entity.Comment;
import com.wacke.entity.User;

public interface CommentDao {
	
	public void saveComment(Comment comment);
	
	public boolean deleteComment(int id, int fromuid);
	
	public int getCommentCount(int articleid, String type, int beginid);
	
	public List<Comment> getCommentListByBeginId(int articleid, String type, int beginid);
  
	public List<Comment> getCommentListByLastId(int articleid, String type, int lastid);
	
	public Map<Integer,User> getCommentUserList(Integer[] uid);
	
}
