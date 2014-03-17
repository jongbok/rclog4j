/*
 * @(#) Console.java 1.0, 2014. 2. 27.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
 
package com.forif.rclog.ui;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 27.
 * 
 */
public class Console extends MessageConsole {

	private static Console console;
	private static final Color RED;
	private static final Color BLUE;
	private static final Color BLACK;
	
	static{
		Display device = Display.getCurrent();
		RED = new Color(device, 255, 0, 0);
		BLUE = new Color(device, 0, 0, 128);
		BLACK = new Color(device, 0, 0, 0);
	}
	
	private Console(){
		super("RCLog4j", null);
		activate();
		MessageConsole console = new MessageConsole("RCLog4j", null);
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{ this });
	}
	
	public static synchronized Console getInstance(){
		if( console == null)
			console = new Console();
		return console;
	}	
	
	public synchronized void log(String message){
		final String text = message;

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageConsoleStream stream = newMessageStream();
				stream.setColor(Console.BLACK);
				stream.print(text);
			}});
	}

	public synchronized void logln(String message){
		final String text = message;

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageConsoleStream stream = newMessageStream();
				stream.setColor(Console.BLACK);
				stream.println(text);
			}});
	}
	
	public synchronized void stat(String message){
		final String text = message;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageConsoleStream stream = newMessageStream();
				stream.setColor(Console.BLUE);
				stream.print(text);
			}});
		
	}
	
	public synchronized void statln(String message){
		final String text = message;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageConsoleStream stream = newMessageStream();
				stream.setColor(Console.BLUE);
				stream.println(text);
			}});
		
	}
	
	
	public synchronized void error(String message){
		final String text = message;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageConsoleStream stream = newMessageStream();
				stream.setColor(Console.RED);
				stream.print(text);
			}});
		
	}

	public synchronized void errorln(String message){
		final String text = message;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageConsoleStream stream = newMessageStream();
				stream.setColor(Console.RED);
				stream.println(text);
			}});
		
	}
	
}
