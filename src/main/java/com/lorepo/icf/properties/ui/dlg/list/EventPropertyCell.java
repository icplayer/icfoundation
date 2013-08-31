package com.lorepo.icf.properties.ui.dlg.list;

import com.google.gwt.user.client.ui.TextArea;
import com.lorepo.icf.properties.IEventProperty;

public class EventPropertyCell extends TextArea implements IItemCellEditor{

	private final IEventProperty property;
	
	public EventPropertyCell(IEventProperty property){
	
		this.property = property;
		setText(property.getValue());
		setStyleName("ic_scriptEditor");
		setWidth("100%");
		setHeight("3em");
	}


	@Override
	public void save() {
		property.setValue(getText());
	}
}
