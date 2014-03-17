/*
 * @(#) ClientService.java 1.0, 2014. 2. 27.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
 
package com.forif.rclog.client;

import java.util.List;

import javax.management.NotificationListener;
import javax.management.ObjectName;

import com.forif.rclog.core.Category;
import com.forif.rclog.jmx.CategoryEmitterMBean;

import jmxlogger.tools.JmxLogEmitterMBean;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 27.
 * 
 */
public interface ClientService {

	String connect(String url, String uname, String pwd);
	
	void disconnect();
	
	JmxLogEmitterMBean getLogEmitter(ObjectName emitterName);
	
	CategoryEmitterMBean getCategoryEmitter(ObjectName emitterName);
	
	void addListenerToEmitter(ObjectName emitterName, NotificationListener listener);
	
	List<Category> getCategories();
	
	void setCategories(List<Category> editList, String remoteIp);
	
	String getServiceUrl();
	
	String getConnectionId();
	
}
