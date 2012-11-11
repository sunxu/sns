package com.wacke.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="document"
 *     
*/
public class Document implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2093969631459557075L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int uid;

    /** persistent field */
    private int cid;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String tag;

    /** nullable persistent field */
    private String intro;

    /** nullable persistent field */
    private String file;

    /** nullable persistent field */
    private Integer dateline;

    /** nullable persistent field */
    private Integer viewnum;

    /** full constructor */
    public Document(int uid, int cid, String title, String name, String tag, String intro, String file, Integer dateline, Integer viewnum) {
        this.uid = uid;
        this.cid = cid;
        this.title = title;
        this.name = name;
        this.tag = tag;
        this.intro = intro;
        this.file = file;
        this.dateline = dateline;
        this.viewnum = viewnum;
    }

    /** default constructor */
    public Document() {
    }

    /** minimal constructor */
    public Document(int uid, int cid) {
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
        return this.id;
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
     *             column="name"
     *             length="80"
     *         
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
     *             column="file"
     *             length="45"
     *         
     */
    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public Date getDate() {
		return new Date(getDateline()*1000L);
	}
}
