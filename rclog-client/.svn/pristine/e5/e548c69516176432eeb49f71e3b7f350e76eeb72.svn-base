/*
 * @(#) RuntimeConfigEditorHandler.java 1.0, 2014. 2. 11.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
package com.forif.rclog.ui;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.part.NullEditorInput;


/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 17.
 * 
 */
@SuppressWarnings("restriction")
public class RuntimeConfigEditorHandler extends AbstractHandler implements IHandler {
	private final static String ID = "com.forif.rclog.ui.RuntimeConfigEditor";
	private final static IEditorInput EDITOR_INPUT = new NullEditorInput(); 

	public Object execute(Map<?, ?> parameterValuesByName) throws ExecutionException {
		return null;
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		
		try {
			page.openEditor(EDITOR_INPUT, ID);
		} catch (PartInitException e) {
			e.printStackTrace();
			throw new ExecutionException(e.getMessage(), e);
		}

		return null;
	}

}
