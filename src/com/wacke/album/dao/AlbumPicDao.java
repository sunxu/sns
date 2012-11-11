package com.wacke.album.dao;

import java.util.List;

import com.wacke.common.entity.Page;
import com.wacke.entity.Album;
import com.wacke.entity.AlbumPic;

public interface AlbumPicDao {
	
	public List<AlbumPic> getAlbumPicListByCid(int cid);
	
	public int getAlbumCountByUid(int uid);
	
	public int getAlbumCountByFriend(Integer[] uid);
	
	public Integer saveAlbumPic(AlbumPic albumPic);
	
	public boolean deleteAlbumPic(int uid, int id);
	
	public List<Album> getAlbumListByUid(int uid, Page page);
	
	public List<Album> getAlbumListByFriend(Integer[] uid, Page page);
	
}
