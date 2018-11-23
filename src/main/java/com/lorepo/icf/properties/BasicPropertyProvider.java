package com.lorepo.icf.properties;

import java.util.ArrayList;
import java.util.List;


/**
 * Klasa implementuje bazowe functionalności potrzebne wszystkim property provider
 * 
 * @author Krzysztof Langner
 */
public class BasicPropertyProvider implements IPropertyProvider {

	private List<IProperty>	properties = new ArrayList<IProperty>();
	private List<IPropertyListener>	propertyListeners = new ArrayList<IPropertyListener>();
	private final String name;
	
	
	public BasicPropertyProvider(String name){
		this.name = name;
	}
	
	@Override
	public void addPropertyListener(IPropertyListener listener){
	
		propertyListeners.add(listener);
	}
	
	@Override
	public void removePropertyListener(IPropertyListener listener){
	
		propertyListeners.remove(listener);
	}
	
	
	@Override
	public int getPropertyCount() {
		return properties.size();
	}

	@Override
	public IProperty getProperty(int index) {
		return properties.get(index);
	}

	public IProperty getPropertyByName(String name) {
		
		for(IProperty property : properties){
			if(property.getName().compareTo(name) == 0){
				return property;
			}
		}
		
		return null;
	}

	public void addProperty(IProperty property){
		properties.add(property);
	}

	public void sendPropertyChangedEvent(IProperty source){
		
		for(IPropertyListener listener : propertyListeners){
			listener.onPropertyChanged(source);
		}
	}

	@Override
	public String getProviderName() {
		return name;
	}


	public void removeProperty(IProperty property) {
		properties.remove(property);
	}
}
