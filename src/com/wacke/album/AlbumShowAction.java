package com.wacke.album;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.album.service.AlbumService;
import com.wacke.common.entity.Page;
import com.wacke.common.entity.PageInfo;
import com.wacke.entity.Album;
import com.wacke.entity.AlbumPic;
import com.wacke.entity.User;
import com.wacke.friend.service.FriendService;

public class AlbumShowAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6227249034042115594L;

	@Override
	public String execute() throws Exception {
		getPageInfo().type = PageInfo.BLOG;

		if(getView() == null && getId() != 0){
			setAlbum(getAlbumService().getAlbum(getId()));
			if(getAlbum() == null){
				getPageInfo().title = "出错了";
				return ERROR;
			}else{
				getPageInfo().title = getAlbum().getTitle();
				int index = -1;
				if(getPid()>0)
					for(int i=0; i<getAlbum().getPicList().size(); i++){
						if(getAlbum().getPicList().get(i).getId() == getPid()){
							index = i;
							break;
						}
					}
				if(getPrev_next() != null){
					if(getPrev_next().equals("next"))
						index++;
					if(getPrev_next().equals("prev"))
						index--;
				}
				if(index >= 0 && index<getAlbum().getPicList().size())
					setAlbumPic(getAlbum().getPicList().get(index));
			}
			return "albumshow";
		}
		
		int uid = com.wacke.common.tool.Tool.getUid();
		
		if(getUid() != 0){
			Page pages = new Page(getAlbumService().getAlbumPicDao().getAlbumCountByUid(uid),getPage(),8);
			if(pages.getTatolCount() > 0){
				setAlbumlist((ArrayList<Album>) getAlbumService().getAlbumPicDao().getAlbumListByUid(getUid(), pages));
				setPages(pages);
				if(getUid() == uid){
					setView(AlbumShowAction.ME);
				}else{
					setUser(getAlbumService().getUserDao().getUserByUid(getUid()));
					getPageInfo().title = getUser().getRealname() + "的相册";
					setView(AlbumShowAction.CATEGORY);
				}
				return "albumlist";
			}
		}
		
		if(getView() == null || !getView().equals(AlbumShowAction.ALL)){
			setView(AlbumShowAction.ME);
			Page pages = new Page(getAlbumService().getAlbumPicDao().getAlbumCountByUid(uid),getPage(),8);
			if(pages.getNow() != getPage()){
				setPage(pages.getNow());
				return "redirect";
			}
			setAlbumlist((ArrayList<Album>) getAlbumService().getAlbumPicDao().getAlbumListByUid(uid, pages));
			setPages(pages);
			getPageInfo().title = "我的相册";
		}else{
			Integer[] friendUid = getFriendService().getFriendUidByUid(uid);
			Page pages = new Page(getAlbumService().getAlbumPicDao().getAlbumCountByFriend(friendUid),getPage(),8);
			if(pages.getNow() != getPage()){
				setPage(pages.getNow());
				return "redirect";
			}
			setAlbumlist((ArrayList<Album>) getAlbumService().getAlbumPicDao().getAlbumListByFriend(friendUid, pages));
			setPages(pages);
			getPageInfo().title = "好友相册";
		}
		
		return "albumlist";
	}
	
	public static final String ALL = "all";
	public static final String ME = "me";
	public static final String CATEGORY = "category";
	
	private PageInfo pageInfo = new PageInfo();
	private AlbumService albumService;
	private FriendService friendService;
	
	private ArrayList<Album> albumlist;
	private AlbumPic albumPic;
	private Album album;
	private Page pages;
	
	private User user;
	private String view;
	private String prev_next;
	private int page;
	private int id;
	private int pid;
	private int uid;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public AlbumService getAlbumService() {
		return albumService;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public ArrayList<Album> getAlbumlist() {
		return albumlist;
	}

	public void setAlbumlist(ArrayList<Album> albumlist) {
		this.albumlist = albumlist;
	}

	public AlbumPic getAlbumPic() {
		return albumPic;
	}

	public void setAlbumPic(AlbumPic albumPic) {
		this.albumPic = albumPic;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
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

	public String getPrev_next() {
		return prev_next;
	}

	public void setPrev_next(String prevNext) {
		prev_next = prevNext;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
