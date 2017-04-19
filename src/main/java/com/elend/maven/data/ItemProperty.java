package com.elend.maven.data;

import com.elend.maven.util.NameFormator;

/**
 * 字段属性
 * 
 * @author liuzifeng
 *
 */
public class ItemProperty {
	
	/** 字段属性名称 */
	private String name;
	/** 首字母大写名称*/
	private String firstBigName;
	
	/** 字段类型（int, varchar, ...） */
	private String type;
	
	/** 字段Java类型（int, String, ...） */
	private String jtype;
	
	/** 字段描述 */
	private String comment;
	
	/** 是否主键 */
	private boolean priKey;
	
	/** 字段Java类型(装箱类型)（Integer, String, ...） */
	private String referenceType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isPriKey() {
		return priKey;
	}

	public void setPriKey(boolean priKey) {
		this.priKey = priKey;
	}

	public String getJtype() {
		return jtype;
	}

	public void setJtype(String jtype) {
		this.jtype = jtype;
	}

	public String getFirstBigName() {
		if(name != null && name.trim().length()!=0){
			firstBigName = NameFormator.toUfirst(name) ;
		}
		return firstBigName;
	}

	public void setFirstBigName(String firstBigName) {
		this.firstBigName = firstBigName;
	}

	public String getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	
	/**
	 * 获取驼峰形式的名称(ab_cd->AbCd)
	 * @return
	 */
	public String getCamelCase(){
		String fn="";
		if(name != null && name.trim().length()!=0){
			fn = NameFormator.toUUCase(name) ;
		}
		return fn;
	}
	
	/**
	 * 获取属性名称(ab_cd->abCd)
	 * @return
	 */
	public String getPropertityName(){
		String fn="";
		if(name != null && name.trim().length()!=0){
			fn = NameFormator.toUUCase(name) ;
		}
		return NameFormator.toLFirst(fn);
	}
}
