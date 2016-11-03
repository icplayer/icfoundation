package com.lorepo.icf.properties;

public interface IEditableSelectProperty extends IProperty {
	public int getChildrenCount();
	public IProperty getChild(int index);
	public int getSelectedIndex();
}
