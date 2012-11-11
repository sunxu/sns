package com.wacke.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="feed"
 *     
*/
public class Feed implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 332950740156897356L;

	public static final String DOING = "doing";
	public static final String BLOG = "blog";
	public static final String FRIEND = "friend";
	public static final String AVATAR = "avatar";
	public static final String ALBUM = "album";
	public static final String DOCUMENT = "document";
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer uid;

    /** nullable persistent field */
    private String username;

    /** nullable persistent field */
    private Integer dateline;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String message;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private Integer contentId;

    /** full constructor */
    public Feed(Integer uid, String username, Integer dateline, String title, String message, String type, Integer contentId) {
        this.uid = uid;
        this.username = username;
        this.dateline = dateline;
        this.title = title;
        this.message = message;
        this.type = type;
        this.contentId = contentId;
    }

    /** default constructor */
    public Feed() {
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
     *         
     */
    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /** 
     *            @hibernate.property
     *             column="username"
     *             length="45"
     *         
     */
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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
     *             column="title"
     *             length="65535"
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
     *             column="type"
     *             length="10"
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
     *             column="content_id"
     *             length="11"
     *         
     */
    public Integer getContentId() {
        return this.contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public Date getDate() {
		return new Date(getDateline ()*1000L);
	}
    
    public String getAvatarURLPath() {
        	return com.wacke.common.tool.Tool.getAvatarURLPath(getUid());
    }
    
}
