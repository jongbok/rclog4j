/*
 * @(#) RCLogException.java 1.0, 2014. 2. 26.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
 
package com.forif.rclog.core;
/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 26.
 * 
 */
public class RCLogException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9189827872756340329L;

	/**
	 * 
	 */
	public RCLogException() {
	}

	/**
	 * @param arg0
	 */
	public RCLogException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public RCLogException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public RCLogException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public RCLogException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
