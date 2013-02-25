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
	public void setChildrenCount(int count);
	
	public IPropertyProvider getChild(int index);
}
