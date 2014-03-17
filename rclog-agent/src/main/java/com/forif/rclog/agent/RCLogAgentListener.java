/*
 * @(#) RCLogAgentListener.java 1.0, 2014. 2. 27.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
 
package com.forif.rclog.agent;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 27.
 * 
 */
public class RCLogAgentListener implements ServletContextListener {
	private Thread testThread;

	public RCLogAgentListener() {}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		testThread = null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		boolean isTest = false;
		if(context != null){
			String layout = context.getInitParameter("rclog4j.layout");
			String level = context.getInitParameter("rclog4j.level");
			String test = context.getInitParameter("rclog4j.test");
			if(StringUtils.isNotEmpty(layout))
				RCLogManager.setLayout(layout);
			if(StringUtils.isNotEmpty(level))
				RCLogManager.setLevel(Level.toLevel(level));
			isTest = StringUtils.equals(test, "yes") || StringUtils.equals(test, "true");
		}
		RCLogManager.start();

		if(isTest){
			testThread = new Thread(){
				private int count = 1;
				Logger logger1 = Logger.getLogger("com.forif.rclog.agent");
				Logger logger2 = Logger.getLogger("com.forif.rclog");
				Logger logger3 = Logger.getLogger("com.forif");
				
				@Override
				public void run(){
					while(true){
						logger1.debug("logger1 debug message - " + count );
						logger1.info("logger1 info message - " + count );
						logger1.error("logger1 error message - " + count );
						logger2.debug("logger2 debug message - " + count );
						logger2.info("logger2 info message - " + count);
						logger2.error("logger2 error message - " + count);
						logger3.debug("logger3 debug message - " + count);
						logger3.info("logger3 info message - " + count);
						logger3.error("logger3 error message - " + count);
						if(count++ > 60*60)
							break;
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
							break;
						}
					}
				}
			};
			testThread.run();
		}
	}

}
