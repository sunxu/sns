package com.wacke.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="message"
 *     
*/
public class Message implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2795508134640657323L;
	
	public static final String UID = "uid";

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int fromuid;

    /** persistent field */
    private int touid;

    /** nullable persistent field */
    private String message;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private Integer dateline;

    /** nullable persistent field */
    private Byte isread;

    /** full constructor */
    public Message(int fromuid, int touid, String message, String type, Integer dateline, Byte isread) {
        this.fromuid = fromuid;
        this.touid = touid;
        this.message = message;
        this.type = type;
        this.dateline = dateline;
        this.isread = isread;
    }

    /** default constructor */
    public Message() {
    }

    /** minimal constructor */
    public Message(int fromuid, int touid) {
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
     *             column="isread"
     *             length="4"
     *         
     */
    public Byte getIsread() {
        return this.isread;
    }

    public void setIsread(Byte isread) {
        this.isread = isread;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
