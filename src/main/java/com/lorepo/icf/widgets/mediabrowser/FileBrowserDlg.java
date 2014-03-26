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
	
	private static String getIconUrl(String type, String imageUrl){
		
		if(type.startsWith("video")){
			return GWT.getModuleBaseURL() + "images/video_icon.png";
		}
		else if(type.startsWith("audio")){
			return GWT.getModuleBaseURL() + "images/audio_icon.png";
		}
		else if(type.equals("image/png") || type.equals("image/jpeg")){
			if(imageUrl.startsWith("/file/serve")){
				String url = imageUrl.replace("serve", "thumbnail");
				return url + "/150/130";
			}
			else{
				return GWT.getModuleBaseURL() + "images/image_icon.png";
			}
		}
		else if(type.startsWith("image")){
			return GWT.getModuleBaseURL() + "images/image_icon.png";
		}
		else{
			return GWT.getModuleBaseURL() + "images/file_icon.png";
		}
	}
}