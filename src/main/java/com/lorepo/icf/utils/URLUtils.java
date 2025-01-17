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

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import com.lorepo.icf.utils.ExtendedRequestBuilder;

public class URLUtils {

  /**
   * Helper function for resolving url
   * 
   * @param name Attribute name
   * @return attribute text or empty string if not found
   */
  public static String resolveURL(String baseUrl, String url){
    return resolveURL(baseUrl, url, false);
  }

  public static String resolveURL(String baseUrl, String url, String contentBaseURL) {
    if (contentBaseURL != null) {
      return resolveURL(contentBaseURL, url, true);
    }
    return resolveURL(baseUrl, url, false);
  }

  public static String resolveURL(String baseUrl, String url, boolean useContentBaseURL){
    if (url.startsWith("http") || url.isEmpty() || (!useContentBaseURL && url.startsWith("/"))){
      return url;
    }
    if (useContentBaseURL && url.startsWith("//")) {
      return "https:" + url;
    }
    return baseUrl + url;
  }

  private static RegExp cssRegexp = RegExp.compile("url\\(['\"]?(?!http|data:|/)([^'\"\\)]+)['\"]?\\)", "g");
  private static RegExp cssRegexpWithoutProtocol = RegExp.compile("url\\(['\"]?(?=//)([^'\"\\)]+)['\"]?\\)", "g");
  private static RegExp cssRegexpForContentBaseURL = RegExp.compile("url\\(['\"]?(?!http|data:)([^'\"\\)]+)['\"]?\\)", "g");
  public static RegExp cssRegexpForURL = RegExp.compile("url\\(['\"]?([^'\"\\)]+)['\"]?\\)", "g");
  private static RegExp cssRegexpFontFace = RegExp.compile("\\@font-face\\s*\\{([\\s\\S]*?)\\}", "g");

  /**
   * Find all relative URLs in CSS and add base URL
   */
  public static String resolveCSSURL(String baseUrl, String css) {
    return resolveCSSURL(baseUrl, css, false);
  }

  public static String resolveCSSURL(String baseUrl, String css, String contentBaseURL) {
    if (contentBaseURL != null) {
      return resolveCSSURL(contentBaseURL, css, true);
    }
    return resolveCSSURL(baseUrl, css, false);
  }

  private static String resolveCSSURL(String baseUrl, String css, boolean useContentBaseURL) {
    // if url isn't starts with 'http' or '/' then add baseUrl
    if (css == null) {
      return null;
    }
    
    RegExp regExp;
    if (useContentBaseURL) {
      regExp = URLUtils.cssRegexpForContentBaseURL;
      RegExp preprocessRegExp = URLUtils.cssRegexpWithoutProtocol;
      css = preprocessRegExp.replace(css, "url('https:$1')");
    } else {
      regExp = URLUtils.cssRegexp;
    }
    css = regExp.replace(css, "url('"+ baseUrl +"$1')");
    if (ExtendedRequestBuilder.getSigningPrefix() != null) {
      RegExp regExpFontFace = URLUtils.cssRegexpFontFace;
      for (MatchResult fontFaceMatcher = regExpFontFace.exec(css); fontFaceMatcher != null; fontFaceMatcher = regExpFontFace.exec(css)) {
        String fontFace = fontFaceMatcher.getGroup(1);
        RegExp regExpURL = URLUtils.cssRegexpForURL;
        for (MatchResult urlMatcher = regExpURL.exec(fontFace); urlMatcher != null; urlMatcher = regExpURL.exec(fontFace)) {
          css = css.replace(urlMatcher.getGroup(0), "url('"+ ExtendedRequestBuilder.signURL(urlMatcher.getGroup(1)) + "')");
        }
      }
    }
    
    return css;
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

  /**
   * Check if given url is correctly formatted
   * @param url
   * @return
   */
  public static boolean isValidUrl(String url) {
	  if(url.contains(" ")){
		  return false;
	  }
	  return true;
  }
}
