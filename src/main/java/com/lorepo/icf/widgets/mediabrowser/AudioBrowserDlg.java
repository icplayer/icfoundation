package com.lorepo.icf.widgets.mediabrowser;

import com.google.gwt.core.client.GWT;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider.MediaType;



/**
 * Audio browser
 * @author Krzysztof Langner
 *
 */
public class AudioBrowserDlg extends MediaBrowserDlg {

	private static String PREVIEW_IMAGE_URL = GWT.getModuleBaseURL() + "images/audio_icon.png";
	
	
	public AudioBrowserDlg(IMediaBrowserListener listener, IMediaProvider provider) {

		super(provider, listener);
	
		setText("Select audio");
	}
	
	
	@Override
	protected void addNewMedia(UploadInfo uploadInfo){
		getMediaProvider().addMediaUrl(MediaType.AUDIO, uploadInfo.getHref(),
				uploadInfo.getFileName(), uploadInfo.getContentType());
	}

	
	protected int getCellCount(){
		return getMediaProvider().getMediaCount(MediaType.AUDIO);
	}

	protected BrowserCell createCellWidget(int index) {
		
		String url = getMediaProvider().getMediaUrl(MediaType.AUDIO, index);
		String title = getMediaProvider().getMediaName(MediaType.AUDIO, index);
		BrowserCell cell = new BrowserCell(url, title, PREVIEW_IMAGE_URL);
		return cell;
	}
}