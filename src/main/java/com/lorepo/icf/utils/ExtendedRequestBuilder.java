package com.lorepo.icf.utils;

import com.google.gwt.http.client.RequestBuilder;


public class ExtendedRequestBuilder extends RequestBuilder {
	
	private static boolean withCredentials = false;
	private static String signingPrefix = null;
	private static String resourcesDomain = null;
	
	public static void setGlobalIncludeCredentials(boolean withCredentials) {
		ExtendedRequestBuilder.withCredentials = withCredentials;
	}
	
	public static boolean shouldIncludeCredentials() {
		return ExtendedRequestBuilder.withCredentials;
	}
	
	public static void setSigningPrefix(String signingPrefix) {
		ExtendedRequestBuilder.signingPrefix = signingPrefix;
	}
	
	public static String getSigningPrefix() {
		return ExtendedRequestBuilder.signingPrefix;
	}
	
	public ExtendedRequestBuilder(ExtendedRequestBuilder.Method httpMethod, java.lang.String url) {
		super(httpMethod, url);
		this.setIncludeCredentials(ExtendedRequestBuilder.withCredentials);
	}
	
	public ExtendedRequestBuilder(java.lang.String httpMethod, java.lang.String url) {
		super(httpMethod, url);
		this.setIncludeCredentials(ExtendedRequestBuilder.withCredentials);
	}
	
	public static String signURL(String url) {
		if (!shouldSignURL(url)) {
			return url;
		}
		String sep = url.contains("?") ? "&" : "?";
		url += sep + ExtendedRequestBuilder.signingPrefix;
		return url;
	}
	
	private static boolean shouldSignURL(String url) {
		if (ExtendedRequestBuilder.signingPrefix == null
			|| url.isEmpty()
			|| url.contains(ExtendedRequestBuilder.signingPrefix)
		) {
			return false;
		}
		
		if (resourcesDomain == null || (!url.startsWith("http") && !url.startsWith("\\"))) {
			return true;
		}
		return url.contains(resourcesDomain);
	}
}
