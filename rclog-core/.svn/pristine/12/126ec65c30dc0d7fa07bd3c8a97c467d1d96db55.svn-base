/*
 * @(#) Category.java 1.0, 2014. 2. 17.
 * 
 * Copyright (c) 2014 Jong-Bok,Park All rights reserved.
 */
 
package com.forif.rclog.core;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 17.
 * 
 */
public class Category implements Serializable, Cloneable {

	private static final long serialVersionUID = 8369315870205432871L;
	private String categoryName;
	private String level = "DEBUG";
	private Date modifiedDate;
	private String modifiedIp;
	private CRUD type = CRUD.C;
	
	public Category(){
		
	}
	
	public Category(String categoryName){
		this.categoryName = categoryName;
	}
	
	public Category(String categoryName, String level, CRUD type){
		this.categoryName = categoryName;
		this.level = level;
		this.type = type;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedIp() {
		return modifiedIp;
	}
	public void setModifiedIp(String modifiedIp) {
		this.modifiedIp = modifiedIp;
	}
	
	public CRUD getType() {
		return type;
	}
	public void setType(CRUD type) {
		this.type = type;
	}
	
	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RCLogException(e);
		}
	}
	
	@Override
	public String toString() {
		return "Category [categoryName=" + categoryName + ", level=" + level
				+ ", modifiedDate=" + modifiedDate + ", modifiedIp="
				+ modifiedIp + ", type=" + type	+ "]";
	}
	
}
