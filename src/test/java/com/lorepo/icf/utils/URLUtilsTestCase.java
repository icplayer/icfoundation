package com.lorepo.icf.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class URLUtilsTestCase{

	@Test
	public void resolveEmptyURL() {
		String baseURL = "http://foo.com";
		String destURL = "";

		String resolved = URLUtils.resolveURL(baseURL, destURL);

		assertEquals("", resolved);
	}

	@Test
	public void resolveEmptyURLWithContentBaseURLAsNull() {
		String baseURL = "http://foo.com";
		String contentBaseURL = null;
		String destURL = "";

		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);

		assertEquals("", resolved);
	}

	@Test
	public void resolveEmptyURLWithContentBaseURL() {
		String baseURL = "http://foo.com";
		String contentBaseURL = "http://boo.com";
		String destURL = "";

		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);

		assertEquals("", resolved);
	}

	@Test
	public void resolveSimpleURL() {
		String baseURL = "http://foo.com";
		String destURL = "/file/serve/123";

		String resolved = URLUtils.resolveURL(baseURL, destURL);

		assertEquals("/file/serve/123", resolved);
	}

	@Test
	public void resolveSimpleURLWithContentBaseURLAsNull() {
		String baseURL = "http://foo.com";
		String contentBaseURL = null;
		String destURL = "/file/serve/123";

		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);

		assertEquals("/file/serve/123", resolved);
	}

	@Test
	public void resolveSimpleURLWithContentBaseURL() {
		String baseURL = "http://foo.com";
		String contentBaseURL = "http://boo.com";
		String destURL = "/file/serve/123";

		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);

		assertEquals("http://boo.com/file/serve/123", resolved);
	}

	@Test
	public void resolveURLWithHTTP() {
		String baseURL = "http://foo.com";
		String destURL = "http://ooo.com/file/serve/123";
		
		String resolved = URLUtils.resolveURL(baseURL, destURL);
		
		assertEquals("http://ooo.com/file/serve/123", resolved);
	}

	@Test
	public void resolveURLWithHTTPAndContentBaseURLAsNull() {
		String baseURL = "http://foo.com";
		String contentBaseURL = null;
		String destURL = "http://ooo.com/file/serve/123";

		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);

		assertEquals("http://ooo.com/file/serve/123", resolved);
	}

	@Test
	public void resolveURLWithHTTPAndContentBaseURL() {
		String baseURL = "http://foo.com";
		String contentBaseURL = "http://boo.com";
		String destURL = "http://ooo.com/file/serve/123";

		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);

		assertEquals("http://ooo.com/file/serve/123", resolved);
	}

	@Test
	public void resolveURLWithoutStartingSlash() {
		String baseURL = "http://foo.com";
		String destURL = "file/serve/123";
		
		String resolved = URLUtils.resolveURL(baseURL, destURL);
		
		assertEquals("http://foo.comfile/serve/123", resolved);
	}

	@Test
	public void resolveURLWithoutStartingSlashAndWithContentBaseURLAsNull() {
		String baseURL = "http://foo.com";
		String contentBaseURL = null;
		String destURL = "file/serve/123";

		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);

		assertEquals("http://foo.comfile/serve/123", resolved);
	}

	@Test
	public void resolveURLWithoutStartingSlashAndWithContentBaseURL() {
		String baseURL = "http://foo.com";
		String contentBaseURL = "http://boo.com";
		String destURL = "file/serve/123";

		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);

		assertEquals("http://boo.comfile/serve/123", resolved);
	}

	@Test
	public void resolveURLWithPathWithoutProtocol() {
		String baseURL = "http://foo.com/";
		String destURL = "//ooo.com/file/serve/123";

		String resolved = URLUtils.resolveURL(baseURL, destURL);

		assertEquals("//ooo.com/file/serve/123", resolved);
	}

	@Test
	public void resolveURLWithPathWithoutProtocolAndWithContentBaseURLAsNull() {
		String baseURL = "http://foo.com/";
		String contentBaseURL = null;
		String destURL = "//ooo.com/file/serve/123";

		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);

		assertEquals("//ooo.com/file/serve/123", resolved);
	}

	@Test
	public void resolveURLWithPathWithoutProtocolAndWithContentBaseURL() {
		String baseURL = "http://foo.com/";
		String contentBaseURL = "Lorem";
		String destURL = "//ooo.com/file/serve/123";
		
		String resolved = URLUtils.resolveURL(baseURL, destURL, contentBaseURL);
		
		assertEquals("https://ooo.com/file/serve/123", resolved);
	}

	@Test
	public void resolveCSSWithURLWithoutStartingSlash() {
		String baseURL = "http://foo.com";
		String css = "background-image:url('resources/522258');";
		
		String resolved = URLUtils.resolveCSSURL(baseURL, css);
		
		assertEquals("background-image:url('http://foo.comresources/522258');", resolved);
	}

	@Test
	public void resolveCSSWithURLWithoutStartingSlashAndWithContentBaseURLAsNull() {
		String baseURL = "http://foo.com";
		String contentBaseURL = null;
		String css = "background-image:url('resources/522258');";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		assertEquals("background-image:url('http://foo.comresources/522258');", resolved);
	}

	@Test
	public void resolveCSSWithURLWithoutStartingSlashAndWithContentBaseURL() {
		String baseURL = "http://foo.com";
		String contentBaseURL = "http://boo.com";
		String css = "background-image:url('resources/522258');";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		assertEquals("background-image:url('http://boo.comresources/522258');", resolved);
	}

	@Test
	public void resolveComplexCSS() {
		String baseURL = "http://foo.com/";
		String css = "background-image:url('aaa/32');image:url('resources/522258');";
		
		String resolved = URLUtils.resolveCSSURL(baseURL, css);

		int index = resolved.indexOf("http://foo.com/res");
		assertTrue(index > 0);
		assertEquals("background-image:url('http://foo.com/aaa/32');image:url('http://foo.com/resources/522258');", resolved);
	}

	@Test
	public void resolveComplexCSSWithContentBaseURLAsNull() {
		String baseURL = "http://foo.com/";
		String contentBaseURL = null;
		String css = "background-image:url('aaa/32');image:url('resources/522258');";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		int index = resolved.indexOf("http://foo.com/res");
		assertTrue(index > 0);
		assertEquals("background-image:url('http://foo.com/aaa/32');image:url('http://foo.com/resources/522258');", resolved);
	}

	@Test
	public void resolveComplexCSSWithContentBaseURL() {
		String baseURL = "http://foo.com/";
		String contentBaseURL = "http://boo.com/";
		String css = "background-image:url('aaa/32');image:url('resources/522258');";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		int index = resolved.indexOf("http://boo.com/res");
		assertTrue(index > 0);
		assertEquals("background-image:url('http://boo.com/aaa/32');image:url('http://boo.com/resources/522258');", resolved);
	}

	@Test
	public void resolveCSSWithURLs() {
		String baseURL = "http://foo.com/";
		String css = "background-image:url('/resources/522258');image:url('http://resources/522258');image:url('//resources/522258');";
		
		String resolved = URLUtils.resolveCSSURL(baseURL, css);

		int index = resolved.indexOf("http://foo.com/");
		assertEquals(-1, index);
		assertEquals(css, resolved);
	}

	@Test
	public void resolveCSSWithURLsAndContentBaseURLAsNull() {
		String baseURL = "http://foo.com";
		String contentBaseURL = null;
		String css = "background-image:url('/resources/522258');image:url('http://resources/522258');image:url('//resources/522258');";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		int index = resolved.indexOf("http://foo.com");
		assertEquals(-1, index);
		assertEquals(css, resolved);
	}

	@Test
	public void resolveCSSWithURLsAndContentBaseURL() {
		String baseURL = "http://foo.com";
		String contentBaseURL = "http://boo.com";
		String css = "background-image:url('/resources/522258');image:url('http://resources/522258');image:url('//resources/522258');";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		assertEquals("background-image:url('http://boo.com/resources/522258');image:url('http://resources/522258');image:url('https://resources/522258');", resolved);
	}

	@Test
	public void resolveCSSwithQuotes() {
		String base = "/ala/";
		String css = "background-image:url(\"resources/522258\");";

		String resolved = URLUtils.resolveCSSURL(base, css);

		assertEquals("background-image:url('/ala/resources/522258');", resolved);
	}

	@Test
	public void resolveCSSwithQuotesWithContentBaseURLAsNull() {
		String base = "/ala/";
		String contentBaseURL = null;
		String css = "background-image:url(\"resources/522258\");";

		String resolved = URLUtils.resolveCSSURL(base, css, contentBaseURL);

		assertEquals("background-image:url('/ala/resources/522258');", resolved);
	}

	@Test
	public void resolveCSSwithQuotesWithContentBaseURL() {
		String base = "/ala/";
		String contentBaseURL = "http://boo.com";
		String css = "background-image:url(\"resources/522258\");";

		String resolved = URLUtils.resolveCSSURL(base, css, contentBaseURL);

		assertEquals("background-image:url('http://boo.comresources/522258');", resolved);
	}
	
	@Test
	public void resolveEmptyCSS() {
		String baseURL = "/ala/";
		String css = "";

		String resolved = URLUtils.resolveCSSURL(baseURL, css);

		assertEquals("", resolved);
	}

	@Test
	public void resolveEmptyCSSWithContentBaseURLAsNull() {
		String baseURL = "/ala/";
		String contentBaseURL = null;
		String css = "";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		assertEquals("", resolved);
	}

	@Test
	public void resolveEmptyCSSWithContentBaseURL() {
		String baseURL = "/ala/";
		String contentBaseURL = "http://boo.com";
		String css = "";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		assertEquals("", resolved);
	}

	@Test
	public void resolveDataUrl() {
		String baseURL = "/ala/";
		String css = "background-image:url('data:image/png;base64')";

		String resolved = URLUtils.resolveCSSURL(baseURL, css);

		assertEquals(css, resolved);
	}

	@Test
	public void resolveDataUrlWithContentBaseURLAsNull() {
		String baseURL = "/ala/";
		String contentBaseURL = null;
		String css = "background-image:url('data:image/png;base64')";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		assertEquals(css, resolved);
	}

	@Test
	public void resolveDataUrlWithContentBaseURL() {
		String baseURL = "/ala/";
		String contentBaseURL = "http://boo.com";
		String css = "background-image:url('data:image/png;base64')";

		String resolved = URLUtils.resolveCSSURL(baseURL, css, contentBaseURL);

		assertEquals(css, resolved);
	}

	@Test
	public void serverPath() {
		String url = "http://foo.com/aaa";
		String resolved = URLUtils.getServerPathFromURL(url);
		
		assertEquals("http://foo.com", resolved);
	}

	@Test
	public void serverPath2() {
		String url = "foo.com/aaa";
		String resolved = URLUtils.getServerPathFromURL(url);
		
		assertEquals("foo.com/aaa", resolved);
	}

	@Test
	public void validUrl1() {
		String url = "foo.com/aaa";
		
		assertTrue(URLUtils.isValidUrl(url));
	}

	@Test
	public void invalidUrl1() {
		String url = "foo.com/aaa bbb";
		
		assertFalse(URLUtils.isValidUrl(url));
	}
}
