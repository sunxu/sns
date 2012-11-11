package com.wacke.document;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.common.entity.Page;
import com.wacke.common.entity.PageInfo;
import com.wacke.document.service.DocumentService;
import com.wacke.entity.Category;
import com.wacke.entity.Document;
import com.wacke.entity.User;
import com.wacke.friend.service.FriendService;

public class DocumentShowAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8610923731243615303L;
	
	@Override
	public String execute() throws Exception {
		getPageInfo().type = PageInfo.BLOG;

		if(getView() == null && getId() != 0){
			setDocument(getDocumentService().getDocument(getId()));
			if(getDocument() == null){
				getPageInfo().title = "出错了";
				return ERROR;
			}else{
				if(getDocument().getCid() == 0)
					setCategory(new Category());
				else
					setCategory(getDocumentService().getCategory(getDocument().getCid()));
				getPageInfo().title = getDocument().getTitle();				
			}
			return "documentshow";
		}
		
		int uid = com.wacke.common.tool.Tool.getUid();
		
		if(getUid() != 0){
			Page pages = new Page(getDocumentService().getDocumentDao().getDocumentCountByCid(getUid(), getCid()),getPage());
			if(pages.getTatolCount() > 0){
				setDocumentlist((ArrayList<Document>) getDocumentService().getDocumentDao().getDocumentListByCid(getUid(), getCid(), pages));
				setCategorylist((ArrayList<Category>) getDocumentService().getCategoryList(getDocumentlist()));
				setPages(pages);
				if(getUid() == uid){
					setView(DocumentShowAction.ME);
				}else{
					setUser(getDocumentService().getUserDao().getUserByUid(getUid()));
					getPageInfo().title = getUser().getRealname() + "的文件";
					setView(DocumentShowAction.CATEGORY);
				}
				return "documentlist";
			}
		}
		
		if(getView() == null || !getView().equals(DocumentShowAction.ALL)){
			setView(DocumentShowAction.ME);
			Page pages = new Page(getDocumentService().getDocumentDao().getDocumentCountByUid(uid),getPage());
			if(pages.getNow() != getPage()){
				setPage(pages.getNow());
				return "redirect";
			}
			setDocumentlist((ArrayList<Document>) getDocumentService().getDocumentDao().getDocumentListByUid(uid, pages));
			setCategorylist((ArrayList<Category>) getDocumentService().getCategoryList(getDocumentlist()));
			setPages(pages);
			getPageInfo().title = "我的文件";
		}else{
			Integer[] friendUid = getFriendService().getFriendUidByUid(uid);
			Page pages = new Page(getDocumentService().getDocumentDao().getDocumentCountByFriend(friendUid),getPage());
			if(pages.getNow() != getPage()){
				setPage(pages.getNow());
				return "redirect";
			}
			setDocumentlist((ArrayList<Document>) getDocumentService().getDocumentDao().getDocumentListByFriend(friendUid, pages));
			setCategorylist((ArrayList<Category>) getDocumentService().getCategoryList(getDocumentlist()));
			setPages(pages);
			getPageInfo().title = "好友文件";
		}
		
		return "documentlist";
	}
	
	public static final String ALL = "all";
	public static final String ME = "me";
	public static final String CATEGORY = "category";
	
	private PageInfo pageInfo = new PageInfo();
	private DocumentService documentService;
	private FriendService friendService;
	
	private ArrayList<Document> documentlist;
	private ArrayList<Category> categorylist;
	private Category category;
	private Document document;
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

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public ArrayList<Document> getDocumentlist() {
		return documentlist;
	}

	public void setDocumentlist(ArrayList<Document> documentlist) {
		this.documentlist = documentlist;
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

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
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
