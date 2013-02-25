package com.lorepo.icf.properties.ui.editor;

import com.google.gwt.user.client.ui.DialogBox;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.widgets.mediabrowser.IMediaBrowserListener;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider;
import com.lorepo.icf.widgets.mediabrowser.ImageBrowserDlg;

class ImagePropertyEditor extends DlgPropertyEditor{

	private IMediaProvider imageProvider;
	
	public ImagePropertyEditor(IProperty property, IMediaProvider mediaProvider) {
		super(property);
		
		this.imageProvider = mediaProvider;
	}

	protected DialogBox createEditor() {
		
		IMediaBrowserListener listener = new IMediaBrowserListener() {
			
			@Override
			public void onMediaSelected(String url) {
				getProperty().setValue(url);
			}
		};
		
		return new ImageBrowserDlg(listener, imageProvider);
	}
	
}
