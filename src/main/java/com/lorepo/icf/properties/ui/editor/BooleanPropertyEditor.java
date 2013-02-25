package com.lorepo.icf.properties.ui.editor;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.IPropertyEditor;

/**
 * Property edytor oparty o dialogbox
 * 
 * @author Krzysztof Langner
 *
 */
public class BooleanPropertyEditor implements IPropertyEditor{

	private IProperty 	property;
	private CheckBox	editorWidget;
	
	
	public BooleanPropertyEditor(IProperty property) {
		
		this.property = property;
	}

	
	@Override
	public Widget getEditor(){

		editorWidget = new CheckBox();
		editorWidget.setWidth("100%");

		setCheckBoxState();
		
		editorWidget.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if(editorWidget.getValue()){
					property.setValue("True");
				}
				else{
					property.setValue("False");
				}
			}
		});
		
		return editorWidget;
	}


	private void setCheckBoxState() {
		if(property.getValue().compareToIgnoreCase("true") == 0){
			editorWidget.setValue(true);
		}
	}

	
	@Override
	public void refresh() {

		if(editorWidget != null){
			setCheckBoxState();
		}
	}
}
