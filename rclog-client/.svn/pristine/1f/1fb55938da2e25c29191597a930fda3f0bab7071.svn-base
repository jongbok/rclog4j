/*
 * @(#) CategoryTable.java 1.0, 2014. 2. 11.
 * 
 * Copyright (c) 2014 Jong-Bok,Park  All rights reserved.
 */
package com.forif.rclog.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Jong-Bok,Park (asdkf20@naver.com)
 * @version 1.0,  2014. 2. 17.
 * 
 */
public class CategoryTable extends Table {

	private List<TableColumn> columns = new ArrayList<TableColumn>();

	public CategoryTable(Composite parent, int style) {
		super(parent, style);
		this.init();
	}

	private void init(){
		TableColumn colSeq = new TableColumn(this, SWT.CENTER);
		colSeq.setText("Seq");
		colSeq.setWidth(50);
		columns.add(colSeq);
		TableColumn colCategory = new TableColumn(this, SWT.LEFT);
		colCategory.setText("Category");
		colCategory.setWidth(330);
		columns.add(colCategory);
		TableColumn colLevel = new TableColumn(this, SWT.CENTER);
		colLevel.setText("Level");
		colLevel.setWidth(70);
		columns.add(colLevel);
		TableColumn colModifiedDate = new TableColumn(this, SWT.CENTER);
		colModifiedDate.setText("Modified Date");
		colModifiedDate.setWidth(150);
		columns.add(colModifiedDate);
		TableColumn colModifiedIp = new TableColumn(this, SWT.CENTER);
		colModifiedIp.setText("Modified IP");
		colModifiedIp.setWidth(140);
		columns.add(colModifiedIp);
	}
	
	@Override
	public void dispose(){
		for(TableColumn column : columns){
			column.dispose();
		}
		columns.clear();
		super.dispose();
	}
	
	@Override
	public void checkSubclass(){
		
	}

}
