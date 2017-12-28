package com.lorepo.icf.utils;


/**
 * Globally available utility classes, mostly for string manipulation.
 * 
 * @author Jim Menard, <a href="mailto:jimm@io.com">jimm@io.com</a>
 * @author Krzysztof Langner
 */
public class StringUtils {
  /**
   * Returns a string with HTML special characters replaced by their entity
   * equivalents.
   * 
   * @param str
   *          the string to escape
   * @return a new string without HTML special characters
   */
  public static String escapeHTML(String str) {
    if (str == null || str.length() == 0)
      return "";

    StringBuffer buf = new StringBuffer();
    int len = str.length();
    for (int i = 0; i < len; i++) {
      char c = str.charAt(i);
      switch (c) {
      case '&':
        buf.append("&amp;");
        break;
      case '<':
        buf.append("&lt;");
        break;
      case '>':
        buf.append("&gt;");
        break;
      case '"':
        buf.append("&quot;");
        break;
      case '\'':
        buf.append("&apos;");
        break;
      default:
        buf.append(c);
        break;
      }
    }
    return buf.toString();
  }

  /**
   * Returns a new string where all newlines (&quot;\n&quot;, &quot;\r&quot;, or
   * &quot;\r\n&quot;) have been replaced by &quot;\n&quot; plus XHTML break
   * tags (&quot;\n&lt;br /&gt;&quot;).
   * <p>
   * We don't call <code>splitIntoLines</code> because that method does not
   * tell us if the string ended with a newline or not.
   * 
   * @param str
   *          any string
   * @return a new string with all newlines replaced by &quot;\n&lt;br
   *         /&gt;&quot;
   */
  public static String newlinesToXHTMLBreaks(String str) {
    if (str == null || str.length() == 0)
      return "";

    StringBuffer buf = new StringBuffer();
    int len = str.length();
    for (int i = 0; i < len; i++) {
      char c = str.charAt(i);
      switch (c) {
      case '\n':
        buf.append("\n<br />");
        break;
      case '\r':
        if (i + 1 < len && str.charAt(i + 1) == '\n') // Look for '\n'
          ++i;
        buf.append("\n<br />");
        break;
      default:
        buf.append(c);
        break;
      }
    }
    return buf.toString();
  }

  public static String removeNewlines(String str) {
	    
	  return str.replaceAll("\n","");
  }

  /**
   * Returns a string with XML special characters replaced by their entity
   * equivalents.
   * 
   * @param str
   *          the string to escape
   * @return a new string without XML special characters
   */
  public static String escapeXML(String str) {
    return escapeHTML(str);
  }

  /**
   * Returns a string with XML entities replaced by their normal characters.
   * 
   * @param str
   *          the string to un-escape
   * @return a new normal string
   */
  public static String unescapeXML(String str) {
    if (str == null || str.length() == 0)
      return "";

    StringBuffer buf = new StringBuffer();
    int len = str.length();
    for (int i = 0; i < len; i++) {
      char c = str.charAt(i);
      if (c == '&') {
        int pos = str.indexOf(";", i);
        if (pos == -1) { // Really evil
          buf.append('&');
        } else if (str.charAt(i + 1) == '#') {
          int val = Integer.parseInt(str.substring(i + 2, pos), 16);
          buf.append((char) val);
          i = pos;
        } else {
          String substr = str.substring(i, pos + 1);
          if (substr.equals("&amp;"))
            buf.append('&');
          else if (substr.equals("&lt;"))
            buf.append('<');
          else if (substr.equals("&gt;"))
            buf.append('>');
          else if (substr.equals("&quot;"))
            buf.append('"');
          else if (substr.equals("&apos;"))
            buf.append('\'');
          else
            // ????
            buf.append(substr);
          i = pos;
        }
      } else {
        buf.append(c);
      }
    }
    return buf.toString();
  }

  /**
   * Returns <var>str</var> with leading and trailing spaces trimmed or, if
   * <var>str</var> is <code>null</code>, returns <code>null</code>.
   * 
   * @return str trimmed or <code>null</code>
   */
  public static String nullOrTrimmed(String str) {
    return str == null ? str : str.trim();
  }

  
  public static String removeAllFormatting(String xml){
	  
	  String strippedText = xml.replaceAll("\\<.*?>","");
	  strippedText = strippedText.replaceAll("&nbsp;"," ");
	  return strippedText.trim();
  }

  
	/**
	 * Konwertuje wartość podaną jako "200px" na 200
	 * @param value
	 * @return
	 */
  	public static int px2int(String value){
		
		if(value.endsWith("px")){
			value = value.substring(0, value.length()-2);
			return (int)Double.parseDouble(value);
		}
		
		return 0; 
  	}
  	

