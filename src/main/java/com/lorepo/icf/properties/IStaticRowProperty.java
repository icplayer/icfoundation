package com.lorepo.icf.properties;

public interface IStaticRowProperty extends IProperty {
	public int getChildrenCount();
	public IPropertyProvider getChild(int index);
}
