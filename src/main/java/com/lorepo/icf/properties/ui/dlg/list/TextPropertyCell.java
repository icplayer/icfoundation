package com.lorepo.icf.properties.ui.dlg.list;

import com.google.gwt.user.client.ui.TextArea;
import com.lorepo.icf.properties.ITextProperty;

public class TextPropertyCell extends TextArea implements IItemCellEditor{

	private final ITextProperty property;
	
	public TextPropertyCell(ITextProperty property){
	
		this.property = property;
		setText(property.getValue());
		setStyleName("ic_textEditor");
		setWidth("100%");
		setHeight("3em");
	}


	@Override
	public void save() {
		property.setValue(getText());
	}
}
