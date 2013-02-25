package com.lorepo.icf.scripting;

@SuppressWarnings("serial")
public class ScriptEngineException extends Exception {

	public ScriptEngineException(String script, String problem){
		super(script + " \n " + problem);
	}
}
