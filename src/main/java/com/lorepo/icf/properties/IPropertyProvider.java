package com.lorepo.icf.properties;

/**
 * Interface implemented by class with properties 
 * @author Krzysztof Langner
 *
 */
public interface IPropertyProvider {

	public String getProviderName();
	public void addPropertyListener(IPropertyListener listener);
	public void removePropertyListener(IPropertyListener listener);
	
	public int getPropertyCount();
	
	public IProperty getProperty(int index);
}
