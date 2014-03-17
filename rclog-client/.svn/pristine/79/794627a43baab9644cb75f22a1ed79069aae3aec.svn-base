/*
 * @(#) JmxClientService.java 1.0, 2014. 2. 11.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
package com.forif.rclog.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.management.InstanceNotFoundException;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import jmxlogger.tools.JmxLogEmitterMBean;
import jmxlogger.tools.ToolBox;
import jmxlogger.tools.console.ClientConnectionListener;

import com.forif.rclog.core.Category;
import com.forif.rclog.core.RCLogException;
import com.forif.rclog.core.RCLogInfo;
import com.forif.rclog.jmx.CategoryEmitterMBean;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 17.
 * 
 */
public class RCLogClientService implements ClientService {
    private JMXServiceURL serviceUrl;
    private JMXConnector connector;
    private MBeanServerConnection server;
    private ClientConnectionListener connListener;
    private JmxLogEmitterMBean logEmitter;
    private CategoryEmitterMBean categoryEmitter;

    public RCLogClientService(){}
    public RCLogClientService(ClientConnectionListener l){
        connListener = l;
    }

    public String connect(String url, String uname, String pwd){
        try{
            serviceUrl = ToolBox.createServiceUrlFromString(url);

            HashMap<String, Object> env = new HashMap<String, Object>(1);
            env.put (JMXConnector.CREDENTIALS, new String[] {uname, pwd});
            connector = JMXConnectorFactory.connect(serviceUrl, env);
            server = connector.getMBeanServerConnection();

            if(connListener != null){
                NotificationListener noteListener = new NotificationListener(){
                    public void handleNotification(Notification notification, Object handback) {
                    	System.out.println("notification.getType :: " + notification.getType());
                        if(notification.getType().equals("jmx.remote.connection.opened")){
                            connListener.onConnectionOpened();
                        }
                        if(notification.getType().equals("jmx.remote.connection.closed")){
                            connListener.onConnectionClosed();
                        }
                        if(notification.getType().equals("jmx.remote.connection.failed")){
                            connListener.onConnectionFailed();
                        }
                    }
                };

                connector.addConnectionNotificationListener(noteListener, null, null);

            }
            
            connector.connect();
            logEmitter = this.getLogEmitter(ToolBox.buildObjectName(RCLogInfo.JMXLOG_OBJECT_NAME));
            categoryEmitter = this.getCategoryEmitter(ToolBox.buildObjectName(RCLogInfo.CATEOGRY_OBJECT_NAME));
            
            return connector.getConnectionId();

        }catch(IOException ioe){
            throw new RuntimeException("Error while connecting to MBeanServer: " + ioe.getMessage(), ioe);
        }catch(Exception ex){
            throw new RuntimeException("Unable to connect to server: " +  ex.getMessage(), ex);
        }
    }

    public String getServiceUrl(){
        return (serviceUrl != null) ? serviceUrl.toString() : null;
    }

    public String getConnectionId() {
        try{
            return (connector != null) ? connector.getConnectionId() : null;
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }

    public void disconnect(){
        try {
            connector.close();
        } catch (IOException ex) {
            throw new RuntimeException("Unable to close connection to JMX server: " + ex.getMessage(), ex);
        }
    }

    public void setConnectionListener(ClientConnectionListener l){
        connListener = l;
    }

    public JmxLogEmitterMBean getLogEmitter(ObjectName emitterName){
        if(server != null){
            return JMX.newMBeanProxy(server, emitterName, JmxLogEmitterMBean.class, true);
        }
        return null;
    }
    
    public CategoryEmitterMBean getCategoryEmitter(ObjectName emitterName){
    	if(server != null){
    		return JMX.newMBeanProxy(server, emitterName, CategoryEmitterMBean.class, true);
    	}
    	return null;
    }

    public void addListenerToEmitter(ObjectName emitterName, NotificationListener listener){
        try {
            server.addNotificationListener(emitterName, listener, null, null);
        } catch (InstanceNotFoundException ex) {
            throw new RCLogException("Error adding log emitter listener: " + ex.getMessage(), ex);
        } catch (IOException ex) {
            throw new RCLogException("Error adding log emitter listener: " + ex.getMessage(), ex);
        }
    }
    
    public List<Category> getCategories(){
   		return categoryEmitter.getCategories();
    }
    
    public void setCategories(List<Category> editList, String remoteIp){
		categoryEmitter.setCategories(editList, remoteIp);
    }
    
    public void setLevel(String level){
    	logEmitter.setLevel(level);
    }
    
    public void setExpressionFilter(String expression){
    	logEmitter.setFilterExpression(expression);
    }
}
