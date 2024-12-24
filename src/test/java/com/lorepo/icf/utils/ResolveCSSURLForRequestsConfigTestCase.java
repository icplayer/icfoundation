package com.lorepo.icf.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lorepo.icf.utils.ExtendedRequestBuilder;

public class ResolveCSSURLForRequestsConfigTestCase{

    String signingPrefix = "test123";

    @Before
	public void setUp() {
		ExtendedRequestBuilder.setSigningPrefix(signingPrefix);
	}

	@After
	public void tearDown () {
		ExtendedRequestBuilder.resetWhiteList();
		ExtendedRequestBuilder.setSigningPrefix(null);
	}
	
	@Test
	public void givenCSSWithFontFaceAndSigningPrefixWhenResolveCSSURLExecutedThenReturnCSSWithUpdatedURLsForFontFace() {
		String cssData = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('/file/serve/font.woff') format('woff2');"
			, "}"
		);
		
		ExtendedRequestBuilder.addPageToWhitelist("/file/serve/");
		String resolved = URLUtils.resolveCSSURL(null, cssData);
		
		String expectedCSS = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('/file/serve/font.woff?test123') format('woff2');"
			, "}"
		);
		assertEquals(expectedCSS, resolved);
	}
	
	@Test
	public void givenCSSWithFontFaceAndSigningPrefixAndUntrustPathWhenResolveCSSURLExecutedThenReturnCSSWithNotUpdatedURLsForFontFace() {
		String cssData = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('https://www.mauthor.com/aaa/font') format('woff2');"
			, "}"
		);

		String resolved = URLUtils.resolveCSSURL(null, cssData);
		
		String expectedCSS = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('https://www.mauthor.com/aaa/font') format('woff2');"
			, "}"
		);
		assertEquals(expectedCSS, resolved);
	}
	
	@Test
	public void givenCSSWithFontFaceAndSigningPrefixAndNewTrustedPathWhenResolveCSSURLExecutedThenReturnCSSWithUpdatedURLsForFontFace() {
		String cssData = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('https://www.mauthor.com/aaa/font') format('woff2');"
			, "}"
		);
		
		ExtendedRequestBuilder.addPageToWhitelist("https://www.mauthor.com");
		String resolved = URLUtils.resolveCSSURL(null, cssData);
		
		String expectedCSS = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('https://www.mauthor.com/aaa/font?test123') format('woff2');"
			, "}"
		);
		assertEquals(expectedCSS, resolved);
	}
	
	@Test
	public void givenCSSWithFontFaceAndMultipleURLsAndSigningPrefixWhenResolveCSSURLExecutedThenReturnCSSWithUpdatedURLsForFontFace() {
		String cssData = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: local('AAA'),"
			, "         url('/file/serve/aaa/font2.woff'),"
			, "         url('/file/serve/aaa/font.woff') format('woff2'),"
			, "         url('/file/serve/aaa/font.ttf') format('ttf');"
			, "}"
		);
		
		ExtendedRequestBuilder.addPageToWhitelist("/file/serve/");
		String resolved = URLUtils.resolveCSSURL(null, cssData);
		
		String expectedCSS = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: local('AAA'),"
			, "         url('/file/serve/aaa/font2.woff?test123'),"
			, "         url('/file/serve/aaa/font.woff?test123') format('woff2'),"
			, "         url('/file/serve/aaa/font.ttf?test123') format('ttf');"
			, "}"
		);
		assertEquals(expectedCSS, resolved);
	}
	
	@Test
	public void givenCSSWithFontFaceAndSigningPrefixWhenResolveCSSURLExecutedThenReturnCSSWithUpdatedOnlyURLsForFontFace() {
		String cssData = String.join("\n"
			, ".aaa {"
			, "    background: url('https://mauthor/aaa/image');"
			, "}"
			, ""
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('https://www.mauthor.com/aaa/font') format('woff2');"
			, "}"
			, ""
			, ".bbb {"
			, "    background: url('https://www.mauthor.com/aaa/image2');"
			, "}"
		);
		
		ExtendedRequestBuilder.addPageToWhitelist("https://www.mauthor.com");
		String resolved = URLUtils.resolveCSSURL(null, cssData);
		
		String expectedCSS = String.join("\n"
			, ".aaa {"
			, "    background: url('https://mauthor/aaa/image');"
			, "}"
			, ""
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('https://www.mauthor.com/aaa/font?test123') format('woff2');"
			, "}"
			, ""
			, ".bbb {"
			, "    background: url('https://www.mauthor.com/aaa/image2');"
			, "}"
		);
		assertEquals(expectedCSS, resolved);
	}
	
	@Test
	public void givenCSSWithFontFaceAndWithoutSigningPrefixWhenResolveCSSURLExecutedThenReturnCSSWithoutUpdatedURLs() {
		ExtendedRequestBuilder.setSigningPrefix(null);
		
		String cssData = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('https://www.mauthor.com/aaa/font') format('woff2');"
			, "}"
		);
		
		ExtendedRequestBuilder.addPageToWhitelist("https://www.mauthor.com");
		String resolved = URLUtils.resolveCSSURL(null, cssData);
		
		String expectedCSS = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('https://www.mauthor.com/aaa/font') format('woff2');"
			, "}"
		);
		assertEquals(expectedCSS, resolved);
	}
}
