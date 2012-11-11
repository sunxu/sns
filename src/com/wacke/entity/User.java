package com.wacke.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="user"
 *     
*/
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5676072301202787987L;
	
	public static final String UID = "uid";
	public static final String REALNAME = "realname";
	public static final String GROUPID = "groupid";
	public static final String ALLOWEDACTION = "allowedaction";
	public static final String STATUS = "status";
	public static final String AVATAR = "avatar";

    /** identifier field */
    private Integer uid;

    /** nullable persistent field */
    private String realname;

    /** nullable persistent field */
    private String password;

    /** nullable persistent field */
    private String email;

    /** nullable persistent field */
    private Integer birthday;

    /** nullable persistent field */
    private Byte sex;

    /** nullable persistent field */
    private Integer viewnum;

    /** nullable persistent field */
    private Integer lastlogintime;

    /** nullable persistent field */
    private Integer attachmaxsize;

    /** nullable persistent field */
    private Boolean hasavatar;

    /** nullable persistent field */
    private Integer experience;

    /** persistent field */
    private byte groupid;

    /** nullable persistent field */
    private String allowedaction;

    /** nullable persistent field */
    private Boolean status;

    /** nullable persistent field */
    private Integer regtime;

    /** nullable persistent field */
    private String birthProvince;

    /** nullable persistent field */
    private String birthCity;

    /** nullable persistent field */
    private String resideProvince;

    /** nullable persistent field */
    private String resideCity;

    /** full constructor */
    public User(String realname, String password, String email, Integer birthday, Byte sex, Integer viewnum, Integer lastlogintime, Integer attachmaxsize, Boolean hasavatar, Integer experience, byte groupid, String allowedaction, Boolean status, Integer regtime, String birthProvince, String birthCity, String resideProvince, String resideCity) {
        this.realname = realname;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.sex = sex;
        this.viewnum = viewnum;
        this.lastlogintime = lastlogintime;
        this.attachmaxsize = attachmaxsize;
        this.hasavatar = hasavatar;
        this.experience = experience;
        this.groupid = groupid;
        this.allowedaction = allowedaction;
        this.status = status;
        this.regtime = regtime;
        this.birthProvince = birthProvince;
        this.birthCity = birthCity;
        this.resideProvince = resideProvince;
        this.resideCity = resideCity;
    }

    /** default constructor */
    public User() {
    }

    /** minimal constructor */
    public User(byte groupid) {
        this.groupid = groupid;
    }

    /** 
     *            @hibernate.id
     *             generator-class="identity"
     *             type="java.lang.Integer"
     *             column="uid"
     *             unsaved-value="0"
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
     *             column="realname"
     *             length="15"
     *         
     */
    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    /** 
     *            @hibernate.property
     *             column="password"
     *             length="32"
     *         
     */
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     *            @hibernate.property
     *             column="email"
     *             length="45"
     *         
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /** 
     *            @hibernate.property
     *             column="birthday"
     *             length="10"
     *         
     */
    public Integer getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    /** 
     *            @hibernate.property
     *             column="sex"
     *             length="4"
     *         
     */
    public Byte getSex() {
        return this.sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /** 
     *            @hibernate.property
     *             column="viewnum"
     *             length="10"
     *         
     */
    public Integer getViewnum() {
        return this.viewnum;
    }

    public void setViewnum(Integer viewnum) {
        this.viewnum = viewnum;
    }

    /** 
     *            @hibernate.property
     *             column="lastlogintime"
     *             length="10"
     *         
     */
    public Integer getLastlogintime() {
        return this.lastlogintime;
    }

    public void setLastlogintime(Integer lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /** 
     *            @hibernate.property
     *             column="attachmaxsize"
     *             length="10"
     *         
     */
    public Integer getAttachmaxsize() {
        return this.attachmaxsize;
    }

    public void setAttachmaxsize(Integer attachmaxsize) {
        this.attachmaxsize = attachmaxsize;
    }

    /** 
     *            @hibernate.property
     *             column="hasavatar"
     *             length="1"
     *         
     */
    public Boolean getHasavatar() {
        return this.hasavatar;
    }

    public void setHasavatar(Boolean hasavatar) {
        this.hasavatar = hasavatar;
    }

    /** 
     *            @hibernate.property
     *             column="experience"
     *             length="10"
     *         
     */
    public Integer getExperience() {
        return this.experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    /** 
     *            @hibernate.property
     *             column="groupid"
     *             length="4"
     *             not-null="true"
     *         
     */
    public byte getGroupid() {
        return this.groupid;
    }

    public void setGroupid(byte groupid) {
        this.groupid = groupid;
    }

    /** 
     *            @hibernate.property
     *             column="allowedaction"
     *             length="65535"
     *         
     */
    public String getAllowedaction() {
        return this.allowedaction;
    }

    public void setAllowedaction(String allowedaction) {
        this.allowedaction = allowedaction;
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
     *             column="regtime"
     *             length="10"
     *         
     */
    public Integer getRegtime() {
        return this.regtime;
    }

    public void setRegtime(Integer regtime) {
        this.regtime = regtime;
    }

    /** 
     *            @hibernate.property
     *             column="birth_province"
     *             length="45"
     *         
     */
    public String getBirthProvince() {
        return this.birthProvince;
    }

    public void setBirthProvince(String birthProvince) {
        this.birthProvince = birthProvince;
    }

    /** 
     *            @hibernate.property
     *             column="birth_city"
     *             length="45"
     *         
     */
    public String getBirthCity() {
        return this.birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    /** 
     *            @hibernate.property
     *             column="reside_province"
     *             length="45"
     *         
     */
    public String getResideProvince() {
        return this.resideProvince;
    }

    public void setResideProvince(String resideProvince) {
        this.resideProvince = resideProvince;
    }

    /** 
     *            @hibernate.property
     *             column="reside_city"
     *             length="45"
     *         
     */
    public String getResideCity() {
        return this.resideCity;
    }

    public void setResideCity(String resideCity) {
        this.resideCity = resideCity;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("uid", getUid())
            .toString();
    }

    /** commentUser constructor */
    public User(int uid, String realname, Boolean hasavatar, Byte sex) {
    	this.uid = uid;
        this.realname = realname;
        this.hasavatar = hasavatar;
        this.sex = sex;
    }
    
    public Date getLastlogintimeToDate() {
		return new Date(getLastlogintime()*1000L);
	}
    
    public Date getRegtimeToDate() {
		return new Date(getRegtime()*1000L);
	}
    
    public String getAvatarURLPath() {
        if(getHasavatar())
        	return com.wacke.common.tool.Tool.getAvatarURLPath(getUid());
        else if(getSex() == 0)
        	return "data/avatar/default/0/";
        else
        	return "data/avatar/default/1/";
    }
}
