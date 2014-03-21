package com.lorepo.icf.widgets.mediabrowser;

import com.google.gwt.core.client.GWT;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider.MediaType;



public class FileBrowserDlg extends MediaBrowserDlg {

	private static String PREVIEW_FILE_URL = GWT.getModuleBaseURL() + "images/no_thumbnail.png";
	
	public FileBrowserDlg(IMediaBrowserListener listener, IMediaProvider provider) {

		super(provider, listener);
	
		setText("Select file");
	}
	
	
	@Override
	protected void addNewMedia(UploadInfo uploadInfo){
		getMediaProvider().addMediaUrl(MediaType.FILE, uploadInfo.getHref(),
				uploadInfo.getFileName(), uploadInfo.getContentType());
	}

	@Override
	protected int getCellCount(){
		return getMediaProvider().getMediaCount(); 
	}

	@Override
	protected BrowserCell createCellWidget(int index) {
		String url = getMediaProvider().getMediaUrl(index);
		String title = getMediaProvider().getMediaName(index);
		return new BrowserCell(url, title, PREVIEW_FILE_URL);
	}
}