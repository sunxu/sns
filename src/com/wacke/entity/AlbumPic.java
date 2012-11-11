package com.wacke.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="album"
 *     
*/
public class AlbumPic implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3689327478391363465L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Integer uid;

    /** persistent field */
    private Integer cid;

    /** nullable persistent field */
    private String pic;

    /** full constructor */
    public AlbumPic(Integer uid, Integer cid, String pic) {
        this.uid = uid;
        this.cid = cid;
        this.pic = pic;
    }

    /** default constructor */
    public AlbumPic() {
    }

    /** minimal constructor */
    public AlbumPic(Integer uid, Integer cid) {
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
    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /** 
     *            @hibernate.property
     *             column="cid"
     *             length="11"
     *             not-null="true"
     *         
     */
    public Integer getCid() {
        return this.cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /** 
     *            @hibernate.property
     *             column="pic"
     *             length="40"
     *         
     */
    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String getThumbPic() {
    	int temp = getPic().lastIndexOf("/");
    	return getPic().subSequence(0, temp)+"/thumb_"+getPic().subSequence(temp+1, getPic().length());
    }
}
