package com.wacke.blog;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.blog.service.BlogService;
import com.wacke.common.entity.Page;
import com.wacke.common.entity.PageInfo;
import com.wacke.entity.Blog;
import com.wacke.entity.Category;
import com.wacke.entity.User;
import com.wacke.friend.service.FriendService;

public class BlogShowAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6227249034042115594L;

	@Override
	public String execute() throws Exception {
		getPageInfo().type = PageInfo.BLOG;

		if(getView() == null && getId() != 0){
			setBlog(getBlogService().getBlog(getId()));
			if(getBlog() == null){
				getPageInfo().title = "出错了";
				return ERROR;
			}else{
				if(getBlog().getCid() == 0)
					setCategory(new Category());
				else
					setCategory(getBlogService().getCategory(getBlog().getCid()));
				getPageInfo().title = getBlog().getTitle();				
			}
			return "blogshow";
		}
		
		int uid = com.wacke.common.tool.Tool.getUid();
		
		if(getUid() != 0){
			Page pages = new Page(getBlogService().getBlogDao().getBlogCountByCid(getUid(), getCid()),getPage());
			if(pages.getTatolCount() > 0){
				setBloglist((ArrayList<Blog>) getBlogService().getBlogDao().getBlogListByCid(getUid(), getCid(), pages));
				setCategorylist((ArrayList<Category>) getBlogService().getCategoryList(getBloglist()));
				setPages(pages);
				if(getUid() == uid){
					setView(BlogShowAction.ME);
				}else{
					setUser(getBlogService().getUserDao().getUserByUid(getUid()));
					getPageInfo().title = getUser().getRealname() + "的日志";
					setView(BlogShowAction.CATEGORY);
				}
				return "bloglist";
			}
		}
		
		if(getView() == null || !getView().equals(BlogShowAction.ALL)){
			setView(BlogShowAction.ME);
			Page pages = new Page(getBlogService().getBlogDao().getBlogCountByUid(uid),getPage());
			if(pages.getNow() != getPage()){
				setPage(pages.getNow());
				return "redirect";
			}
			setBloglist((ArrayList<Blog>) getBlogService().getBlogDao().getBlogListByUid(uid, pages));
			setCategorylist((ArrayList<Category>) getBlogService().getCategoryList(getBloglist()));
			setPages(pages);
			getPageInfo().title = "我的日志";
		}else{
			Integer[] friendUid = getFriendService().getFriendUidByUid(uid);
			Page pages = new Page(getBlogService().getBlogDao().getBlogCountByFriend(friendUid),getPage());
			if(pages.getNow() != getPage()){
				setPage(pages.getNow());
				return "redirect";
			}
			setBloglist((ArrayList<Blog>) getBlogService().getBlogDao().getBlogListByFriend(friendUid, pages));
			setCategorylist((ArrayList<Category>) getBlogService().getCategoryList(getBloglist()));
			setPages(pages);
			getPageInfo().title = "好友日志";
		}
		
		return "bloglist";
	}
	
	public static final String ALL = "all";
	public static final String ME = "me";
	public static final String CATEGORY = "category";
	
	private PageInfo pageInfo = new PageInfo();
	private BlogService blogService;
	private FriendService friendService;
	
	private ArrayList<Blog> bloglist;
	private ArrayList<Category> categorylist;
	private Category category;
	private Blog blog;
	private Page pages;
	
	private User user;
	private String view;
	private int page;
	private int id;
	private int cid;
	private int uid;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public BlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public ArrayList<Blog> getBloglist() {
		return bloglist;
	}

	public void setBloglist(ArrayList<Blog> bloglist) {
		this.bloglist = bloglist;
	}

	public ArrayList<Category> getCategorylist() {
		return categorylist;
	}

	public void setCategorylist(ArrayList<Category> categorylist) {
		this.categorylist = categorylist;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Page getPages() {
		return pages;
	}

	public void setPages(Page pages) {
		this.pages = pages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
