package com.lorepo.icf.utils;

/*
 * Description of module of order configuration from TextToSpeech addOn
 */
public class NavigationModuleIndentifier {
	private String id;
	private String area;
	
	NavigationModuleIndentifier (String id, String area) {
		this.id = id;
		this.area = area;
	}
	
	public String getId () {
		return this.id;
	}
	
	public String getArea () {
		return this.area;
	}
	
}
