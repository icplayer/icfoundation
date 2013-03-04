package com.lorepo.icf.utils;

import java.util.HashMap;

import com.google.gwt.core.client.JavaScriptObject;


/**
 * Funkcje pomocnicze dla javascriptu
 * 
 * @author Krzysztof Langner
 *
 */
public class JavaScriptUtils {


	public static JavaScriptObject createHashMap(HashMap<String, String> data){
		
		JavaScriptObject model = JavaScriptObject.createArray();
		
		for(String key : data.keySet()){
			addPropertyToJSArray(model, key, data.get(key));
		}
		
		return model;
	}
	
	
	public native static void addPropertyToJSArray(JavaScriptObject model, String key, String value)  /*-{
		model[key] = value;
	}-*/; 
	
	
	public native static String getArrayItemByKey(JavaScriptObject model, String key)  /*-{
		
		if(key in model){
			return model[key];
		}
		else{
			return "";
		}
	}-*/;


	public native static void addPropertyToJSArray(JavaScriptObject model, String key, int value)  /*-{
		model[key] = value;
	}-*/; 
	
	public native static void log( String message) /*-{
    	console.log( message );
	}-*/;
}
