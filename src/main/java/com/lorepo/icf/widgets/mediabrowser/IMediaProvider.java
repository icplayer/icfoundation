package com.lorepo.icf.widgets.mediabrowser;

public interface IMediaProvider {

	public enum MediaType{
		AUDIO,
		IMAGE,
		VIDEO,
		FILE,
		SCRIPT
	}
	
	public int getMediaCount(MediaType type);
	public String getMediaUrl(MediaType type, int index);
	public String getMediaName(MediaType type, int index);
	public void	addMediaUrl(MediaType type, String url, String fileName, String contentType);
	/** Count all media */
	public int getMediaCount();
	public String getMediaUrl(int index);
	public String getMediaName(int index);
	public String getContentType(int index);
}
