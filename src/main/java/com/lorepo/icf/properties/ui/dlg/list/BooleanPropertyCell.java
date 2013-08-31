package com.lorepo.icf.properties.ui.dlg.list;

import com.google.gwt.user.client.ui.CheckBox;
import com.lorepo.icf.properties.IBooleanProperty;
import com.lorepo.icf.properties.IProperty;

public class BooleanPropertyCell extends CheckBox implements IItemCellEditor{

	private final IProperty property;
	
	
	public BooleanPropertyCell(IBooleanProperty property){
	
		this.property = property;
		setWidth("100%");
		if(property.getValue().compareToIgnoreCase("true") == 0){
			setValue(true);
		}
		else{
			setValue(false);
		}
	}


	@Override
	public void save() {
		if(getValue()){
			property.setValue("True");
		}
		else{
			property.setValue("False");
		}
	}
}
