package com.wacke.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="emailcheck"
 *     
*/
public class Emailcheck implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5683160567659782253L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int userid;

    /** nullable persistent field */
    private String hash;

    /** nullable persistent field */
    private Integer dateline;

    /** full constructor */
    public Emailcheck(int userid, String hash, Integer dateline) {
        this.userid = userid;
        this.hash = hash;
        this.dateline = dateline;
    }

    /** default constructor */
    public Emailcheck() {
    }

    /** minimal constructor */
    public Emailcheck(int userid) {
        this.userid = userid;
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
     *             column="userid"
     *             length="11"
     *             not-null="true"
     *         
     */
    public int getUserid() {
        return this.userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    /** 
     *            @hibernate.property
     *             column="hash"
     *             length="70"
     *         
     */
    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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

}
