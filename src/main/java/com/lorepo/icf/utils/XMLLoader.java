/*
  The MIT License
  
  Copyright (c) 2009 Krzysztof Langner
  
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  
  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.
  
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
*/
package com.lorepo.icf.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

/**
 * Obsługa wczytywania danych contentu z pliku *.ic.xml
 * 
 * @author Krzysztof Langner
 *
 */
public class XMLLoader {

	private IXMLSerializable model;
	/** Potrzebny by dostac się w inner function */
	private ILoadListener listener;
	private String	errorString;

	public XMLLoader(IXMLSerializable model){
		this.model = model;
	}
	
	public void load(String url, ILoadListener l){
		this.listener = l;
		errorString = null;
		try {
			this.sendRequest(url);
		} catch (RequestException e) {
		  // Couldn't connect to server
			errorString = "Can't connect to the server: " + e.toString();
		} catch (DOMException e) {
			errorString = "Could not parse file: " + url;
		} catch(Exception e) {
			errorString = e.getMessage();
			listener.onError(errorString);
		}

	  if(errorString != null) {
		  listener.onError(errorString);
	  }
	}

	private void sendRequest(String url) throws RequestException {
		final String resolvedURL = this.getResolvedURL(url);
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, resolvedURL);
		builder.sendRequest(null, new RequestCallback() {
			public void onError(Request request, Throwable exception) {
				// Couldn't connect to server (could be timeout, SOP violation, etc.)
				errorString = "Error" + exception.toString();
			}

			public void onResponseReceived(Request request, Response response){
				responseHandler(response, resolvedURL);
			}
		});
	}

	private String getResolvedURL(String url) {
		String resolvedURL;

		if( url.contains("://") || url.startsWith("/") ){
			resolvedURL = url;
		}
		else{
			resolvedURL = GWT.getHostPageBaseURL() + url;
		}

		return resolvedURL;
	}

	private void responseHandler(Response response, String resolvedURL) {
		// StatusCode == 0 when loading from local file
		if (response.getStatusCode() == 200 || response.getStatusCode() == 0) {
			successCallback(response.getText(), resolvedURL);
		} else {
			// Handle the error.  Can get the status text from response.getStatusText()
			if(response.getStatusCode() != 404) {
				listener.onError("Wrong status: " + response.getText());
			} else {
				listener.onError("Not found (404)");
			}
		}
	}

	private void successCallback(String xmlString, String resolvedURL) {
		Document dom = XMLParser.parse(xmlString);
		initContentFromDOM(dom, resolvedURL);
		listener.onFinishedLoading(model);
	}

	
	/**
	 * Init object from DOM. return initialized object
	 * @param dom
	 * @param document
	 */
	private void initContentFromDOM(Document dom, String url){
		model.load(dom.getDocumentElement(), url);
	}

} 