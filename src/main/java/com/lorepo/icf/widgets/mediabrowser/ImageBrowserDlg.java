package com.lorepo.icf.widgets.mediabrowser;

import com.lorepo.icf.widgets.mediabrowser.IMediaProvider.MediaType;



/**
 * Image browser
 * @author Krzysztof Langner
 *
 */
public class ImageBrowserDlg extends MediaBrowserDlg {

	
	
	public ImageBrowserDlg(IMediaBrowserListener listener, IMediaProvider images) {

		super(images, listener);
	
		setText("Select Image");
	}

	
	@Override
	protected void addNewMedia(UploadInfo uploadInfo){
		getMediaProvider().addMediaUrl(MediaType.IMAGE, uploadInfo.getHref(),
				uploadInfo.getFileName(), uploadInfo.getContentType());
	}
	
	
	protected int getCellCount(){
		return getMediaProvider().getMediaCount(MediaType.IMAGE);
	}

	protected BrowserCell createCellWidget(int i) {
		
		String url = getMediaProvider().getMediaUrl(MediaType.IMAGE, i);
		BrowserCell cell = new BrowserCell(url, url);
		return cell;
	}

}