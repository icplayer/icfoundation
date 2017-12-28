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
		
		String xml = "<img src='aaa'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<img src='/bbb/aaa'/>", output);
	}


	@Test
	public void testUpdateHrefLinks1() {
		
		String xml = "<a href='aaa'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<a href='/bbb/aaa'/>", output);
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
		
		String xml = "<img src='media/river.jpg'/>";
		
		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/");
		
		assertEquals("<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>", output);
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
	
	@Test
	public void testQuoteReplacementQuestion() {
		
		String expected = "Lorem ipsum dolor \\? sit amet, consectetur adipisicing elit";
		String input = "Lorem ipsum dolor ? sit amet, consectetur adipisicing elit";
		
		String output = StringUtils.quoteReplacement(input);
		
		assertEquals(expected, output);
	}
	
	@Test
	public void testQuoteReplacementSlash() {
		
		String expected = "Lorem ip\\\\sum dolor sit amet, cons|ectetur adipisicing elit";
		String input = "Lorem ip\\sum dolor sit amet, cons|ectetur adipisicing elit";
		
		String output = StringUtils.quoteReplacement(input);
		
		assertEquals(expected, output);
	}
	
	@Test
	public void testQuoteReplacementDots() {

		String expected = "Lorem ipsum d\\.\\.olor sit amet, cons|ectetur adipisicing elit";
		String input = "Lorem ipsum d..olor sit amet, cons|ectetur adipisicing elit";

		String output = StringUtils.quoteReplacement(input);

		assertEquals(expected, output);
	}

	@Test
	public void testQuoteReplacementPlus() {

		String expected = "Lorem ipsum dolor sit amet, cons|ectetur adipisicing elit\\+";
		String input = "Lorem ipsum dolor sit amet, cons|ectetur adipisicing elit+";

		String output = StringUtils.quoteReplacement(input);

		assertEquals(expected, output);
	}

	@Test
	public void testQuoteReplacementDolar() {
		String expected = "Lore=m ipsum dolor sit a\\$met, consectetur adipis&icing elit";
		String input = "Lore=m ipsum dolor sit a$met, consectetur adipis&icing elit";
		
		String output = StringUtils.quoteReplacement(input);
		
		assertEquals(expected, output);
	}
	
	@Test
	public void testQuoteReplacementNone() {
		String expected = "_Lore=m ipsum dolor sit amet, conse-ctetur adipis&icing elit";
		String input = "_Lore=m ipsum dolor sit amet, conse-ctetur adipis&icing elit";
		
		String output = StringUtils.quoteReplacement(input);
		
		assertEquals(expected, output);
	}
	
	@Test
	public void testQuoteReplacementMulti() {
		String expected = "\\$Lore=m ipsum d\\+olor sit amet,\\\\\\?\\$ consectetur adipis&icing elit\\\\";
		String input = "$Lore=m ipsum d+olor sit amet,\\?$ consectetur adipis&icing elit\\";
		
		String output = StringUtils.quoteReplacement(input);
		
		assertEquals(expected, output);
	}



	@Test
	public void javascript() {
		String xml = "<a href='javascript:void(0)'/>";
		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/");
		
		assertEquals("<a href='javascript:void(0)'/>", output);
	}

	@Test
	public void removePrefix() {
		
		String xml = "<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>" + 
						"<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>";
		String expected = "<img src='/content/pages/media/river.jpg'/><img src='/content/pages/media/river.jpg'/>";
		String output = StringUtils.removePrefixFromLinks(xml, "http://127.0.0.1:8888");
		
		assertEquals(expected, output);
	}

	@Test
	public void markup2html1() {
		
		String markup = "This is **bold** and __italic__";
		String html = StringUtils.markup2html(markup);
		
		assertEquals("This is <b>bold</b> and <i>italic</i>", html);
	}

	@Test
	public void markup2html2() {
		
		String markup = "This is **bold** and __italic__.";
		String html = StringUtils.markup2html(markup);
		
		assertEquals("This is <b>bold</b> and <i>italic</i>.", html);
	}
	
	@Test
	public void testRemoveIllegalChars() {
		String xml = "[<'^]In chapter 6";
		String output = StringUtils.removeIllegalCharacters(xml);
		assertEquals("[<'^]In chapter 6", output);
	}
}