package com.lorepo.icf.properties;

public interface IStaticListProperty extends IProperty {

	public int getChildrenCount();
	public void addChildren(int count);
	public IPropertyProvider getChild(int index);
	public void moveChildUp(int index);
	public void moveChildDown(int index);
}
