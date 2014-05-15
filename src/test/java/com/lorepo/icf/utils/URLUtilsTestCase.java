package com.lorepo.icf.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class URLUtilsTestCase{

	@Test
	public void resolveURLWithHTTP() {
		
		String base = "/ala/";
		String destURL = "http://foo.com";
		
		String resolved = URLUtils.resolveURL(base, destURL);
		
		assertEquals("http://foo.com", resolved);
	}

	@Test
	public void resolveURLWithRelativePath() {
		
		String base = "/ala/";
		String destURL = "foo.com";
		
		String resolved = URLUtils.resolveURL(base, destURL);
		
		assertEquals("/ala/foo.com", resolved);
	}

	@Test
	public void resolveURLWithAbsolutePath() {
		
		String base = "/ala/";
		String destURL = "/foo.com";
		
		String resolved = URLUtils.resolveURL(base, destURL);
		
		assertEquals("/foo.com", resolved);
	}


	@Test
	public void resolveEmpty() {
		
		String base = "/ala/";
		String destURL = "";
		
		String resolved = URLUtils.resolveURL(base, destURL);
		
		assertEquals("", resolved);
	}


	@Test
	public void resolveCSS1() {
		
		String base = "/ala/";
		String css = "background-image:url('resources/522258');";
		
		String resolved = URLUtils.resolveCSSURL(base, css);
		
		assertEquals("background-image:url('/ala/resources/522258');", resolved);
	}


	@Test
	public void resolveCSSreplaceAllUrls() {
		
		String base = "/ala/";
		String css = "background-image:url('aaa/32');image:url('resources/522258');";
		
		String resolved = URLUtils.resolveCSSURL(base, css);

		int index = resolved.indexOf("/ala/res");
		assertTrue(index > 0);
		assertEquals("background-image:url('/ala/aaa/32');image:url('/ala/resources/522258');", resolved);
	}

	@Test
	public void resolveCSSommitNonRelativeUrls() {
		
		String base = "/ala/";
		String css = "background-image:url('/aaa/32');image:url('http://resources/522258');";
		
		String resolved = URLUtils.resolveCSSURL(base, css);

		int index = resolved.indexOf("/ala/");
		assertEquals(-1, index);
	}

	@Test
	public void resolveCSSwithQuotes() {
		String base = "/ala/";
		String css = "background-image:url(\"resources/522258\");";

		String resolved = URLUtils.resolveCSSURL(base, css);

		assertEquals("background-image:url('/ala/resources/522258');", resolved);
	}

	@Test
	public void resolveCSSwithoutQuotes() {
		String base = "/ala/";
		String css = "background-image:url(resources/522258);";

		String resolved = URLUtils.resolveCSSURL(base, css);

		assertEquals("background-image:url('/ala/resources/522258');", resolved);
	}
	
	@Test
	public void resolveEmptyCSS() {
		String base = "/ala/";
		String css = null;

		String resolved = URLUtils.resolveCSSURL(base, css);

		assertEquals(null, resolved);
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
