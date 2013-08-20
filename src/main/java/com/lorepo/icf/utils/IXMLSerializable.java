package com.lorepo.icf.utils;

import com.google.gwt.xml.client.Element;

public interface IXMLSerializable {

	public void load(Element rootElement, String url);
	public String toXML();
}
