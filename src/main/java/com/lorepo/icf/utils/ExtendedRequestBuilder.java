package com.lorepo.icf.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.http.client.RequestBuilder;


public class ExtendedRequestBuilder extends RequestBuilder {
	
	private static boolean withCredentials = false;
	private static String signingPrefix = null;
	private static List<String> whitelist = new ArrayList<String>(); //contain trusted pages
	private static String[] trustedPathsName = {"file/serve", "capacitor", "mauthor"};
	
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
		this.updateWhitelist();
	}
	
	public ExtendedRequestBuilder(java.lang.String httpMethod, java.lang.String url) {
		super(httpMethod, url);
		this.setIncludeCredentials(ExtendedRequestBuilder.withCredentials);
		this.updateWhitelist();
	}

	public static void addPageToWhitelist(String pageURL) {
		whitelist.add(pageURL.toLowerCase());
	}
	
	public static String signURL(String url) {
		if (!shouldSignURL(url) || !isPathURLOnWhitelist(url)) {
			return url;
		}
		String sep = url.contains("?") ? "&" : "?";
		url += sep + ExtendedRequestBuilder.signingPrefix;
		return url;
	}
	
	private static boolean shouldSignURL(String url) {
		return !(
			ExtendedRequestBuilder.signingPrefix == null
			|| url.isEmpty()
			|| url.contains("URLPrefix")
			|| url.contains(ExtendedRequestBuilder.signingPrefix)
		);
	}

	public static boolean isPathURLOnWhitelist(String url) {
		if (whitelist.size() < 1 || url.isEmpty()) {
			return false;
		}

		for(String trustedPathName : whitelist) {
			if (url.contains(trustedPathName)) {
				return true;
			}
		}

		return false;
	}

	protected static void updateWhitelist() {
		for(String pageName : trustedPathsName) {
			whitelist.add(pageName);
		}
	}
}
