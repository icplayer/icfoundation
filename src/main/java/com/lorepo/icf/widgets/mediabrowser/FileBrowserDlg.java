package com.lorepo.icf.widgets.mediabrowser;

import com.google.gwt.core.client.GWT;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider.MediaType;



public class FileBrowserDlg extends MediaBrowserDlg {

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
		String iconUrl = getIconUrl(getMediaProvider().getContentType(index), url);
		return new BrowserCell(url, title, iconUrl);
	}
	
	private static String getIconUrl(String contentType, String imageUrl){
		String type;
		int index = contentType.indexOf('/');
		if(index > 0){
			type = contentType.substring(0, index);
		}
		else{
			type = contentType;
		}
		
		if(type.equals("video")){
			return GWT.getModuleBaseURL() + "images/video_icon.png";
		}
		else if(type.equals("audio")){
			return GWT.getModuleBaseURL() + "images/audio_icon.png";
		}
		else if(type.equals("image")){
			return imageUrl + "/150/130";
		}
		else{
			return GWT.getModuleBaseURL() + "images/file_icon.png";
		}
	}
}