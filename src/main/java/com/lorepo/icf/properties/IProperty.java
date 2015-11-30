package com.lorepo.icf.properties;

public interface IProperty{

	/** Property name. Not localizable */
	public String getName();
	public String getValue();
	/** Name used by property grid in the editor */
	public String getDisplayName();
	public void setValue(String newValue);
	public boolean isDefault();
}
