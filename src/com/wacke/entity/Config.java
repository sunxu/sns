package com.wacke.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="config"
 *     
*/
public class Config implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7066990183849884024L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String var;

    /** nullable persistent field */
    private String datavalue;

    /** nullable persistent field */
    private Byte type;

    /** full constructor */
    public Config(String var, String datavalue, Byte type) {
        this.var = var;
        this.datavalue = datavalue;
        this.type = type;
    }

    /** default constructor */
    public Config() {
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
     *             column="var"
     *             length="30"
     *         
     */
    public String getVar() {
        return this.var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    /** 
     *            @hibernate.property
     *             column="datavalue"
     *             length="65535"
     *         
     */
    public String getDatavalue() {
        return this.datavalue;
    }

    public void setDatavalue(String datavalue) {
        this.datavalue = datavalue;
    }

    /** 
     *            @hibernate.property
     *             column="type"
     *             length="4"
     *         
     */
    public Byte getType() {
        return this.type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
