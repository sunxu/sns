package com.wacke.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="blog"
 *     
*/
public class Blog implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4903455395327280154L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int uid;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String message;

    /** nullable persistent field */
    private String intro;

    /** nullable persistent field */
    private Integer viewnum;

    /** nullable persistent field */
    private Integer dateline;

    /** nullable persistent field */
    private String tag;

    /** persistent field */
    private int cid;

    /** full constructor */
    public Blog(int uid, String title, String message, String intro, Integer viewnum, Integer dateline, String tag, int cid) {
        this.uid = uid;
        this.title = title;
        this.message = message;
        this.intro = intro;
        this.viewnum = viewnum;
        this.dateline = dateline;
        this.tag = tag;
        this.cid = cid;
    }

    /** default constructor */
    public Blog() {
    }

    /** minimal constructor */
    public Blog(int uid, int cid) {
        this.uid = uid;
        this.cid = cid;
    }

    /** 
     *            @hibernate.id
     *             generator-class="identity"
     *             type="java.lang.Integer"
     *             column="id"
     *             unsaved-value="0"
     *         
     */

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    /** 
     *            @hibernate.property
     *             column="uid"
     *             length="11"
     *             not-null="true"
     *         
     */
    public int getUid() {
        return this.uid;
    }

	public void setUid(int uid) {
        this.uid = uid;
    }

    /** 
     *            @hibernate.property
     *             column="title"
     *             length="80"
     *         
     */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** 
     *            @hibernate.property
     *             column="message"
     *             length="65535"
     *         
     */
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /** 
     *            @hibernate.property
     *             column="intro"
     *             length="65535"
     *         
     */
    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    /** 
     *            @hibernate.property
     *             column="viewnum"
     *             length="11"
     *         
     */
    public Integer getViewnum() {
        return this.viewnum;
    }

    public void setViewnum(Integer viewnum) {
        this.viewnum = viewnum;
    }

    /** 
     *            @hibernate.property
     *             column="dateline"
     *             length="10"
     *         
     */
    public Integer getDateline() {
        return this.dateline;
    }

    public void setDateline(Integer dateline) {
        this.dateline = dateline;
    }

    /** 
     *            @hibernate.property
     *             column="tag"
     *             length="65535"
     *         
     */
    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /** 
     *            @hibernate.property
     *             column="cid"
     *             length="11"
     *             not-null="true"
     *         
     */
    public int getCid() {
        return this.cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("idblog", getId())
            .toString();
    }

    public String getEditMessage() {
    	String str = getMessage();
    	if(str == null)
    		return "";
    	str = str.replaceAll("&", "&amp;");
    	str = str.replaceAll("<", "&lt;");
    	str = str.replaceAll(">", "&gt;");
    	str = str.replaceAll("\"", "&quot;");
    	return str;
    }
    
    public Date getDate() {
		return new Date(getDateline()*1000L);
	}
    
}
