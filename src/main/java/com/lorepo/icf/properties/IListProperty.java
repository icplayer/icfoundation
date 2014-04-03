package com.lorepo.icf.properties;

/**
 * Lista zawierającą pod property. Np. choice zawiera property items
 * określające ilość elementów opcji
 * 
 * @author Krzysztof Langner
 *
 */
public interface IListProperty extends IProperty {

	public int getChildrenCount();
	public void addChildren(int count);
	public IPropertyProvider getChild(int index);
	public void removeChildren(int index);
	public void moveChildUp(int index);
	public void moveChildDown(int index);
}
