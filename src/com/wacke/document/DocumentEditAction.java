package com.wacke.document;

import java.io.File;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wacke.category.service.CategoryService;
import com.wacke.common.entity.PageInfo;
import com.wacke.document.service.DocumentService;
import com.wacke.entity.Category;
import com.wacke.entity.Document;
import com.wacke.entity.Feed;

public class DocumentEditAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1095883073753314089L;
	
	@Override
	public String execute() throws Exception {
		getPageInfo().type = PageInfo.DOCUMENT;
		setCategoryList((ArrayList<Category>) getCategoryService().getCategoryList("document"));
		if(getId() == 0){
			getPageInfo().title = "新建文件";
			setDocument(new Document());
		}
		else{
			getPageInfo().title = "修改文件";
			setDocument(getDocumentService().getDocumentDao().getDocumentById(getId()));
		}
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	public String save() throws Exception {
		if(document.getId() == 0){
			String directory = ServletActionContext.getRequest().getRealPath("/data/temp");
			File source = new File(directory,document.getFile());
			com.wacke.common.tool.Tool.converFile(source);
			document.setFile(com.wacke.common.tool.Tool.getFileName(source));
			document.setName(com.wacke.common.tool.Tool.getFileName(new File(document.getName())));
			setId(getDocumentService().saveOrUpdateDocument(document));
			getDocumentService().addFeed(new Feed(com.wacke.common.tool.Tool.getUid(), 
											com.wacke.common.tool.Tool.getUserName(),
											com.wacke.common.tool.Tool.getDateLine(),
											document.getTitle(),
											document.getIntro(),
											Feed.DOCUMENT,
											getId()));
		}
		else{
			getDocumentService().saveOrUpdateDocument(document);
		}
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		getDocumentService().deleteDocument(getId());
		return SUCCESS;
	}
	
	private PageInfo pageInfo = new PageInfo();
	private DocumentService documentService;
	private CategoryService categoryService;
	private int id;
	private Document document;
	
	private ArrayList<Category> categoryList;

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

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
}
