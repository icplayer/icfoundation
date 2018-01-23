package com.lorepo.icf.utils.dom;

import com.google.gwt.dom.client.Element;


public class ElementHTMLUtils {

	static public final boolean hasClass (Element element, String className) {
		String[] elementClassNames = element.getClassName().split(" ");
		
		for (String elementClassName: elementClassNames) {
			if (elementClassName.equals(className)) {
				return true;
			}
		}
		
		return false;
	}
	
}
