package com.wacke.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="forum"
 *     
*/
public class Forum implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6797755372057225615L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int cid;

    /** persistent field */
    private int userUid;

    /** full constructor */
    public Forum(int cid, int userUid) {
        this.cid = cid;
        this.userUid = userUid;
    }

    /** default constructor */
    public Forum() {
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
     *             column="user_uid"
     *             length="11"
     *             not-null="true"
     *         
     */
    public int getUserUid() {
        return this.userUid;
    }

    public void setUserUid(int userUid) {
        this.userUid = userUid;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
