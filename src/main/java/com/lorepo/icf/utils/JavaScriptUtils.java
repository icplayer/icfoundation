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
		var getContentScale = $entry(function() {
			return @com.lorepo.icf.utils.JavaScriptUtils::getContentScale()();
		});
		var scale = {X:1.0, Y:1.0};
		
		var $element = $wnd.$(e), elementWidth = $element.width(), elementHeight = $element.height();
		var helperOriginalPosition = {top:0.0, left:0.0};
		var firstIteration = true;

        // helper must be "clone" - "original" causes issues with position on iOS
		$element.draggable({
			revert : jsObject.shouldRevert,
			helper: "clone",
			start : function(event, ui) {
				scale = getContentScale();
				helperOriginalPosition.left = ui.originalPosition.left/scale.X;
				helperOriginalPosition.top = ui.originalPosition.top/scale.Y;
				firstIteration = true;
				ui.position.left = 0;
				ui.position.top = 0;
				
				if (!jsObject.isDragPossible()) {
					event.stopPropagation();
					event.preventDefault();
					return;
				}

				if (jsObject.isRemovable()) {
					$element.css('visibility', 'hidden');
				}

				if (ui.helper.hasClass("ic_sourceListItem")) {
					ui.helper.addClass("ic_sourceListItem-selected");
				}

				var width = $wnd.$(this).width(),
					height = $wnd.$(this).height();
					
				ui.helper.zIndex(100);
				if (!jsObject.isRemovable()) {
					ui.helper.width(width);
					ui.helper.height(height);
				}
				jsObject.setDragMode();
			},
			drag : function(event, ui) {
				if(firstIteration){
					ui.originalPosition.left = helperOriginalPosition.left;
		        	ui.originalPosition.top = helperOriginalPosition.top;
		        	firstIteration = false;
				}
		        ui.position.left = ui.position.left/scale.X;
		        ui.position.top = ui.position.top/scale.Y;
			},
			stop : function(event, ui) {
				
				if (jsObject.isRemovable()) {
					$element.css('visibility', 'visible');
				}
				
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
			drop : handleCardDrop,
			accept: acceptCardDrop
		});
		function handleCardDrop(event, ui) {
			jsObject.dropHandler(ui.helper[0]);
			$wnd.$.ui.ddmanager.current = null;
		}
		
		// return true if center of helper is over the droppable element
		// as per jquery-ui 'intersect' drop tolerance
		function acceptCardDrop(ui) {
			var $helper = $wnd.$('.ui-draggable-dragging');
			if($helper.size()>0){
				ui = $wnd.$($helper[0]);
			};
			
			if ( !ui.offset ) {
				return false;
			}

			var x = ui.offset().left + ui[0].getBoundingClientRect().width/2,
				y = ui.offset().top + ui[0].getBoundingClientRect().height/2,
				l = $wnd.$(this).offset().left,
				t = $wnd.$(this).offset().top,
				r = l + this.getBoundingClientRect().width,
				b = t + this.getBoundingClientRect().height;
			return (l < x && x < r && t < y && y < b);
		};
		
	}-*/;
	
	public native static void makeDroppedDraggable(Element e, JavaScriptObject jsObject) /*-{
		var getContentScale = $entry(function() {
			return @com.lorepo.icf.utils.JavaScriptUtils::getContentScale()();
		});
		var scale = {X:1.0, Y:1.0};
		
		$wnd.$(e).draggable({
			revert : false,
			helper: "clone",
			start : function(event, ui) {
				scale = getContentScale();
				ui.position.left = 0;
				ui.position.top = 0;
				if (!jsObject.isDragPossible()) {
					event.stopPropagation();
					event.preventDefault();
					return;
				}
				ui.helper.zIndex(100);
				jsObject.itemDragged();
			},
			drag : function(event, ui) {
				ui.position.left = ui.position.left/scale.X;
		        ui.position.top = ui.position.top/scale.Y;
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
		var getContentScale = $entry(function() {
			return @com.lorepo.icf.utils.JavaScriptUtils::getContentScale()();
		});
		scale = {X:1.0, Y:1.0};
		
		$wnd.$(e).draggable({
			revert : false,
			helper: helper(),
			start : function(event, ui) {
				scale = getContentScale();
				ui.position.left = 0;
				ui.position.top = 0;
				if (!jsObject.isDragPossible()) {
					event.stopPropagation();
					event.preventDefault();
					return;
				}
				ui.helper.zIndex(100);
				jsObject.itemDragged();
			},
			drag : function(event, ui) {
				ui.position.left = ui.position.left/scale.X;
		        ui.position.top = ui.position.top/scale.Y;
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

	private native static JsArray<JavaScriptObject> getContentScale() /*-{
		var $content = $wnd.$("#content"); // the div transform css is attached to
		if($content.size()>0){
            var contentElem = $content[0];
            var scaleX = contentElem.getBoundingClientRect().width / contentElem.offsetWidth;
            var scaleY = contentElem.getBoundingClientRect().height / contentElem.offsetHeight;
            return {X:scaleX, Y:scaleY};
		};
		return {X:1.0, Y:1.0};
	}-*/;
}
