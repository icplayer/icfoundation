package com.lorepo.icf.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringUtilsTestCase{

	@Before
	public void setUp() {}

	@After
	public void tearDown () {
		ExtendedRequestBuilder.setSigningPrefix(null);
		ExtendedRequestBuilder.setGlobalIncludeCredentials(false);
	}

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
	public void testUpdateLinks1WithContentBaseURLAsNull() {

		String xml = "<img src='aaa'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", null);

		assertEquals("<img src='/bbb/aaa'/>", output);
	}

	@Test
	public void testUpdateLinks1WithContentBaseURL() {

		String xml = "<img src='aaa'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", "http://boo.com/");

		assertEquals("<img src='http://boo.com/aaa'/>", output);
	}

	@Test
	public void testUpdateHrefLinks1() {
		
		String xml = "<a href='aaa'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<a href='/bbb/aaa'/>", output);
	}

	@Test
	public void testUpdateHrefLinks1WithContentBaseURLAsNull() {

		String xml = "<a href='aaa'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", null);

		assertEquals("<a href='/bbb/aaa'/>", output);
	}

	@Test
	public void testUpdateHrefLinks1WithContentBaseURL() {

		String xml = "<a href='aaa'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", "http://boo.com/");

		assertEquals("<a href='http://boo.com/aaa'/>", output);
	}

	@Test
	public void testUpdateLinks2() {
		
		String xml = "<a src='#'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<a src='#'/>", output);
	}

	@Test
	public void testUpdateLinks2WithContentBaseURLAsNull() {

		String xml = "<a src='#'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", null);

		assertEquals("<a src='#'/>", output);
	}

	@Test
	public void testUpdateLinks2WithContentBaseURL() {

		String xml = "<a src='#'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", "http://boo.com/");

		assertEquals("<a src='#'/>", output);
	}

	@Test
	public void testUpdateLinks3() {
		
		String xml = "<a src='http://test'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<a src='http://test'/>", output);
	}

	@Test
	public void testUpdateLinks3WithConentBaseURLAsNull() {

		String xml = "<a src='http://test'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", null);

		assertEquals("<a src='http://test'/>", output);
	}

	@Test
	public void testUpdateLinks3WithConentBaseURL() {

		String xml = "<a src='http://test'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", "http://boo.com/");

		assertEquals("<a src='http://test'/>", output);
	}

	@Test
	public void testUpdateLinks4() {
		
		String xml = "<a src='/test'/>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<a src='/test'/>", output);
	}

	@Test
	public void testUpdateLinks4WithConentBaseURLAsNull() {

		String xml = "<a src='/test'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", null);

		assertEquals("<a src='/test'/>", output);
	}

	@Test
	public void testUpdateLinks4WithConentBaseURL() {

		String xml = "<a src='/test'/>";

		String output = StringUtils.updateLinks(xml, "/bbb/", "http://boo.com/");

		assertEquals("<a src='http://boo.com//test'/>", output);
	}

	@Test
	public void testUpdateLinks5() {
		
		String xml = "<img src='media/river.jpg'/>";
		
		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/");
		
		assertEquals("<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>", output);
	}

	@Test
	public void testUpdateLinks5WithConentBaseURLAsNull() {

		String xml = "<img src='media/river.jpg'/>";

		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/", null);

		assertEquals("<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>", output);
	}

	@Test
	public void testUpdateLinks5WithConentBaseURL() {

		String xml = "<img src='media/river.jpg'/>";

		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/", "http://boo.com/");

		assertEquals("<img src='http://boo.com/media/river.jpg'/>", output);
	}

	@Test
	public void testUpdateLinks6() {
		
		String xml = "src=\"../resources/1327279.png\"";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("src=\"/bbb/../resources/1327279.png\"", output);
	}

	@Test
	public void testUpdateLinks6WithContentBaseURLAsNull() {

		String xml = "src=\"../resources/1327279.png\"";

		String output = StringUtils.updateLinks(xml, "/bbb/", null);

		assertEquals("src=\"/bbb/../resources/1327279.png\"", output);
	}

	@Test
	public void testUpdateLinks6WithContentBaseURL() {

		String xml = "src=\"../resources/1327279.png\"";

		String output = StringUtils.updateLinks(xml, "/bbb/", "http://boo.com/");

		assertEquals("src=\"http://boo.com/../resources/1327279.png\"", output);
	}

	@Test
	public void testUpdateLinks7() {
		
		String xml = "<img src=\"/file/serve/180636\"><br>fasdfsdafasd<br>fasdfsdafasd<br><br>";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals("<img src=\"/file/serve/180636\"><br>fasdfsdafasd<br>fasdfsdafasd<br><br>", output);
	}

	@Test
	public void testUpdateLinks7WithConentBaseURLAsNull() {

		String xml = "<img src=\"/file/serve/180636\"><br>fasdfsdafasd<br>fasdfsdafasd<br><br>";

		String output = StringUtils.updateLinks(xml, "/bbb/", null);

		assertEquals("<img src=\"/file/serve/180636\"><br>fasdfsdafasd<br>fasdfsdafasd<br><br>", output);
	}

	@Test
	public void testUpdateLinks7WithConentBaseURL() {

		String xml = "<img src=\"/file/serve/180636\"><br>fasdfsdafasd<br>fasdfsdafasd<br><br>";

		String output = StringUtils.updateLinks(xml, "/bbb/", "http://boo.com/");

		assertEquals("<img src=\"http://boo.com//file/serve/180636\"><br>fasdfsdafasd<br>fasdfsdafasd<br><br>", output);
	}

	@Test
	public void testUpdateLinks8() {
		
		String expected = "<img src=\"capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/../resources/123\">";
		String xml = "<img src=\"capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/../resources/123\">";
		
		String output = StringUtils.updateLinks(xml, "/bbb/");
		
		assertEquals(expected, output);
	}

	@Test
	public void testUpdateLinks8WithConentBaseURLAsNull() {

		String expected = "<img src=\"capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/../resources/123\">";
		String xml = "<img src=\"capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/../resources/123\">";

		String output = StringUtils.updateLinks(xml, "/bbb/", null);

		assertEquals(expected, output);
	}

	@Test
	public void testUpdateLinks8WithConentBaseURL() {

		String expected = "<img src=\"capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/../resources/123\">";
		String xml = "<img src=\"capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/../resources/123\">";

		String output = StringUtils.updateLinks(xml, "/bbb/", "http://boo.com/");

		assertEquals(expected, output);
	}
	
	@Test
	public void testUpdateLinks9() {
		
		String expected = "<img src=\"capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/../resources/123\">";
		String xml = "<img src=\"../resources/123\">";
		
		String output = StringUtils.updateLinks(xml, "capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/");
		
		assertEquals(expected, output);
	}

	@Test
	public void testUpdateLinks9WithConentBaseURLAsNull() {

		String expected = "<img src=\"capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/../resources/123\">";
		String xml = "<img src=\"../resources/123\">";

		String output = StringUtils.updateLinks(xml, "capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/", null);

		assertEquals(expected, output);
	}

	@Test
	public void testUpdateLinks9WithConentBaseURL() {

		String expected = "<img src=\"http://boo.com/../resources/123\">";
		String xml = "<img src=\"../resources/123\">";

		String output = StringUtils.updateLinks(xml, "capacitor://pec/_capacitor_file_/var/mobile/Containers/Data/Application/", "http://boo.com/");

		assertEquals(expected, output);
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
	public void updateLinksMultiWithConentBaseURLAsNull() {

		String expected = "<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>" +
						"<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>";
		String xml = "<img src='media/river.jpg'/><img src='media/river.jpg'/>";

		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/", null);

		assertEquals(expected, output);
	}

	@Test
	public void updateLinksMultiWithConentBaseURL() {

		String expected = "<img src='http://boo.com/media/river.jpg'/>" +
						"<img src='http://boo.com/media/river.jpg'/>";
		String xml = "<img src='media/river.jpg'/><img src='media/river.jpg'/>";

		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/", "http://boo.com/");

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
	
	@Test
	public void givenNullAsBaseURLWhenUpdateLinksCalledThenReturnGivenText() {
		String xml = "<img src='aaa'/><img href='aaa'/>";
		
		String output = StringUtils.updateLinks(xml, null);
		
		assertEquals("<img src='aaa'/><img href='aaa'/>", output);
	}
	
	@Test
	public void givenDomainNameAsBaseURLWhenUpdateLinksCalledThenReturnGivenText() {
		String xml = "<img src='aaa'/><img href='aaa'/>";
		
		String output = StringUtils.updateLinks(xml, "test.com");
		
		assertEquals("<img src='aaa'/><img href='aaa'/>", output);
	}
	
	@Test
	public void givenNullAsBaseURLAndSigningPrefixWhenUpdateLinksCalledThenReturnGivenTextWithSignedURL() {
		ExtendedRequestBuilder.addPageToWhitelist("/file/serve/");
		ExtendedRequestBuilder.setSigningPrefix("123");
		String xml = "<img src='/file/serve/aaa'/><img href='/file/serve/aaa'/>";
		
		String output = StringUtils.updateLinks(xml, null);
		
		assertEquals("<img src='/file/serve/aaa?123'/><img href='/file/serve/aaa?123'/>", output);
	}

	@Test
	public void givenDomainNameAsBaseURLAndSigningPrefixWhenUpdateLinksCalledThenReturnGivenTextWithSignedURL() {
		ExtendedRequestBuilder.addPageToWhitelist("mauthor");
		ExtendedRequestBuilder.setSigningPrefix("123");
		String xml = "<img src='https://mauthor/aaa'/><img href='https://mauthor/aaa'/>";
		
		String output = StringUtils.updateLinks(xml, "test.com");
		
		assertEquals("<img src='https://mauthor/aaa?123'/><img href='https://mauthor/aaa?123'/>", output);
	}
	
	@Test
	public void notUpdateLinksMultiWithUntrustedURLAndSigningPrefix() {
		ExtendedRequestBuilder.addPageToWhitelist("mauthor");
		ExtendedRequestBuilder.setSigningPrefix("123");
		String expected = "<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>" +
						"<img src='http://127.0.0.1:8888/content/pages/media/river.jpg'/>";
		String xml = "<img src='media/river.jpg'/><img src='media/river.jpg'/>";
		
		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/");
		
		assertEquals(expected, output);
	}
	
	@Test
	public void updateLinksMultiWithConentBaseURLAndSigningPrefix() {
		ExtendedRequestBuilder.addPageToWhitelist("mauthor");
		ExtendedRequestBuilder.setSigningPrefix("123");
		String expected = "<img src='/mauthor/boo.com/media/river.jpg?123'/>" +
						"<img src='/mauthor/boo.com/media/river.jpg?123'/>";
		String xml = "<img src='media/river.jpg'/><img src='media/river.jpg'/>";
		
		String output = StringUtils.updateLinks(xml, "http://127.0.0.1:8888/content/pages/", "/mauthor/boo.com/");
		
		assertEquals(expected, output);
	}
}
