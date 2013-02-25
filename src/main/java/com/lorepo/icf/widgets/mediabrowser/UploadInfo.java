package com.lorepo.icf.widgets.mediabrowser;

import com.google.gwt.core.client.JavaScriptObject;

public class UploadInfo extends JavaScriptObject{
	
	protected UploadInfo(){
		
	}

	public static final native UploadInfo create(String json) /*-{
		return eval('(' + json + ')');
	}-*/;
	
	
	public final native String getHref() /*-{ 
		return this.href; 
	}-*/;
	
	
	public final native String getFileName() /*-{ 
		return this.fileName; 
	}-*/;
	
	
	public final native String getContentType() /*-{ 
		return this.contentType; 
	}-*/;
}
