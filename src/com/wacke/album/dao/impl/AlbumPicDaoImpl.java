package com.wacke.album.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wacke.album.dao.AlbumPicDao;
import com.wacke.common.entity.Page;
import com.wacke.entity.Album;
import com.wacke.entity.AlbumPic;

public class AlbumPicDaoImpl extends HibernateDaoSupport implements AlbumPicDao {

	public boolean deleteAlbumPic(int uid, int id) {
		AlbumPic pic = (AlbumPic) this.getHibernateTemplate().get(AlbumPic.class, id);
		if(pic.getUid() == uid){
			this.getHibernateTemplate().delete(pic);
			return true;
		}else{
			return false;
		}
	}

	public int getAlbumCountByFriend(Integer[] uid) {
		String hql = "select count(*) from Category category where "
			+ " category.type = 'album' and "
			+ " category.uid in (" + com.wacke.common.tool.Tool.arrayToString(uid) + ")";
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}

	public int getAlbumCountByUid(int uid) {
		String hql = "select count(*) from Category category where "
			+ " category.type = 'album' and "
			+ " category.uid = " + uid;
		return ((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbumListByFriend(Integer[] uid, final Page page) {
		final String hql = "from Category category where "
			+ " category.type = 'album' and "
			+ " category.uid in ("
			+ com.wacke.common.tool.Tool.arrayToString(uid)
			+ ") "
			+ " order by category.id DESC";
		
		return (List<Album>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbumListByUid(int uid, final Page page) {
		final String hql = "from Category category where "
			+ " category.type = 'album' and "
			+ " category.uid = " + uid;
		
		return (List<Album>)getHibernateTemplate().executeFind(new HibernateCallback() {   
			public Object doInHibernate(Session session) throws HibernateException,   
			SQLException {
				return session.createQuery(hql).setFirstResult((page.getNow()-1)*page.getPerPage())   
				.setMaxResults(page.getNow()*page.getPerPage())   
				.list();   
			}   
		});
	}

	@SuppressWarnings("unchecked")
	public List<AlbumPic> getAlbumPicListByCid(int cid) {
		String hql = "from AlbumPic pic where pic.cid = " + cid;
		return this.getHibernateTemplate().find(hql);
	}

	public Integer saveAlbumPic(AlbumPic albumPic) {
		this.getHibernateTemplate().save(albumPic);
		return albumPic.getId();
	}

}
