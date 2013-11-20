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

import com.google.gwt.regexp.shared.RegExp;

public class URLUtils {

  /**
   * Helper function for resolving url
   * 
   * @param name Attribute name
   * @return attribute text or empty string if not found
   */
  public static String resolveURL(String baseUrl, String url){
	  
		if(url.startsWith("http") || url.startsWith("/") || url.isEmpty()) {
			return url;
		} else {
			return baseUrl + url;
		}
  }

  /**
   * Find all relative URLs in CSS and add base URL
   */
  public static String resolveCSSURL(String baseUrl, String css) {
	  // if url isn't starts with 'http' or '/' then add baseUrl
	  RegExp regExp = RegExp.compile("url\\(['\"]?(?!http|/)([^'\"\\)]+)['\"]?\\)", "g");
	  return regExp.replace(css,"url('"+ baseUrl +"$1')");
  }
  
  /**
   * Get server path from url
   * E.g http://mauthor.com/editor will return http://mauthor.com
   */
  public static String getServerPathFromURL(String url){

	  String output = url;
	  if(url.startsWith("http://")){
		  int index = url.indexOf('/', 7);
		  if(index > 0){
			  output = url.substring(0, index);
		  }
	  }
	  return output;
  }

  
}
