package com.lorepo.icf.widgets.mediabrowser;

import com.google.gwt.core.client.GWT;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider.MediaType;



public class FileBrowserDlg extends MediaBrowserDlg {

	private static String PREVIEW_FILE_URL = GWT.getModuleBaseURL() + "images/audio_icon.png";
	
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
		return getMediaProvider().getMediaCount(MediaType.AUDIO) 
				+ getMediaProvider().getMediaCount(MediaType.FILE)
				+ getMediaProvider().getMediaCount(MediaType.VIDEO);
	}

	@Override
	protected BrowserCell createCellWidget(int index) {
		
		String url = null;
		String title = null;
		
		if(index < getMediaProvider().getMediaCount(MediaType.AUDIO)){
			url = getMediaProvider().getMediaUrl(MediaType.AUDIO, index);
			title = getMediaProvider().getMediaName(MediaType.AUDIO, index);
		}
		else{
			index -= getMediaProvider().getMediaCount(MediaType.AUDIO);
			if(index < getMediaProvider().getMediaCount(MediaType.FILE)){
				url = getMediaProvider().getMediaUrl(MediaType.FILE, index);
				title = getMediaProvider().getMediaName(MediaType.FILE, index);
			}
			else{
				index -= getMediaProvider().getMediaCount(MediaType.FILE);
				url = getMediaProvider().getMediaUrl(MediaType.VIDEO, index);
				title = getMediaProvider().getMediaName(MediaType.VIDEO, index);
			}
		}

		return new BrowserCell(url, title, PREVIEW_FILE_URL);
	}
}