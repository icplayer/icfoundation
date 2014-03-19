package com.lorepo.icf.widgets.mediabrowser;

public interface IMediaProvider {

	public enum MediaType{
		AUDIO,
		IMAGE,
		VIDEO,
		FILE
	}
	
	public int getMediaCount(MediaType type);
	public String getMediaUrl(MediaType type, int index);
	public String getMediaName(MediaType type, int index);
	public void	addMediaUrl(MediaType type, String url, String fileName, String contentType);
}
