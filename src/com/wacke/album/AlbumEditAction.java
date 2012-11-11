package com.wacke.album;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.album.service.AlbumService;
import com.wacke.category.service.CategoryService;
import com.wacke.common.entity.PageInfo;
import com.wacke.entity.AlbumPic;
import com.wacke.entity.Category;

public class AlbumEditAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7210396722528690214L;

	@Override
	public String execute() throws Exception {
		getPageInfo().type = PageInfo.BLOG;
		setCategoryList((ArrayList<Category>) getCategoryService().getCategoryList("album"));
		getPageInfo().title = "上传图片";
		return SUCCESS;
	}
	
	public String save() throws Exception {
		List<AlbumPic> list = getAlbumService().saveAlbumPicList(getPiclist(), getCid());

		Category album = getCategoryService().getCategoryDao().getCategoryById(getCid());
		if(album.getImage() == null || album.getImage().equals("")){
			album.setImage(list.get(0).getThumbPic());
			getCategoryService().getCategoryDao().saveOrUpdateCategory(album);
		}
			
		getAlbumService().addFeed(album,list);
				
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		getAlbumService().deleteAlbumPic(getPid());
		return SUCCESS;
	}
	
	private PageInfo pageInfo = new PageInfo();
	private AlbumService albumService;
	private CategoryService categoryService;

	private int id;
	private int cid;
	private int pid;
	private ArrayList<Category> categoryList;
	private ArrayList<String> piclist;

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

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public ArrayList<String> getPiclist() {
		return piclist;
	}

	public void setPiclist(ArrayList<String> piclist) {
		this.piclist = piclist;
	}
	
}
