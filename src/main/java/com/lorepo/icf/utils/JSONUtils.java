package com.lorepo.icf.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JSONUtils {
	
	public static String toJSONString(HashMap<String, String> data){
		
		JSONObject json = new JSONObject();
		
		for(String key : data.keySet()){
			String value = data.get(key);
			if(value != null){
				JSONString jsonValue = new JSONString(value);
				json.put(key, jsonValue);
			}
		}
		
		return json.toString();
	}

	public static String toJSONString(ArrayList<Boolean> list) {

		JSONArray json = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			JSONBoolean jsonValue = JSONBoolean.getInstance(list.get(i).booleanValue());
			json.set(i, jsonValue); 
		}
		
		return json.toString();
	}
	
	
	// more generic version of array to jsonString - wrapper accepts string, number and boolean args 
	public static String toJSONString(List<JSONValueAdapter> list) {

		JSONArray json = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			JSONValue jsonValue = list.get(i).getValue();
			json.set(i, jsonValue); 
		}
		
		return json.toString();
	}
	
	public static ArrayList<Integer> decodeIntegerArray(String jsonText) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		JSONValue jsonValue = JSONParser.parseStrict(jsonText);
		JSONArray array = jsonValue.isArray();
		
		if (array == null) {
			return list;
		}
		
		for (int i = 0; i < array.size(); i++) {
			JSONNumber number = array.get(i).isNumber();
			
			if (number != null) {
				Integer value = new Double(number.doubleValue()).intValue();
				list.add(value);
			}
		}
		
		return list;
	}

	public static ArrayList<Boolean> decodeArray(String jsonText) {

		ArrayList<Boolean> list = new ArrayList<Boolean>();
		JSONValue jsonValue = JSONParser.parseStrict(jsonText);
		
		if(jsonValue instanceof JSONArray){
			JSONArray json = (JSONArray) jsonValue;
			for(int i = 0; i < json.size(); i++){
				
				if(json.get(i).isBoolean() != null){
					boolean value = json.get(i).isBoolean().booleanValue();
					list.add(new Boolean(value));
				}
			}
		}
		
		return list;
	}
	
	public static HashMap<String,String> decodeHashMap(String jsonText){
		
		HashMap<String,String> output = new HashMap<String, String>();
		JSONValue jsonValue = JSONParser.parseStrict(jsonText);
		
		if (jsonValue instanceof JSONObject) {
			JSONObject json = (JSONObject) jsonValue;
			for (String key : json.keySet()) {
				if (json.get(key).isString() != null) {
					String value = json.get(key).isString().stringValue();
					output.put(key, value);
				}
			}
		}
		
		return output;
	}
	
	public static native <T> T parseOverlayType(JavaScriptObject obj, Class<T> type) /*-{
		return obj || {};
	}-*/;
}