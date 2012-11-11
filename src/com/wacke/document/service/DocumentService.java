package com.wacke.document.service;

import java.util.ArrayList;
import java.util.List;

import com.wacke.category.dao.CategoryDao;
import com.wacke.document.dao.DocumentDao;
import com.wacke.entity.Category;
import com.wacke.entity.Document;
import com.wacke.entity.Feed;
import com.wacke.feed.dao.FeedDao;
import com.wacke.user.dao.UserDao;

public class DocumentService {
	
	public int saveOrUpdateDocument(Document document){
		if(document.getId() == 0){
			document.setUid(com.wacke.common.tool.Tool.getUid());
			document.setDateline(com.wacke.common.tool.Tool.getDateLine());
			return getDocumentDao().saveOrUpdateDocument(document);
		}
		Document temp = getDocument(document.getId());
		if(temp.getUid() == com.wacke.common.tool.Tool.getUid()){
			document.setName(temp.getName());
			document.setFile(temp.getFile());
			document.setViewnum(temp.getViewnum());
			document.setDateline(temp.getDateline());
			return getDocumentDao().saveOrUpdateDocument(document);
		}
		return 0;
	}
	
	public Document getDocument(int id){
		return getDocumentDao().getDocumentById(id);
	}

	public Category getCategory(int id){
		return getCategoryDao().getCategoryById(id);
	}
	
	public List<Category> getCategoryList(ArrayList<Document> documentlist){
		List<Category> categorylist= new ArrayList<Category>();
		for(Document document : documentlist){
			if(document.getCid() == 1)
				categorylist.add(new Category(1));
			else
				categorylist.add(getCategory(document.getCid()));
		}
		return categorylist;
	}
	
	public boolean deleteDocument(int id){
		return getDocumentDao().deleteDocument(com.wacke.common.tool.Tool.getUid(), id);
	}
	
	public void addFeed(Feed feed){
		getFeedDao().addFeed(feed);
	}
	
	private DocumentDao documentDao;
	private CategoryDao categoryDao;
	private UserDao userDao;
	private FeedDao feedDao;
	
	public DocumentDao getDocumentDao() {
		return documentDao;
	}

	public void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
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
