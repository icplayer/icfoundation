package com.lorepo.icf.utils;

import com.google.gwt.core.client.JavaScriptObject;


interface ITextToSpeechVoice {
	JavaScriptObject getObject();
}

public class TextToSpeechVoice extends JavaScriptObject implements ITextToSpeechVoice {
	protected TextToSpeechVoice() {}
	
	public static native TextToSpeechVoice create () /*-{
		return {
			text: null,
			lang: null
		};
	}-*/;
	
	public static native TextToSpeechVoice create (String text) /*-{
		return {
			text: text,
			lang: null
		};
	}-*/;
	
	public static native TextToSpeechVoice create (String text, String lang) /*-{
		return {
			text: text,
			lang: lang
		};
	}-*/; 
	
	public final native TextToSpeechVoice getObject () /*-{
		return {
			text: this.text,
			lang: this.lang
		};
	}-*/;
	
}

class TextToSpeechVoiceImpl implements ITextToSpeechVoice {
	private final JavaScriptObject obj;
	
	public TextToSpeechVoiceImpl (JavaScriptObject obj) {
		this.obj = obj;
	}
	
	public JavaScriptObject getObject () {
		return this.obj;
	}
}
