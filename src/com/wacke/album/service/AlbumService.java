package com.wacke.album.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.wacke.album.dao.AlbumPicDao;
import com.wacke.category.dao.CategoryDao;
import com.wacke.entity.Album;
import com.wacke.entity.AlbumPic;
import com.wacke.entity.Category;
import com.wacke.entity.Feed;
import com.wacke.feed.dao.FeedDao;
import com.wacke.user.dao.UserDao;

public class AlbumService {
	
	public Album getAlbum(int id){
		if(getCategoryDao().getCategoryById(id) == null)
			return null;
		Album album = new Album(getCategoryDao().getCategoryById(id));
		album.setPicList(getAlbumPicDao().getAlbumPicListByCid(id));
		return album;
	}

	@SuppressWarnings("deprecation")
	public List<AlbumPic> saveAlbumPicList(ArrayList<String> piclist, int cid){
		String directory = ServletActionContext.getRequest().getRealPath("/data");
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		List<AlbumPic> list = new ArrayList<AlbumPic>();
		AlbumPic albumPic = new AlbumPic();
		File srcPath = new File(directory+"/temp/");
		File destPath = new File(directory+"/album/"+date.substring(0,4)+"/"+date.substring(4,6));
		if(!destPath.exists()){
			destPath.mkdirs();
		}
		for(String pic : piclist){
			albumPic.setId(null);
			albumPic.setCid(cid);
			albumPic.setPic("album/"+date.substring(0,4)+"/"+date.substring(4,6)+"/"+pic);
			albumPic.setUid(com.wacke.common.tool.Tool.getUid());
			if(com.wacke.common.tool.Tool.dealAlbumPic(pic, srcPath, destPath)){
				getAlbumPicDao().saveAlbumPic(albumPic);
				list.add(albumPic);
			}
		}
		return list;
	}
	
	public boolean deleteAlbumPic(int id){
		return getAlbumPicDao().deleteAlbumPic(com.wacke.common.tool.Tool.getUid(), id);
	}

	public void addFeed(Category album, List<AlbumPic> list){
		String message = "";
		for(AlbumPic albumPic : list){
			message += albumPic.getThumbPic()+"#";
		}
		message = message.substring(0, message.length()-1);
		getFeedDao().addFeed(
				new Feed(com.wacke.common.tool.Tool.getUid(),
						com.wacke.common.tool.Tool.getUserName(),
						com.wacke.common.tool.Tool.getDateLine(), 
						album.getTitle(),message, Feed.ALBUM,
						album.getId()));
	}
	
	private AlbumPicDao albumPicDao;
	private CategoryDao categoryDao;
	private UserDao userDao;
	private FeedDao feedDao;

	public AlbumPicDao getAlbumPicDao() {
		return albumPicDao;
	}

	public void setAlbumPicDao(AlbumPicDao albumPicDao) {
		this.albumPicDao = albumPicDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public FeedDao getFeedDao() {
		return feedDao;
	}

	public void setFeedDao(FeedDao feedDao) {
		this.feedDao = feedDao;
	}
	
}
