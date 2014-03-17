/*
 * @(#) ConnectGroup.java 1.0, 2014. 2. 11.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
package com.forif.rclog.ui;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.Notification;
import javax.management.NotificationListener;

import jmxlogger.tools.ToolBox;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.forif.rclog.client.ClientService;
import com.forif.rclog.core.Category;
import com.forif.rclog.core.RCLogInfo;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 17.
 * 
 */
public class ConnectGroup extends Group {

    private final PriorityQueue<Notification> queue = new PriorityQueue<Notification>(5);
	private List<Control> controls = new ArrayList<Control>();
    private ClientService clientService;
    private volatile boolean connected = false;
    private volatile boolean logging = true;
    private String userName = "";
    private String passWord = "";
    
    private Cursor defaultCursor;
    private Cursor waitCursor = new Cursor(this.getDisplay(), SWT.CURSOR_WAIT);
    private CategoryView categoryView;
    private Label lbAgentIp;
    private Text txAgentIp;
    private Label lbAgentPort;
    private Text txAgentPort;
    private Button btnConnect;
	private Button btnRefresh;
	private Button btnSave;
    @SuppressWarnings("unused")
	private Thread refreshThread;
	
	public ConnectGroup(Composite parent, int style) {
		super(parent, style);
		this.init();
	}
	
	public ConnectGroup(Composite parent, int style, ClientService clientService){
		super(parent, style);
		this.clientService = clientService;
		this.init();
	}
	
	public void setClientService(ClientService clientService){
		this.clientService = clientService;
	}
	
	public void setCategoryView(CategoryView categoryView){
		this.categoryView = categoryView;
	}
	
	private void init(){
		controls.clear();
		lbAgentIp = new Label(this, SWT.NULL);
		lbAgentIp.setText("Agent IP : ");
		lbAgentIp.setBounds( 20, 20, 70, 20);
		controls.add(lbAgentIp);
		txAgentIp = new Text(this, SWT.SINGLE | SWT.BORDER);
		txAgentIp.setBounds( UIUtils.getBoundsH(lbAgentIp, 5, 100) );
		txAgentIp.setTextLimit(15);
		controls.add(txAgentIp);
		lbAgentPort = new Label(this, SWT.NULL);
		lbAgentPort.setText("Port : ");
		lbAgentPort.setBounds( UIUtils.getBoundsH(txAgentIp, 30, 40));
		controls.add(lbAgentPort);
		txAgentPort = new Text(this, SWT.SINGLE | SWT.BORDER);
		txAgentPort.setBounds( UIUtils.getBoundsH(lbAgentPort, 5, 50));
		txAgentPort.setTextLimit(5);
		controls.add(txAgentPort);
		btnConnect = new Button(this, SWT.PUSH);
		btnConnect.setText("Connect");
		btnConnect.setBounds( UIUtils.getBoundsH(txAgentPort, 30, 60));
		btnConnect.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				try{
					if(!connected){
						String ip = ConnectGroup.this.txAgentIp.getText();
						String port = ConnectGroup.this.txAgentPort.getText();
						if(ip == null || "".equals(ip)){
							MessageDialog.openError(ConnectGroup.this.getShell(), "Connect", "Input IP address!");
							ConnectGroup.this.txAgentIp.setFocus();
							return;
						}
						if(port == null || "".equals(port)){
							MessageDialog.openError(ConnectGroup.this.getShell(), "Connect", "Input Port number!");
							ConnectGroup.this.txAgentPort.setFocus();
							return;
						}
						Pattern p = Pattern.compile("((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})");
						Matcher m = p.matcher(ip);
						if(!m.find()){
							MessageDialog.openError(ConnectGroup.this.getShell(), "Connect", "It's not valid IP!");
							ConnectGroup.this.txAgentIp.setFocus();
							return;
						}
						try{
							Integer.parseInt(port);
						}catch(NumberFormatException ex){
							MessageDialog.openError(ConnectGroup.this.getShell(), "Connect", "It's not valid Port!");
							ConnectGroup.this.txAgentPort.setFocus();
							return;
						}
						
						btnRefresh.setEnabled(true);
						btnSave.setEnabled(true);
			        	categoryView.connected();
						setupConnection();
						Runnable runnable = new RefreshRunnable();
						Thread refreshThread = new Thread(runnable);
						refreshThread.setDaemon(true);
						refreshThread.start();
					}else{
						ConnectGroup.this.clientService.disconnect();
						ConnectGroup.this.connected = false;
						ConnectGroup.this.logging = false;
						ConnectGroup.this.txAgentIp.setEnabled(true);
						ConnectGroup.this.txAgentPort.setEnabled(true);
						ConnectGroup.this.btnConnect.setText("Connect");
						btnRefresh.setEnabled(false);
						btnSave.setEnabled(false);
						categoryView.disConnected();
						Console.getInstance().statln("Connection to server closed.");
					}		
				}finally{
					ConnectGroup.this.setCursor(defaultCursor);
				}
			}
			
			public void mouseDown(MouseEvent e) {
				defaultCursor = ConnectGroup.this.getCursor();
				ConnectGroup.this.setCursor(waitCursor);
			}
			
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		controls.add(btnConnect);
		
