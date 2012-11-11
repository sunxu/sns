package com.wacke.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.comment.service.CommentService;
import com.wacke.entity.Comment;

public class CommentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4216396950071325419L;

	@Override
	public String execute() throws Exception {
		ArrayList<Comment> commentlist = (ArrayList<Comment>) getCommentService().
											getCommentListByBeginId(
														getComment().getArticleid(), 
														getComment().getType(),
														getComment().getBeginId());
		if(commentlist.size() == 0){
			result.put("status", false);
			return SUCCESS;
		}
		
		result.put("status", true);
		result.put("commentlist", commentlist);
		
		if(commentlist.size() == 11){
			result.put("hasmore", true);
			result.put("beginid", ((Comment)commentlist.get(commentlist.size()-2)).getId());
		}else{
			result.put("hasmore", false);
			result.put("beginid", 0);
		}

		return SUCCESS;
	}
	
	public String save() throws Exception {
		if(getComment() == null){
			result.put("status", false);
			return SUCCESS;
		}
		
		getCommentService().saveComment(getComment());
		if(getComment().getId() != 0){
			result.put("status", true);
			result.put("commentlist", 
					(ArrayList<Comment>) getCommentService().
					getCommentListByLastId(getComment().getArticleid(), getComment().getType(), getLastid()));
		}else{
			result.put("status", false);
		}
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		if(getCommentService().deleteComment(getComment().getId())){
			result.put("status", true);
		}else{
			result.put("status", false);
		}
		return SUCCESS;
	}
    
	private CommentService commentService;

	private Map<String,Object> result = new HashMap<String,Object>();
	private ArrayList<Comment> commentlist;
	private Comment comment;
	
	private int lastid;
	
	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public ArrayList<Comment> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(ArrayList<Comment> commentlist) {
		this.commentlist = commentlist;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public int getLastid() {
		return lastid;
	}

	public void setLastid(int lastid) {
		this.lastid = lastid;
	}

}
