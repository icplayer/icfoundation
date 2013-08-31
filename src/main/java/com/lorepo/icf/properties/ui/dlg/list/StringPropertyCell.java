package com.lorepo.icf.properties.ui.dlg.list;

import com.google.gwt.user.client.ui.TextBox;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.widgets.richeditor.RichTextToolbar;

public class StringPropertyCell extends TextBox implements IItemCellEditor{

	private final IProperty property;
	
	
	public StringPropertyCell(IProperty property, RichTextToolbar toolbar){
	
		this.property = property;
		setText(property.getValue());
		setWidth("100%");
	}


	@Override
	public void save() {
		property.setValue(getText());
	}
}
