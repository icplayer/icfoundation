package com.lorepo.icf.utils;

import java.util.HashMap;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;


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
	
	
	public native static String getArrayItemByKey(JavaScriptObject model, String key)  /*-{
		
		if(key in model){
			return model[key].toString();
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
	
	
	public native static void makeDraggable(Element e) /*-{
		var isMobile = navigator.userAgent.match(/(iPhone|iPod|iPad|Android|BlackBerry)/);
		$wnd.$(e).draggable({ revert: true
			 				, start: function(event, ui) { 
			 					ui.helper.zIndex(100);
			 					$wnd.$(e).click(); 
			 				  }
							, stop: function(event, ui) { 
								ui.helper.zIndex(0);
								if(!isMobile){
									$wnd.$(e).click();
								} 
							  }
							});
	}-*/;
	
	
	public native static void makeDropable(Element e) /*-{
		$wnd.$(e).droppable( { drop: handleCardDrop } );
		function handleCardDrop( event, ui ){
			$wnd.$(e).click();
		}    						 
	}-*/;
	
}
