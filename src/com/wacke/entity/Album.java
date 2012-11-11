package com.wacke.entity;

import java.util.List;

public class Album extends Category {

	/**
	 * 
	 */
	private static final long serialVersionUID = -453284124148754802L;

	public Album(Category category){
		super(category.getUid(), category.getType(),category.getTitle(), category.getImage());
		setId(category.getId());
	}
	private List<AlbumPic> picList;

	public List<AlbumPic> getPicList() {
		return picList;
	}

	public void setPicList(List<AlbumPic> picList) {
		this.picList = picList;
	}
	
}
