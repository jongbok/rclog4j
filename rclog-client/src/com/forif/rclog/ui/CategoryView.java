/*
 * @(#) CategoryView.java 1.0, 2014. 2. 24.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
 
package com.forif.rclog.ui;

import java.util.List;

import com.forif.rclog.core.Category;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 24.
 * 
 */
public interface CategoryView {

	public void refreshCategories(List<Category> categories);
	
	public void connected();
	
	public void disConnected();
	
	public List<Category> getEditCategories();
}
