/*
 * @(#) CategoryMBean.java 1.0, 2014. 2. 17.
 * 
 * Copyright (c) 2014 ������  All rights reserved.
 */
 
package com.forif.rclog.jmx;

import java.util.List;

import com.forif.rclog.core.Category;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 17.
 * 
 */
public interface CategoryEmitterMBean {

	public List<Category> getCategories();
	
	public void setCategories(List<Category> categories, String remoteIp);
	
}
