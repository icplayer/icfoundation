package com.lorepo.icf.properties.ui.editor;

import com.google.gwt.user.client.ui.DialogBox;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.widgets.mediabrowser.FileBrowserDlg;
import com.lorepo.icf.widgets.mediabrowser.IMediaBrowserListener;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider;

class FilePropertyEditor extends DlgPropertyEditor{

	private final IMediaProvider mediaProvider;
	
	public FilePropertyEditor(IProperty property, IMediaProvider provider) {
		super(property);
		
		this.mediaProvider = provider;
	}

	protected DialogBox createEditor() {
		
		IMediaBrowserListener listener = new IMediaBrowserListener() {
			public void onMediaSelected(String url) {
				getProperty().setValue(url);
			}
		};
		
		return new FileBrowserDlg(listener, mediaProvider);
	}
	
}
