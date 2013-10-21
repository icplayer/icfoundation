package com.lorepo.icf.properties;

public interface IProperty {

	public String getName();
	public String getValue();
	public String getDisplayName();
	public void setValue(String newValue);
}
