package com.lorepo.icf.properties.ui.editor;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.IPropertyEditor;

class StringPropertyEditor implements IPropertyEditor{

	private IProperty property;
	private TextBox editorWidget = new TextBox();
	
	
	public StringPropertyEditor(IProperty property) {
		this.property = property;
	}

	
	@Override
	public Widget getEditor(){
		
		editorWidget.setWidth("95%");
		editorWidget.setText(property.getValue());
		editorWidget.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				saveValue();
			}
		});
		
		editorWidget.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
					saveValue();
				}
			}
		});
		
		return editorWidget;
	}

	
	private void saveValue(){
		
		property.setValue(editorWidget.getText());
		refresh();
	}


	@Override
	public void refresh() {

		if(editorWidget != null){
			editorWidget.setText(property.getValue());
		}
	}
}
