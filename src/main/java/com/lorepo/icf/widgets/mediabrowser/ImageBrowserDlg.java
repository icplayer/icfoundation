package com.lorepo.icf.widgets.mediabrowser;

import com.lorepo.icf.utils.i18n.DictionaryWrapper;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider.MediaType;


public class ImageBrowserDlg extends MediaBrowserDlg {

	
	public ImageBrowserDlg(IMediaBrowserListener listener, IMediaProvider images) {
		super(images, listener);
		setText(DictionaryWrapper.get("select_image_dialog"));
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
		String title = getMediaProvider().getMediaName(MediaType.IMAGE, i);
		BrowserCell cell = new BrowserCell(url, title, url);
		return cell;
	}

}