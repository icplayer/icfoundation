package com.lorepo.icf.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

import com.lorepo.icf.utils.NavigationModuleIndentifier;


public class JavaScriptUtils {

	public static JavaScriptObject createHashMap(HashMap<String, String> data){
		
		JavaScriptObject model = JavaScriptObject.createArray();
		
		for(String key : data.keySet()){
			addPropertyToJSArray(model, key, data.get(key));
		}
		
		return model;
	}
	
	public static List<String> convertJsArrayToArrayList (JsArrayString jsArray) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < jsArray.length(); i++) {
			result.add(jsArray.get(i));
		}
		
		return result;
	}
	
	private native static String getParameter (String obj, String parameterName) /*-{
		return obj[parameterName];
	}-*/;
	
	public static List<NavigationModuleIndentifier> convertJsArrayObjectsToJavaObjects (JsArrayString jsArray) {
		List<NavigationModuleIndentifier> result = new ArrayList<NavigationModuleIndentifier>();
		
		if (jsArray == null) {
			return result;
		}
		
		for (int i = 0; i < jsArray.length(); i++) {
			String obj = jsArray.get(i);
			result.add(new NavigationModuleIndentifier(getParameter(obj, "id"), getParameter(obj, "area")));
		}
		
		return result;
	}
	
	public static JsArray<JavaScriptObject> textToSpeechVoicesObjectToJavaScriptArray (List<TextToSpeechVoice> texts) {
		JsArray<JavaScriptObject> result = createEmptyJsArray();
		
		for (TextToSpeechVoice t : texts) {
			addElementToJSArray(result, t.getObject());
		}
		
		return result;
	}
	
	private native static JsArray<JavaScriptObject> createEmptyJsArray() /*-{
		return [];
	}-*/;

	public native static JavaScriptObject createJSObject() /*-{
		return {};
	}-*/;
	
	public native static void addPropertyToJSArray(JavaScriptObject model, String key, String value)  /*-{
		model[key] = value;
	}-*/; 
	
	public native static void addElementToJSArray(JavaScriptObject model, String value) /*-{
		model.push(value);
	}-*/;
	
	public native static void addElementToJSArray(JavaScriptObject model, JavaScriptObject value) /*-{
		model.push(value);
	}-*/;
	
	public native static String getArrayItemByKey(JavaScriptObject model, String key)  /*-{
		if (key in model) {
			return model[key].toString();
		} else {
			return "";
		}
	}-*/;
	
	public native static String getArrayItem(JavaScriptObject arrayObject, int index)  /*-{
		return arrayObject[index];
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
	

	public native static String toJsonString(JavaScriptObject eventData) /*-{
		return JSON.stringify(eventData);
	}-*/;
	
	public native static String stringify(JavaScriptObject obj) /*-{
		return JSON.stringify(obj);
	}-*/;
	

	public native static void log(String message) /*-{
		$wnd.console.log(message);
	}-*/;
	
	public native static void log(Object message) /*-{
		$wnd.console.log(message);
	}-*/;
	
	public native static void alert(String message) /*-{
		alert(message);
	}-*/;
	
	public native static void trace() /*-{
		$wnd.console.trace();
	}-*/;
	
	public native static void debugger() /*-{
		debugger;
	}-*/;
	

	public native static void makeDraggable(Element e, JavaScriptObject jsObject) /*-{
		var $element = $wnd.$(e), elementWidth = $element.width(), elementHeight = $element.height();

		$element.draggable({
			revert : jsObject.shouldRevert,
			helper: jsObject.isRemovable() ? "original" : "clone",
			start : function(event, ui) {
				if(ui.helper.hasClass("ic_sourceListItem")){
					ui.helper.addClass("ic_sourceListItem-selected");
				}

				var width = $wnd.$(this).width(),
					height = $wnd.$(this).height();
					
				if (!jsObject.isDragPossible()) {
					event.stopPropagation();
					event.preventDefault();
					return;
				}
				ui.helper.zIndex(100);
				if (!jsObject.isRemovable()) {
					ui.helper.width(width);
					ui.helper.height(height);
				}
				jsObject.setDragMode();
			},
			stop : function(event, ui) {
				jsObject.unsetDragMode();
				ui.helper.zIndex(0);
				if (!jsObject.isRemovable()) {
					ui.helper.remove();
				}
				$wnd.$.ui.ddmanager.current = null;
			}
		});
	}-*/;

	public native static void destroyDraggable(Element e) /*-{
		$wnd.$(e).draggable("destroy");
	}-*/;
	
	public native static void makeDropable(Element e, JavaScriptObject jsObject) /*-{
		$wnd.$(e).droppable({
			drop : handleCardDrop
		});
		function handleCardDrop(event, ui) {
			jsObject.dropHandler(ui.helper[0]);
			$wnd.$.ui.ddmanager.current = null;
		}
	}-*/;
	
	public native static void makeDroppedDraggable(Element e, JavaScriptObject jsObject) /*-{
		$wnd.$(e).draggable({
			revert : false,
			helper: "clone",
			start : function(event, ui) {
				if (!jsObject.isDragPossible()) {
					event.stopPropagation();
					event.preventDefault();
					return;
				}
				ui.helper.zIndex(100);
				jsObject.itemDragged();
			},
			stop : function(event, ui) {
				ui.helper.zIndex(0);
				ui.helper.remove();
				$wnd.$.ui.ddmanager.current = null;
				$wnd.$(e).draggable( "destroy" );
				jsObject.itemStopped();
			}
		});
	}-*/;
	
	public native static void makeDroppedDraggableText(Element e, JavaScriptObject jsObject, String helperElement) /*-{
		$wnd.$(e).draggable({
			revert : false,
			helper: helper(),
			start : function(event, ui) {
				if (!jsObject.isDragPossible()) {
					event.stopPropagation();
					event.preventDefault();
					return;
				}
				ui.helper.zIndex(100);
				jsObject.itemDragged();
			},
			stop : function(event, ui) {
				ui.helper.zIndex(0);
				ui.helper.remove();
				$wnd.$.ui.ddmanager.current = null;
				$wnd.$(e).draggable( "destroy" );
				jsObject.itemStopped();
			}
		});
		
		function helper() {
	        return function (e, ui) {
	            return helperElement;
	        };
	    };
	}-*/;
	
	public static Map<String, String> jsonToMap(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();

		JSONValue parsed = JSONParser.parseStrict(jsonStr);
		JSONObject jsonObj = parsed.isObject();
		if (jsonObj != null) {
			for (String key : jsonObj.keySet()) {
				if (jsonObj.get(key).isString() != null) {
					map.put(key, jsonObj.get(key).isString().stringValue());
				} else {
					map.put(key, jsonObj.get(key).toString());
				}
			}
		}

		return map;
	}
}
