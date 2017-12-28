package com.lorepo.icf.utils.dom;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.StyleInjector;


/**
 * Klasa odpowiedzialna za dokładanie kodu do DOM-u dokumentu
 * 
 * @author Krzysztof Langner
 *
 */
public class DOMInjector {

	
	public native static void includeJavaScript(String url) /*-{
	  
		$wnd.jQuery.getScript(url, function(data, textStatus, jqxhr) {
		});
	}-*/; 

	
	public native static void injectJavaScript(String code) /*-{
	  var elem = $wnd.document.createElement("script");
	  elem.setAttribute("language", "JavaScript");
	  var txt = document.createTextNode(code);
	  elem.appendChild(txt);
	  $wnd.document.getElementsByTagName("head")[0].appendChild(elem);
	}-*/; 

	
	public native static JavaScriptObject appendStyle(String style) /*-{
	
		if(style == null){
			return;
		}
		
		var styleElement = $wnd.document.getElementById("_ice_style");
		
		if(styleElement == null){     
			var headID = $wnd.document.getElementsByTagName("head")[0];         
			styleElement = $wnd.document.createElement('style');
			styleElement.setAttribute("id", "_ice_style");
			headID.appendChild(styleElement);
		}
	
		styleElement.innerHTML = style.replace(/\s/g, " ");
		
		return styleElement;
		
	}-*/;


	public static void injectStyleAtStart(String css){
		StyleInjector.injectAtStart(css);
	}
}
