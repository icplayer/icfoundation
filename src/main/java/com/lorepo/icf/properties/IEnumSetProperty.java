package com.lorepo.icf.properties;

public interface IEnumSetProperty extends IProperty {

	public int getAllowedValueCount();
	
	public String getAllowedValue(int index);
}
