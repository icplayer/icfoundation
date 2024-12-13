package com.lorepo.icf.utils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class GetFileServeURLsTestCase {
	
	@Test
	public void getEmptyListWhenEmptyCSS() {
		String cssData = "";
		
		List<String> result = URLUtils.getFileServeURLs(cssData);
		
		assertTrue(result instanceof List);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void getEmptyListWhenProvidedNullAssCSS() {
		String cssData = null;
		
		List<String> result = URLUtils.getFileServeURLs(cssData);
		
		assertTrue(result instanceof List);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void getURLFromBackgroundImage() {
		String cssData = String.join("\n"
			, "body {"
			, "    background-image: url('/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
		);
		
		List<String> result = URLUtils.getFileServeURLs(cssData);
		
		assertEquals(1, result.size());
		assertTrue(result.contains("/file/serve/123"));
	}
	
	@Test
	public void getURLFromFontFace() {
		String cssData = String.join("\n"
			, "@font-face {"
			, "    font-family: 'Material Symbols Outlined';"
			, "    font-style: normal;"
			, "    font-style: normal;"
			, "    font-weight: 400;"
			, "    src: url('/file/serve/123') format('woff2');"
			, "}"
		);
		
		List<String> result = URLUtils.getFileServeURLs(cssData);
		
		assertEquals(1, result.size());
		assertTrue(result.contains("/file/serve/123"));
	}
	
	@Test
	public void getURLIfInvalidCSSPropertyButInCSS() {
		String cssData = String.join("\n"
			, "body {"
			, "    background-immage: url('/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
		);
		
		List<String> result = URLUtils.getFileServeURLs(cssData);
		
		assertEquals(1, result.size());
		assertTrue(result.contains("/file/serve/123"));
	}
	
	@Test
	public void doNotGetURLIfNotInURLPrefix() {
		String cssData = String.join("\n"
			, "body {"
			, "    background-image: urll('/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
		);
		
		List<String> result = URLUtils.getFileServeURLs(cssData);
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void getAndParseURLStartedWithHTTP() {
		String cssData = String.join("\n"
			, "body {"
			, "    background-image: url('http://www.mauthor.com/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
		);
		
		List<String> result = URLUtils.getFileServeURLs(cssData);
		
		assertEquals(1, result.size());
		assertTrue(result.contains("/file/serve/123"));
	}
	
	@Test
	public void getAndParseURLStartedWithHTTPs() {
		String cssData = String.join("\n"
			, "body {"
			, "    background-image: url('https://www.mauthor.com/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
		);

		List<String> result = URLUtils.getFileServeURLs(cssData);

		assertEquals(1, result.size());
		assertEquals("/file/serve/123", result.get(0));
		assertTrue(result.contains("/file/serve/123"));
	}

	@Test
	public void getAndParseURLStartedWithoutProtocol() {
		String cssData = String.join("\n"
			, "body {"
			, "    background-image: url('//www.mauthor.com/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
		);

		List<String> result = URLUtils.getFileServeURLs(cssData);

		assertEquals(1, result.size());
		assertTrue(result.contains("/file/serve/123"));
	}

	@Test
	public void getMoreThenOneURL() {
		String cssData = String.join("\n"
			, "body {"
			, "    background-image: url('/file/serve/124')"
			, "    background-color: #ccccc;"
			, "}"
			, ""
			, "head {"
			, "    background-image: url('/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
		);

		List<String> result = URLUtils.getFileServeURLs(cssData);

		assertEquals(2, result.size());
		assertTrue(result.contains("/file/serve/123"));
		assertTrue(result.contains("/file/serve/124"));
	}

	@Test
	public void getURLsWithoutDuplications() {
		String cssData = String.join("\n"
			, "body {"
			, "    background-image: url('//www.mauthor.com/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
			, ""
			, "head {"
			, "    background-image: url('/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
		);

		List<String> result = URLUtils.getFileServeURLs(cssData);

		assertEquals(1, result.size());
		assertTrue(result.contains("/file/serve/123"));
	}

	@Test
	public void getURLsWithoutURLsToExternalAssets() {
		String cssData = String.join("\n"
			, "body {"
			, "    background-image: url('http://www.mauthor.com/file/serve/123')"
			, "    background-color: #ccccc;"
			, "}"
			, ""
			, "head {"
			, "    background-image: url('https://www.evilsite.com/file/serve/124')"
			, "    background-image: url('http://www.evilsite.com/file/serve/125')"
			, "    background-image: url('//www.evilsite.com/file/serve/126')"
			, "    background-color: #ccccc;"
			, "}"
		);

		List<String> result = URLUtils.getFileServeURLs(cssData);

		assertEquals(1, result.size());
		assertTrue(result.contains("/file/serve/123"));
	}
}