		btnRefresh = new Button(this, SWT.PUSH);
		btnRefresh.setText("Refresh");
		btnRefresh.setEnabled(false);
		btnRefresh.addMouseListener(new MouseListener() {
			
			public void mouseUp(MouseEvent e) {
				try{
					List<Category> categories = clientService.getCategories();
					categoryView.refreshCategories(categories);
				}finally{
					ConnectGroup.this.setCursor(defaultCursor);
				}
			}
			
			public void mouseDown(MouseEvent e) {
				defaultCursor = ConnectGroup.this.getCursor();
				ConnectGroup.this.setCursor(waitCursor);
			}
			
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		controls.add(btnRefresh);
		
		btnSave = new Button(this, SWT.PUSH);
		btnSave.setText("Save");
		btnSave.setEnabled(false);
		btnSave.addMouseListener(new MouseListener() {
			
			public void mouseUp(MouseEvent e) {
	           	List<Category> editList = categoryView.getEditCategories();
	           	if(editList == null || editList.size() == 0){
	           		MessageDialog.openError(ConnectGroup.this.getShell(), "Save", "Empty modified categories!");
	           		return;
	           	}
	           	
	           	boolean confirm = MessageDialog.openConfirm(ConnectGroup.this.getShell(), "Save", "Are you sure save them?");
	           	if(!confirm)
	           		return;
	           	
	           	defaultCursor = ConnectGroup.this.getCursor();
	           	ConnectGroup.this.setCursor(waitCursor);
				String remoteIp = StringUtils.EMPTY;
	           	try {
					remoteIp = InetAddress.getLocalHost().getHostAddress();
		           	clientService.setCategories(editList, remoteIp);
				} catch (UnknownHostException ex) {
					Console.getInstance().statln("Unable to get localhost IP : " + ex.getMessage());
					remoteIp = "UnKnown";
				} finally {
		           	ConnectGroup.this.setCursor(defaultCursor);
				}
			}
			
			public void mouseDown(MouseEvent e) {
			}
			
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		controls.add(btnSave);
		
		this.addControlListener(new ControlListener() {
			
			public void controlResized(ControlEvent e) {
				btnRefresh.setBounds(ConnectGroup.this.getBounds().width - btnConnect.getBounds().width - 20
						, btnConnect.getBounds().y
						, btnConnect.getBounds().width
						, btnConnect.getBounds().height);
				btnSave.setBounds(UIUtils.getBoundsH(btnRefresh, 10, btnConnect.getBounds().width, UIUtils.LEFT));
			}
			
			public void controlMoved(ControlEvent e) {
				controlResized(e);
			}
		});
		
	}
	
    private void setupConnection(){
        String connId = null;
    	Cursor defaultCursor = ConnectGroup.this.getCursor();
        try {
        	ConnectGroup.this.setCursor(waitCursor);
            connId = clientService.connect(
                    this.txAgentIp.getText() + ":" + this.txAgentPort.getText(),
                    this.userName,
                    this.passWord);
            
            this.connected = true;
            this.logging = true;
            this.txAgentIp.setEnabled(false);
            this.txAgentPort.setEnabled(false);
            this.btnConnect.setText("Release");
            Console.getInstance().statln("Connection established with the server.");
        } catch (Exception ex) {
            connected = false;
            Console.getInstance().statln("Unable to connect MBean server: " + ex.getMessage() );
        } finally {
        	ConnectGroup.this.setCursor(defaultCursor);
        }

        if (connId != null) {
            connected = true;
            logging = true;
            Console.getInstance().statln("Connected: " + this.userName + "@" + clientService.getServiceUrl());

            List<Category> categories = clientService.getCategories();
            categoryView.refreshCategories(categories);

            clientService.addListenerToEmitter(
                    ToolBox.buildObjectName(RCLogInfo.JMXLOG_OBJECT_NAME),
                    new NotificationListener() {

                        public void handleNotification(Notification notification, Object handback) {
                            if (ConnectGroup.this.logging) {
                                try {
                                    @SuppressWarnings("unchecked")
									Map<String, Object> data = (Map<String, Object>) notification.getUserData();
                                    String formattedMsg = (String) data.get(ToolBox.KEY_EVENT_FORMATTED_MESSAGE);
                                    String level = (String)data.get(ToolBox.KEY_EVENT_LEVEL);
                                    if(StringUtils.equals(level, "ERROR") || StringUtils.equals(level, "FATAL"))
                                    	Console.getInstance().error(formattedMsg);
                                    else
                                    	Console.getInstance().log(formattedMsg);                                   
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
            
            clientService.addListenerToEmitter(
            		  ToolBox.buildObjectName(RCLogInfo.CATEOGRY_OBJECT_NAME) 
            		, new NotificationListener(){

						public void handleNotification(
								Notification notification, Object handback) {
							queue.add(notification);
						}
            			  
            		  });
            
            clientService.setCategories(null, null);
        }
    }

    
	@Override
	public void dispose(){
		refreshThread = null;
		for(Control control : controls){
			control.dispose();
		}
		controls.clear();
		super.dispose();
	}
	
	@Override
	public void checkSubclass(){
		
	}
	
	class RefreshRunnable implements Runnable{

		public void run() {
			while(!ConnectGroup.this.isDisposed()){
				
				if(queue.size() > 0){
					ConnectGroup.this.getDisplay().asyncExec(new Runnable(){
						public void run() {
							Notification notification = queue.poll();
							@SuppressWarnings("unchecked")
							List<Category> categories = (List<Category>) notification.getUserData();
							String message = notification.getMessage();
							MessageDialog.openWarning(ConnectGroup.this.getShell(), "Agent Message", message);
							categoryView.refreshCategories(categories);
						}
						
					});
				}
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
	}

}
