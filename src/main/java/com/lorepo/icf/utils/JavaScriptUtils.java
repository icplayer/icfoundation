package com.lorepo.icf.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;


/**
 * Javascript helper functions
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
	
	public native static void addElementToJSArray(JavaScriptObject model, String value) /*-{
		model.push(value);
	}-*/;
	
	public native static String getArrayItemByKey(JavaScriptObject model, String key)  /*-{

		if (key in model) {
			return model[key].toString();
		} else {
			return "";
		}
	}-*/;


	public native static void addPropertyToJSArray(JavaScriptObject model,
			String key, int value) /*-{
		model[key] = value;
	}-*/;

	public native static void addObjectAsPropertyToJSArray(
			JavaScriptObject model, String key, JavaScriptObject object) /*-{
		model[key] = object;
	}-*/;

	public native static void addObjectToJSArray(JavaScriptObject model,
			JavaScriptObject object) /*-{
		model.push(object);
	}-*/;

	public native static void log(String message) /*-{
		$wnd.console.log(message);
	}-*/;
	
	
	public native static void alert(String message) /*-{
		alert(message);
	}-*/;
	

	public native static void makeDraggable(Element e) /*-{
		$wnd.$(e).draggable({
			revert : true,
			start : function(event, ui) {
				ui.helper.zIndex(100);
			},
			stop : function(event, ui) {
				ui.helper.zIndex(0);
			}
		});
	}-*/;
	
	
	public native static void makeDropable(Element e) /*-{
		$wnd.$(e).droppable({
			drop : handleCardDrop
		});
		function handleCardDrop(event, ui) {
			$wnd.$(e).click();
		}
	}-*/;
	
	public static Map<String, String> jsonToMap(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();

		JSONValue parsed = JSONParser.parseStrict(jsonStr);
		JSONObject jsonObj = parsed.isObject();
		if (jsonObj != null) {
			for (String key : jsonObj.keySet()) {
				map.put(key, jsonObj.get(key).toString());
			}
		}

		return map;
	}


	public native static String toJsonString(JavaScriptObject eventData) /*-{
		return JSON.stringify(eventData);
	}-*/;
	
}
