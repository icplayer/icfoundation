package com.lorepo.icf.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExtendedRequestBuilderTestCase{
	
	String signingPrefix;
	
	@Before
	public void setUp() {
		signingPrefix = "test123";
	}
	
	@After
	public void tearDown () {
		ExtendedRequestBuilder.setSigningPrefix(null);
		ExtendedRequestBuilder.setGlobalIncludeCredentials(false);
	}
	
	@Test
	public void givenURLAndSetSigningPrefixWhenSignURLExecutedThenReturnSignedURL() {
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://test/image.img";
		String expectedURL = url + "?" + signingPrefix;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}
	
	@Test
	public void givenURLWithParametersAndSetSigningPrefixWhenSignURLExecutedThenReturnSignedURL() {
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://test/image.img?SomeRandomParam=123";
		String expectedURL = url + "&" + signingPrefix;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}
	
	@Test
	public void givenSignedURLAndSetSigningPrefixWhenSignURLExecutedThenReturnSignedURL() {
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://test/image.img" + "?" + signingPrefix;
		String expectedURL = url;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}
	
	@Test
	public void givenURLWithURLPrefixAndSetSigningPrefixWhenSignURLExecutedThenDoNotChangeGivenURL() {
		signingPrefix = "URLPrefix=123";
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://test/image.img?URLPrefix=432";
		String expectedURL = url;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}
}
