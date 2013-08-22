package com.lorepo.icf.properties;

import java.util.HashMap;

/**
 * This property allows to pass hash map with values instead of string 
 */
public interface IComplexProperty extends IProperty {

	public HashMap<String,String> getDataValue();
	public void setDataValue(HashMap<String,String> data);
}
