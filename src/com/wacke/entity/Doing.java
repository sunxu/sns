package com.wacke.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="doing"
 *     
*/
public class Doing implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2117638932887854527L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int uid;

    /** nullable persistent field */
    private String username;

    /** nullable persistent field */
    private Integer dateline;

    /** nullable persistent field */
    private String message;

    /** full constructor */
    public Doing(int uid, String username, Integer dateline, String message) {
        this.uid = uid;
        this.username = username;
        this.dateline = dateline;
        this.message = message;
    }

    /** default constructor */
    public Doing() {
    }

    /** minimal constructor */
    public Doing(int uid) {
        this.uid = uid;
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
     *             column="username"
     *             length="15"
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
