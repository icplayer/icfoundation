package com.lorepo.icf.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringUtilsTestCase{

	@Test
	public void testStripXML() {
		
		String xml = "<p>Paragraf</p>";
		
		String output = StringUtils.removeAllFormatting(xml);
		
		assertEquals("Paragraf", output);
	}


	@Test
	public void unescapeXML() {
		
		String xml = "{{ 1:\\(G_{1}\\)}}<br>";
		
		String output = StringUtils.unescapeXML(xml);
		
		assertEquals("{{ 1:\\(G_{1}\\)}}<br>", output);
	}
	

	@Test
	public void testUpdateLinks1() {
		
		String xml = "<a src='aaa'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<a src='/bbb/aaa'/>", output);
	}


	@Test
	public void testUpdateLinks2() {
		
		String xml = "<a src='#'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<a src='#'/>", output);
	}


	@Test
	public void testUpdateLinks3() {
		
		String xml = "<a src='http://test'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<a src='http://test'/>", output);
	}


	@Test
	public void testUpdateLinks4() {
		
		String xml = "<a src='/test'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<a src='/test'/>", output);
	}


	@Test
	public void testUpdateLinks5() {
		
		String xml = "<a  src='media/river.jpg'/>";
		
		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/");
		
		assertEquals("<a  src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>", output);
	}
	

	@Test
	public void testUpdateLinks6() {
		
		String xml = "src=\"../resources/1327279.png\"";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("src=\"/bbb/../resources/1327279.png\"", output);
	}


	@Test
	public void testUpdateLinks7() {
		
		String xml = "<img src=\"/file/serve/180636\"><br>fasdfsdafasd<br>fasdfsdafasd<br><br>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<img src=\"/file/serve/180636\"><br>fasdfsdafasd<br>fasdfsdafasd<br><br>", output);
	}


	@Test
	public void updateLinksMulti() {
		
		String expected = "<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>" + 
						"<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>";
		String xml = "<img src='media/river.jpg'/><img src='media/river.jpg'/>";
		
		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/");
		
		assertEquals(expected, output);
	}
}