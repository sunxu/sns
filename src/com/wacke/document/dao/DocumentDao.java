package com.wacke.document.dao;

import java.util.List;

import com.wacke.common.entity.Page;
import com.wacke.entity.Document;

public interface DocumentDao {
	
	public Document getDocumentById(int id);
	
	public Integer saveOrUpdateDocument(Document document);
	
	public int getDocumentCountByUid(int uid);
	
	public int getDocumentCountByFriend(Integer[] uid);
	
	public int getDocumentCountByCid(int uid, int cid);
	
	public List<Document> getDocumentListByUid(int uid, Page page);
	
	public List<Document> getDocumentListByFriend(Integer[] uid, Page page);
	
	public List<Document> getDocumentListByCid(int uid, int cid, Page page);
	
	public boolean deleteDocument(int uid, int id);
}