	/**
	 * Returns a literal replacement <var>string</var> for the specified
	 * <var>string</var>. The string produced will match the sequence of
	 * characters in input treated as a literal sequence. Slashes ('\'), dollar
	 * signs ('$'), question marks ('?'), dots ('.') and plus marks ('+') will
	 * be given no special meaning. Method is extension of Matcher class method.
	 * 
	 * @param The
	 *            string to be literalized
	 * @return A literal string replacement
	 */
	public static String quoteReplacement(String s) {
		if ((s.indexOf('\\') == -1) && (s.indexOf('$') == -1)
				&& (s.indexOf('?') == -1) && (s.indexOf('.') == -1)
				&& (s.indexOf('+') == -1))
            return s;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
			if (c == '\\' || c == '$' || c == '?' || c == '.' || c == '+') {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

	
	/**
	 * Replace relative links with absolute ones by adding base path.
	 */
	public static String updateLinks(String xml, String baseURL) {

		if(baseURL == null || (!baseURL.startsWith("/") && !baseURL.startsWith("http") && !baseURL.startsWith("file"))){
			return xml;
		}
		
		String input = updateSrcLinks(xml, baseURL);
		String output = updateHrefLinks(input, baseURL);
		return output;
	}

	private static String updateSrcLinks(String xml, String baseURL) {

		String input = xml;
		String output = "";
		int index;

		while( (index = input.indexOf("src=")) >= 0){
			
			index += 5;
			char ch = input.charAt(index-1);
			output += input.substring(0, index);
			input = input.substring(index);
			index = input.indexOf(ch);
			String url = input.substring(0, index);
			input = input.substring(index);
			if(url.startsWith("#") || url.startsWith("/") || url.startsWith("http") || url.startsWith("file")){
				output += url;
			}
			else{
				output += baseURL + url;
			}
		}

		output += input;
		return output;
	}

	private static String updateHrefLinks(String xml, String baseURL) {

		String input = xml;
		String output = "";
		int index;

		while( (index = input.indexOf("href=")) >= 0){
			
			index += 6;
			char ch = input.charAt(index-1);
			output += input.substring(0, index);
			input = input.substring(index);
			index = input.indexOf(ch);
			String url = input.substring(0, index);
			input = input.substring(index);
			if(url.startsWith("#") || url.startsWith("/") || url.startsWith("http") 
					|| url.startsWith("file") || url.startsWith("javascript")){
				output += url;
			}
			else{
				output += baseURL + url;
			}
		}

		output += input;
		return output;
	}
	
	/**
	 * Remove prefix from links
	 */
	public static String removePrefixFromLinks(String xml, String prefix) {

		String input = xml;
		String output = "";
		int index;

		while( (index = input.indexOf("src=")) >= 0){
			
			index += 5;
			char ch = input.charAt(index-1);
			output += input.substring(0, index);
			input = input.substring(index);
			index = input.indexOf(ch);
			String url = input.substring(0, index);
			input = input.substring(index);
			if(url.startsWith(prefix)){
				output += url.substring(prefix.length());
			}
			else{
				output += url;
			}
		}

		output += input;
		return output;
	}
	
	
	/**
	 * Convert markup text to html:
	 * - **text** -> <b>text</b>
	 * - __text__ -> <i>text</i>
	 */
	public static String markup2html(String str) {
		if (str == null || str.length() == 0)
			return "";

	    boolean isBoldMode = false;
	    boolean isItalicMode = false;
	    StringBuffer buf = new StringBuffer();
	    for (int i = 0; i < str.length(); i++) {
	    	char c1 = str.charAt(i);
	    	if(i < str.length()-1){
	    		char c2 = str.charAt(i+1);
	    		if(c1 == '*' && c2 == '*'){
	    			if(isBoldMode) buf.append("</b>");
	    			else buf.append("<b>");
	    			isBoldMode = !isBoldMode;
	    			i++;
	    		}else if(c1 == '_' && c2 == '_'){
		    	  	if(isItalicMode) buf.append("</i>");
		    	  	else buf.append("<i>");
		    	  	isItalicMode = !isItalicMode;
		    	  	i++;
	    		}else{
		    	  	buf.append(c1);
	    		}
	    	}else{
	    		buf.append(c1);
	    	}
	    }
	    return buf.toString();
	}

	public static String removeIllegalCharacters(String in) {
		StringBuffer out = new StringBuffer(); // Used to hold the output.
		char current; // Used to reference the current character.
	
		if (in == null || ("".equals(in))) {
			return "";
		}
		for (int i = 0; i < in.length(); i++) {
			current = in.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD)
					|| ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD))
					|| ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}	
}