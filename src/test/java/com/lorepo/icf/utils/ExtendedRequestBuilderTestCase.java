package com.lorepo.icf.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		ExtendedRequestBuilder.resetWhiteList();
	}
	
	@Test
	public void givenURLNotOnWhiteListAndSetSigningPrefixWhenSignURLExecutedThenDoNotReturnSignedURL() {
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://www.evilsite.com/image.img";
		String expectedURL = url;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}
	
	@Test
	public void givenURLWithFileServePatternNotOnWhiteListAndSetSigningPrefixWhenSignURLExecutedThenDoNotReturnSignedURL() {
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "/file/serve/image.img";
		String expectedURL = url;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}

	@Test
	public void givenURLWithNewServerOnWhiteListAndSetSigningPrefixWhenSignURLExecutedThenReturnSignedURL() {
		ExtendedRequestBuilder.addPageToWhitelist("www.evilsite.com");
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://www.evilsite.com/file/serve/image.img";
		String expectedURL = url + "?" + signingPrefix;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}

	@Test
	public void givenValidURLWithSchameLessMauthorPatternAndSetSigningPrefixWhenSignURLExecutedThenReturnSignedURL() {
		ExtendedRequestBuilder.addPageToWhitelist("www.mauthor.com");
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "//www.mauthor.com/image.img";
		String expectedURL = url + "?" + signingPrefix;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}
	
	@Test
	public void givenValidURLWithMauthorPatternAndSetSigningPrefixWhenSignURLExecutedThenDoNotReturnSignedURL() {
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://www.mauthor.com/image.img";
		String expectedURL = url;

		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}

	@Test
	public void givenValidURLWithMauthorPatternAndMauthorOnWhiteListAndSetSigningPrefixWhenSignURLExecutedThenReturnSignedURL() {
		ExtendedRequestBuilder.addPageToWhitelist("www.mauthor.com");
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://www.mauthor.com/image.img";
		String expectedURL = url + "?" + signingPrefix;

		String result = ExtendedRequestBuilder.signURL(url);

		assertEquals(expectedURL, result);
	}
	
	@Test
	public void givenURLWithParametersAndSetSigningPrefixWhenSignURLExecutedThenReturnSignedURL() {
		ExtendedRequestBuilder.addPageToWhitelist("www.mauthor.com");
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://www.mauthor.com/image.img?SomeRandomParam=123";
		String expectedURL = url + "&" + signingPrefix;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}
	
	@Test
	public void givenSignedURLAndSetSigningPrefixWhenSignURLExecutedThenReturnNotDuplicatedSignedURL() {
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://www.mauthor.com/image.img" + "?" + signingPrefix;
		String expectedURL = url;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}
	
	@Test
	public void givenURLWithURLPrefixAndSetSigningPrefixWhenSignURLExecutedThenDoNotChangeGivenURL() {
		signingPrefix = "URLPrefix=123";
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
		String url = "https://www.mauthor.com/image.img?URLPrefix=432";
		String expectedURL = url;
		
		String result = ExtendedRequestBuilder.signURL(url);
		
		assertEquals(expectedURL, result);
	}
	
	@Test
	public void givenEmptyURLWhenCheckingIfURLMatchesWhitelistThenReturnFalse() {
		String url = "";
		
		boolean result = ExtendedRequestBuilder.isURLMatchesWhitelist(url);
		
		assertFalse(result);
	}
	
	@Test
	public void givenNullWhenCheckingIfURLMatchesWhitelistThenReturnFalse() {
		String url = null;
		
		boolean result = ExtendedRequestBuilder.isURLMatchesWhitelist(url);
		
		assertFalse(result);
	}
	
	@Test
	public void givenURLNotOnWhiteListWhenCheckingIfURLMatchesWhitelistThenReturnFalse() {
		ExtendedRequestBuilder.addPageToWhitelist("www.mauthor.com");
		String url = "http://www.evilsite.com/file/serve/123";
		
		boolean result = ExtendedRequestBuilder.isURLMatchesWhitelist(url);
		
		assertFalse(result);
	}
	
	@Test
	public void givenURLOnWhiteListWhenCheckingIfURLMatchesWhitelistThenReturnTrue() {
		ExtendedRequestBuilder.addPageToWhitelist("www.evilsite.com");
		String url = "http://www.evilsite.com/file/serve/123";
		
		boolean result = ExtendedRequestBuilder.isURLMatchesWhitelist(url);
		
		assertTrue(result);
	}

	@Test
	public void givenURLStartingWithFileServeButNotOnWhiteListWhenCheckingIfURLMatchesWhitelistThenReturnFalse() {
		ExtendedRequestBuilder.addPageToWhitelist("www.mauthor.com");
		String url = "/file/serve/123";
		
		boolean result = ExtendedRequestBuilder.isURLMatchesWhitelist(url);
		
		assertFalse(result);
	}
}
