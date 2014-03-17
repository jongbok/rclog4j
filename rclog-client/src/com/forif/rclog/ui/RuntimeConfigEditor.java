/*
 * @(#) RuntimeConfigEditor.java 1.0, 2014. 2. 11.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
package com.forif.rclog.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.forif.rclog.client.ClientService;
import com.forif.rclog.client.RCLogClientService;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 17.
 * 
 */
public class RuntimeConfigEditor extends EditorPart {
	private ConnectGroup connectGroup = null;
	private CategoryGroup categoryGroup = null;
	private Label lbCopyright = null;

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		final Composite shell = new Composite(parent, SWT.BORDER);
		
		ClientService clientService = new RCLogClientService();
		
		connectGroup = new ConnectGroup(shell, SWT.NULL, clientService);
		connectGroup.setText("Connect Agent...");
		connectGroup.setBounds(20, 20, 800, 50);
		
		categoryGroup = new CategoryGroup(shell, SWT.NULL);
		categoryGroup.setText("Edit Category...");
		categoryGroup.setBounds(UIUtils.getBoundsV(connectGroup, 20, connectGroup.getBounds().width, 370));
		connectGroup.setCategoryView(categoryGroup);

		lbCopyright = new Label(shell, SWT.NULL);
		lbCopyright.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_GRAY));
		Font ft = new Font(shell.getDisplay(), new FontData("", 15, SWT.ITALIC | SWT.BOLD));
		lbCopyright.setFont(ft);
		lbCopyright.setText("Copyright (c) 2014 Jong-Bok,Park(asdkf20@naver.com)  All rights reserved.");
		lbCopyright.setBounds(UIUtils.getBoundsV(categoryGroup, 10, connectGroup.getBounds().width, 30));
	}

	@Override
	public void setFocus() {
	}
	
	@Override
	public void dispose(){
		if(connectGroup != null )
			connectGroup.dispose();
		if(categoryGroup != null)
			categoryGroup.dispose();
		if(lbCopyright != null)
			lbCopyright.dispose();
		super.dispose();
	}

}
