package com.lorepo.icf.utils.i18n;

import com.google.gwt.i18n.client.Dictionary;

public class DictionaryWrapper {
	private static Dictionary dictionary;
	static {
		try {
			dictionary = Dictionary.getDictionary("ice_dictionary");
		} catch (Throwable e) {
		}
	}

	/**
	 * Reads the value from dictionary located in a hosted page.
	 * If dictionary or key is not available returns defaultValue passed
	 * or an empty string
	 * @param name Key to be found in the dictionary
	 * @param defaultValue default value to be used
	 * @return 
	 * @see Dictionary
	 */
	public static String get(String name) {
		if (dictionary != null && dictionary.keySet().contains(name)) {
			return dictionary.get(name);
		}
		return "MISSING_LABEL";
	}
	
	public static boolean contains(String name) {
		return (dictionary != null && dictionary.keySet().contains(name));
	}
	
}
