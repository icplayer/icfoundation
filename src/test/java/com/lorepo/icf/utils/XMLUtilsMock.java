/*
The MIT License

Copyright (c) 2009 Krzysztof Langner

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package com.lorepo.icf.utils;

import com.google.gwt.xml.client.CDATASection;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class XMLUtilsMock {

	public static Element createElement(String name) {
		return null;
	}
	
	public static CDATASection createCDATASection(String value) {
		return null;
	}

	public static void setBooleanAttribute(Element element, String key, boolean value) {
		element.setAttribute(key, Boolean.toString(value));
	}
	
	public static void setIntegerAttribute(Element element, String key, int value) {
		element.setAttribute(key, Integer.toString(value));
	}

	/**
	 * Helper function for getting element attribute as string
	 * 
	 * @param name
	 *            Attribute name
	 * @return attribute text or empty string if not found
	 */
	public static String getAttributeAsString(Element element, String name) {
		return getAttributeAsString(element, name, "");
	}

	/**
	 * Helper function for getting element attribute as string with default
	 * value
	 * 
	 * @param name
	 *            Attribute name
	 * @return attribute text or empty string if not found
	 */
	public static String getAttributeAsString(Element element, String name, String defaultValue) {
		String attribute;

		attribute = element.getAttribute(name);
		if (attribute == null) {
			return defaultValue;
		} else {
			return attribute;
		}
	}

	/**
	 * Helper function for getting element attribute as boolean
	 * 
	 * @param name
	 *            Attribute name
	 * @return attribute value or false if not found
	 */
	public static boolean getAttributeAsBoolean(Element element, String name) {
		return getAttributeAsBoolean(element, name, false);
	}

	public static boolean getAttributeAsBoolean(Element element, String name, boolean defaultValue) {
		String attribute;

		attribute = element.getAttribute(name);

		if (!defaultValue) {
			if (attribute == null) {
				return false;
			} else {
				return (attribute.compareToIgnoreCase("true") == 0);
			}
		} else {
			if (attribute == null) {
				return true;
			} else {
				return (attribute.compareToIgnoreCase("false") != 0);
			}
		}
	}

	/**
	 * Helper function for getting element attribute as int
	 * 
	 * @param name
	 *            Attribute name
	 * @return attribute value or 0 if not found
	 */
	public static int getAttributeAsInt(Element element, String name) {
		String attribute;

		attribute = element.getAttribute(name);
		if (attribute == null || attribute.isEmpty()) {
			return 0;
		} else {
			return (int) Float.parseFloat(attribute);
		}
	}
	
	public static int getAttributeAsInt(Element element, String name, int defaultValue) {
		String attribute;

		attribute = element.getAttribute(name);
		if (attribute == null) {
			return defaultValue;
		} else {
			return (int) Float.parseFloat(attribute);
		}
	}

	/**
	 * get all TEXT nodes
	 * 
	 * @return contents of tag
	 */
	public static String getText(Element element) {
		String text = new String();
		NodeList nodes = element.getChildNodes();

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);

			if (node.getNodeType() == Node.TEXT_NODE) {
				text = text + node.getNodeValue();
			}
		}

		return text;
	}

	public static String getCharacterDataFromElement(Element e) {

		Node child = e.getFirstChild();
		if (child instanceof CDATASection) {
			CDATASection cd = (CDATASection) child;
			return cd.getData();
		}

		return null;
	}

	/**
	 * get CDATA node text
	 * 
	 * @return contents of tag
	 */
	public static String getCDATA(Element element) {
		String text = new String();
		NodeList nodes = element.getChildNodes();

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);

			if (node.getNodeType() == Node.CDATA_SECTION_NODE) {
				text = text + node.getNodeValue();
			}
		}

		return text;
	}

	/**
	 * Get first element with given tag name
	 * 
	 * @param tagName
	 * @return first element or null if not found
	 */
	public static Element getFirstElementWithTagName(Element element,
			String tagName) {

		Element node = null;
		NodeList nodeList = element.getElementsByTagName(tagName);

		if (nodeList.getLength() > 0) {
			node = (Element) nodeList.item(0);
		}

		return node;
	}

	/**
	 * Get content of the first element with given tag name, or default value
	 *
	 * @param element element to search in
	 * @param tagName tagname to search for
	 * @param defaultValue value to return if tag not found
	 * @return value of the tag, or default value
	 */
	public static String getFirstElementContentWithTagName(
			Element element, String tagName, String defaultValue
	) {
		Element node = XMLUtilsMock.getFirstElementWithTagName(element, tagName);

		return node == null ? defaultValue : XMLUtilsMock.getText(node);
	}
}
