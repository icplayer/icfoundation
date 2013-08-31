package com.lorepo.icf.properties.ui.editor;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.lorepo.icf.properties.IEnumSetProperty;
import com.lorepo.icf.properties.IPropertyEditor;

class EnumPropertyEditor implements IPropertyEditor{

	private final IEnumSetProperty property;
	private ListBox	editorWidget;
	
	
	public EnumPropertyEditor(IEnumSetProperty property) {
		
		this.property = property;
	}

	
	@Override
	public Widget getEditor(){

		editorWidget = new ListBox();
		editorWidget.setWidth("100%");

		String value = property.getValue();
		int index = 0;
		for(int i = 0; i < property.getAllowedValueCount(); i++){
			String allowedValue = property.getAllowedValue(i);
			editorWidget.addItem(allowedValue, allowedValue);
			if(allowedValue.compareTo(value) == 0){
				index = i;
			}
		}

		editorWidget.setSelectedIndex(index);
		
		editorWidget.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
			
				int index = editorWidget.getSelectedIndex();
				String value = property.getAllowedValue(index);
				property.setValue(value);
			}
		});
		
		return editorWidget;
	}


	private void setSelection() {

		String value = property.getValue();
		for(int i = 0; i < property.getAllowedValueCount(); i++){
			String allowedValue = property.getAllowedValue(i);
			if(allowedValue.compareTo(value) == 0){
				editorWidget.setSelectedIndex(i);
				break;
			}
		}

	}
	
	@Override
	public void refresh() {

		if(editorWidget != null){
			setSelection();
		}
	}
	
}
