package com.wacke.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts2.json.annotations.JSON;


/** 
 *        @hibernate.class
 *         table="comment"
 *     
*/
public class Comment implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -574353646388694043L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int fromuid;

    /** persistent field */
    private int touid;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private Integer articleid;

    /** nullable persistent field */
    private String message;

    /** nullable persistent field */
    private Integer dateline;

    /** full constructor */
    public Comment(int fromuid, int touid, String type, Integer articleid, String message, Integer dateline) {
        this.fromuid = fromuid;
        this.touid = touid;
        this.type = type;
        this.articleid = articleid;
        this.message = message;
        this.dateline = dateline;
    }

    /** default constructor */
    public Comment() {
    }

    /** minimal constructor */
    public Comment(int fromuid, int touid) {
        this.fromuid = fromuid;
        this.touid = touid;
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
     *             column="fromuid"
     *             length="11"
     *             not-null="true"
     *         
     */
    public int getFromuid() {
        return this.fromuid;
    }

    public void setFromuid(int fromuid) {
        this.fromuid = fromuid;
    }

    /** 
     *            @hibernate.property
     *             column="touid"
     *             length="11"
     *             not-null="true"
     *         
     */
    public int getTouid() {
        return this.touid;
    }

    public void setTouid(int touid) {
        this.touid = touid;
    }

    /** 
     *            @hibernate.property
     *             column="type"
     *             length="45"
     *         
     */
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /** 
     *            @hibernate.property
     *             column="articleid"
     *             length="11"
     *         
     */
    public Integer getArticleid() {
        return this.articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Date getDate() {
		return new Date(getDateline()*1000L);
	}
    
    private String realname;
    private String avatarURLPath;
    private int beginId;
    
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getAvatarURLPath() {
		return avatarURLPath;
	}

	public void setAvatarURLPath(String avatarURLPath) {
		this.avatarURLPath = avatarURLPath;
	}

	public int getBeginId() {
		return beginId;
	}

	public void setBeginId(int beginId) {
		this.beginId = beginId;
	}
    
}
