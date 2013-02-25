package com.lorepo.icf.scripting;

@SuppressWarnings("serial")
public class ScriptParserException extends Exception {

	public ScriptParserException(String expected, String received){
		super("Expected: " + expected + " Received: " + received);
	}
}
