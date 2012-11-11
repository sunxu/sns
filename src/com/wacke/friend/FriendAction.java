package com.wacke.friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.category.service.CategoryService;
import com.wacke.common.entity.Page;
import com.wacke.common.entity.PageInfo;
import com.wacke.entity.Category;
import com.wacke.entity.Friend;
import com.wacke.entity.User;
import com.wacke.friend.service.FriendService;

public class FriendAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5731258723666561176L;
	
	@Override
	public String execute() throws Exception {
		getPageInfo().type = PageInfo.FRIEND;
		int uid = com.wacke.common.tool.Tool.getUid();
		
		if(getType() == null)
			setType(FriendAction.LIST);
		
		if(getType().equals(FriendAction.LIST)){
			getPageInfo().title = "朋友圈";
			Page pages = new Page(getFriendService().getFriendDao().getFriendCount(uid, getCid()),getPage(),20);
			setPages(pages);
			setFriendlist((ArrayList<Friend>) getFriendService().getFriendDao().getFriendList(uid, getCid(), pages));
			setCategorylist((ArrayList<Category>) getFriendService().getCategoryDao().getCategoryList(uid, "friend"));
		}
		
		if(getType().equals(FriendAction.REQUEST)){
			getPageInfo().title = "好友申请";
			setFriendlist((ArrayList<Friend>)getFriendService().getFriendDao().getRequestFriendListByUid((com.wacke.common.tool.Tool.getUid())));
		}
		
		if(getType().equals(FriendAction.CATEGORY)){
			getPageInfo().title = "分组管理";
			setCategorylist((ArrayList<Category>) getCategoryService().getCategoryList("friend"));
		}
		
		if(getType().equals(FriendAction.SEARCH)){
			getPageInfo().title = "查找好友";
		}

		return SUCCESS;
	}
	
	public String search() throws Exception {
		getPageInfo().type = PageInfo.FRIEND;
		getPageInfo().title = "查找好友";
		String where = " user.uid != " + com.wacke.common.tool.Tool.getUid();
		int whereLenght = where.length();
		if(getMemName() != null && !getMemName().equals("")){
			where += " and user.realname = '" + getMemName() +"'";
		}
		if(whereLenght != where.length())
			setUserlist(getFriendService().searchUser(where));
		return SUCCESS;
	}
	
	public String doAjax() throws Exception {
		if(getType() == null)
			return SUCCESS;
		
		int uid = com.wacke.common.tool.Tool.getUid();
		
		if(getType().equals(FriendAction.REQUEST)){
			if(uid == getFriendid()){
				result.put("status", false);
				result.put("message", "不允许添加自己为好友");
				return SUCCESS;
			}
			Friend friend = getFriendService().getFriendDao().getFriend(uid, getFriendid());
			if(friend != null){
				if(friend.getStatus()){
					result.put("status", false);
					result.put("message", "该用户已是您的好友");
				}else{
					result.put("status", false);
					result.put("message", "好友请求已发送，等待对方处理");
				}
				return SUCCESS;
			}
			getFriendService().getFriendDao().saveOrUpdateFriend(
					new Friend(uid, getFriendid(), 1, false, 0, com.wacke.common.tool.Tool.getDateLine()));
			result.put("status", true);
			result.put("message", "处理成功");
			return SUCCESS;
		}
		
		if(getType().equals(FriendAction.ADD)){
			result.put("status", 
						getFriendService().addFriend(uid, getFriendid()));
			return SUCCESS;
		}
		
		if(getType().equals(FriendAction.DELETE)){
			getFriendService().getFriendDao().deleteFriend(uid, getFriendid());
			result.put("status", true);
			return SUCCESS;
		}
		
		if(getType().equals(FriendAction.CHANGE_CATEGORY)){
			Category Category = getCategoryService().getCategoryDao().getCategoryById(getCid());
			if(Category.getUid() != uid || !Category.getType().equals("friend")){
				result.put("status", false);
				return SUCCESS;
			}
			Friend friend = getFriendService().getFriendDao().getFriend(uid, getFriendid());
			friend.setCid(getCid());
			getFriendService().getFriendDao().saveOrUpdateFriend(friend);
			result.put("status", true);
			return SUCCESS;
		}
		
		return SUCCESS;
	}
	
	public static final String LIST = "list";
	public static final String CATEGORY = "category";
	public static final String REQUEST = "request";
	public static final String SEARCH = "search";
	public static final String RESULT = "result";
	
	public static final String ADD = "add";
	public static final String DELETE = "delete";
	public static final String CHANGE_CATEGORY = "change_category";
	
	private PageInfo pageInfo = new PageInfo();
	private FriendService friendService;
	private CategoryService categoryService;
	private Map<String,Object> result = new HashMap<String,Object>();
	
	private ArrayList<User> userlist;
	private ArrayList<Friend> friendlist;
	private ArrayList<Category> categorylist;
	private Page pages;

	private String memName;
	private String type;
	private int friendid;
	private int page;
	
	private int cid;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public ArrayList<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(ArrayList<User> userlist) {
		this.userlist = userlist;
	}

	public ArrayList<Friend> getFriendlist() {
		return friendlist;
	}

	public void setFriendlist(ArrayList<Friend> friendlist) {
		this.friendlist = friendlist;
	}

	public ArrayList<Category> getCategorylist() {
		return categorylist;
	}

	public void setCategorylist(ArrayList<Category> categorylist) {
		this.categorylist = categorylist;
	}

	public Page getPages() {
		return pages;
	}

	public void setPages(Page pages) {
		this.pages = pages;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public int getFriendid() {
		return friendid;
	}

	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

}
