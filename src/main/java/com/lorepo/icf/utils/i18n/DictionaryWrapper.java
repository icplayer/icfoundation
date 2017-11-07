package com.lorepo.icf.utils.i18n;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.i18n.client.Dictionary;

public class DictionaryWrapper {
	private static Dictionary dictionary;
	private static Set<String> cachedKeySet = new HashSet<String>();
	
	static {
		try {
			dictionary = Dictionary.getDictionary("ice_dictionary_en");
			if (dictionary != null) {
				cachedKeySet = dictionary.keySet(); 	
			}
		} catch (Throwable e) {
		}
	}
	
	public static void setLang(String lang) {
		if (lang.equals("es")) {
			lang = "mx";
		}
		try {
			dictionary = Dictionary.getDictionary("ice_dictionary_" + lang);
			if (dictionary != null) {
				cachedKeySet = dictionary.keySet();	
			}
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
		if (contains(name)) {
			return dictionary.get(name);
		}
		
		return "MISSING_LABEL";
	}
	
	public static boolean contains(String name) {
		return (dictionary != null && cachedKeySet.contains(name));
	}
	
}
