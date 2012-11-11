package com.wacke.comment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wacke.comment.dao.CommentDao;
import com.wacke.entity.Comment;
import com.wacke.entity.User;

public class CommentService {
	
	public boolean deleteComment(int id){
		return getCommentDao().deleteComment(id, com.wacke.common.tool.Tool.getUid());
	}
	
	public void saveComment(Comment comment){
		comment.setId(0);
		comment.setFromuid(com.wacke.common.tool.Tool.getUid());
		comment.setDateline(com.wacke.common.tool.Tool.getDateLine());
		if(!comment.getMessage().startsWith("回复"))
			comment.setTouid(0);
		getCommentDao().saveComment(comment);
	}
	
	public List<Comment> getCommentListByBeginId(int articleid, String type, int beginid){
		List<Comment> commentlist = getCommentDao().getCommentListByBeginId(articleid, type, beginid);
		if(commentlist.size() == 0)
			return null;
		Integer[] uid = new Integer[commentlist.size()];
		int i = 0;
		for(Comment comment : commentlist){
			uid[i] = comment.getFromuid();
			i++;
		}
		Map<Integer,User> usermap = getCommentDao().getCommentUserList(uid);
		List<Comment> result = new ArrayList<Comment>();
		User user = null;
		for(Comment comment : commentlist){
			user = usermap.get(comment.getFromuid());
			comment.setRealname(user.getRealname());
			comment.setAvatarURLPath(user.getAvatarURLPath());
			result.add(comment);
		}
		return result;
	}
	  
	public List<Comment> getCommentListByLastId(int articleid, String type, int lastid){
		List<Comment> commentlist = getCommentDao().getCommentListByLastId(articleid, type, lastid);
		if(commentlist.size() == 0)
			return null;
		Integer[] uid = new Integer[commentlist.size()];
		int i = 0;
		for(Comment comment : commentlist){
			uid[i] = comment.getFromuid();
			i++;
		}
		Map<Integer,User> usermap = getCommentDao().getCommentUserList(uid);
		List<Comment> result = new ArrayList<Comment>();
		User user = null;
		for(Comment comment : commentlist){
			user = usermap.get(comment.getFromuid());
			comment.setRealname(user.getRealname());
			comment.setAvatarURLPath(user.getAvatarURLPath());
			result.add(comment);
		}
		return result;
	}
	
	private CommentDao commentDao;

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
}
