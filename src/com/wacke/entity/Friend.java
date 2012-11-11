package com.wacke.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="friend"
 *     
*/
public class Friend implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5343568399351826561L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int uid;

    /** persistent field */
    private int fuid;

    /** persistent field */
    private int cid;

    /** nullable persistent field */
    private Boolean status;

    /** nullable persistent field */
    private Integer visittime;

    /** nullable persistent field */
    private Integer requesttime;

    /** full constructor */
    public Friend(int uid, int fuid, int cid, Boolean status, Integer visittime, Integer requesttime) {
        this.uid = uid;
        this.fuid = fuid;
        this.cid = cid;
        this.status = status;
        this.visittime = visittime;
        this.requesttime = requesttime;
    }

    /** default constructor */
    public Friend() {
    }

    /** minimal constructor */
    public Friend(int uid, int fuid, int cid) {
        this.uid = uid;
        this.fuid = fuid;
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
     *             column="fuid"
     *             length="11"
     *             not-null="true"
     *         
     */
    public int getFuid() {
        return this.fuid;
    }

    public void setFuid(int fuid) {
        this.fuid = fuid;
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
     *             column="status"
     *             length="1"
     *         
     */
    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    /** 
     *            @hibernate.property
     *             column="visittime"
     *             length="10"
     *         
     */
    public Integer getVisittime() {
        return this.visittime;
    }

    public void setVisittime(Integer visittime) {
        this.visittime = visittime;
    }

    /** 
     *            @hibernate.property
     *             column="requesttime"
     *             length="10"
     *         
     */
    public Integer getRequesttime() {
        return this.requesttime;
    }

    public void setRequesttime(Integer requesttime) {
        this.requesttime = requesttime;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    /** requestFriend constructor */
    public Friend(int uid, int fuid, int cid, String frealname, Boolean fhasavatar, Byte fsex, int requesttime) {
        this.uid = uid;
        this.fuid = fuid;
        this.cid = cid;
        this.frealname = frealname;
        this.fhasavatar = fhasavatar;
        this.fsex = fsex;
        this.requesttime = requesttime;
    }
    
    private Boolean fhasavatar; 
    private String frealname; 
    private Byte fsex;

	public Boolean getFhasavatar() {
		return fhasavatar;
	}

	public void setFhasavatar(Boolean fhasavatar) {
		this.fhasavatar = fhasavatar;
	}

	public String getFrealname() {
		return frealname;
	}

	public void setFrealname(String frealname) {
		this.frealname = frealname;
	}

	public Byte getFsex() {
		return fsex;
	}

	public void setFsex(Byte fsex) {
		this.fsex = fsex;
	}
    
    public Date getRequesttimeToDate() {
		return new Date(getRequesttime()*1000L);
	}

    public Date getVisittimeToDate() {
		return new Date(getVisittime()*1000L);
	}
    
    public String getAvatarURLPath() {
        if(fhasavatar)
        	return com.wacke.common.tool.Tool.getAvatarURLPath(getFuid());
        else if(fsex == 0)
        	return "data/avatar/default/0/";
        else
        	return "data/avatar/default/1/";
    }
}
