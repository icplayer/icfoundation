package com.lorepo.icf.utils.i18n;

import java.util.List;
import java.util.Arrays;

import com.google.gwt.i18n.client.Dictionary;

public class DictionaryWrapper {
	private static Dictionary dictionary;
	private static List<String> languages;
	static {
		languages = Arrays.asList(new String[] {"en", "fr", "mx", "pl"});
		try {
			dictionary = Dictionary.getDictionary("ice_dictionary_en");
		} catch (Throwable e) {
		}
	}
	
	public static void setLang(String lang) {
		if (languages.contains(lang)){
			try {
				dictionary = Dictionary.getDictionary("ice_dictionary_" + lang);
			} catch (Throwable e) {
			}			
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
