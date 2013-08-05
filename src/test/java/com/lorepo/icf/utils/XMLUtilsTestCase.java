package com.lorepo.icf.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class XMLUtilsTestCase{

	@Test
	public void testRemoveIllegalChars() {
		String xml = "[<'^]In chapter 6";
		String output = XMLUtils.removeIllegalCharacters(xml);
		assertEquals("[<'^]In chapter 6", output);
	}
}