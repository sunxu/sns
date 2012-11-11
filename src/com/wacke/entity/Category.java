package com.wacke.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="category"
 *     
*/
public class Category implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7762067694084359405L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int uid;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String image;

    /** full constructor */
    public Category(int uid, String type, String title, String image) {
        this.uid = uid;
        this.type = type;
        this.title = title;
        this.image = image;
    }

    /** default constructor */
    public Category() {
    }

    /** minimal constructor */
    public Category(int uid) {
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
     *             column="title"
     *             length="45"
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
     *             column="image"
     *             length="100"
     *         
     */
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public Category(Integer id) {
    	this.id = id;
    }
    
    public Category(int uid, String type, String title) {
        this.uid = uid;
        this.type = type;
        this.title = title;
    }
}
