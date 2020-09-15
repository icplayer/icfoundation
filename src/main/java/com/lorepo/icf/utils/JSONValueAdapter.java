package com.lorepo.icf.utils;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;


class JavaJSONValueAdapter {
	private Object value;
	
	public JavaJSONValueAdapter(JSONValue obj) {
		if (obj.isBoolean() != null) {
			this.value = obj.isBoolean().booleanValue();
		} else if (obj.isString() != null) {
			this.value = obj.isString();
		} else if (obj.isNumber() != null) {
			this.value = obj.isNumber();
		} else {		
			this.value = null;
		}
	}
	
	public Object getValue() {
		return this.value;
	}
}

public class JSONValueAdapter {
	private JSONValue value;
	
	public JSONValueAdapter(Boolean value) {
		this.value = JSONBoolean.getInstance(value);
	}
	
	public JSONValueAdapter(Number value) {
		this.value = new JSONNumber(value.doubleValue());
	}
	
	public JSONValueAdapter(String value) {
		this.value = new JSONString(value);
	}
	
	public JSONValue getValue() {
		return this.value;
	}
}