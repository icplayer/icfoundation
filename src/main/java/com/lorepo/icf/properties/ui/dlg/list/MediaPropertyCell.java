package com.lorepo.icf.properties.ui.dlg.list;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.lorepo.icf.properties.IImageProperty;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.utils.i18n.DictionaryWrapper;
import com.lorepo.icf.widgets.mediabrowser.IMediaBrowserListener;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider;
import com.lorepo.icf.widgets.mediabrowser.ImageBrowserDlg;

public class MediaPropertyCell extends HorizontalPanel implements IItemCellEditor{

	private IProperty property;
	private String url;
	private Button uploadButton;
	private Label urlLabel;
	private IMediaProvider mediaProvider;
	
	
	public MediaPropertyCell(IImageProperty property, IMediaProvider mediaProvider){
	
		this.property = property;
		this.mediaProvider = mediaProvider;
		createUI();
		
		connectHandlers();
	}


	private void createUI() {

		urlLabel = new Label();
		if(property.getValue().isEmpty()){
			urlLabel.setText(DictionaryWrapper.get("none"));
		}
		else{
			urlLabel.setText(property.getValue());
		}
			
		add(urlLabel);
		uploadButton = new Button(DictionaryWrapper.get("change"));
		add(uploadButton);
		
		setCellVerticalAlignment(urlLabel, ALIGN_MIDDLE);
		setSpacing(10);
	}


	private void connectHandlers() {

		uploadButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showUploadDlg();
			}
		});
	}


	protected void showUploadDlg() {

		IMediaBrowserListener listener = new IMediaBrowserListener() {
			
			@Override
			public void onMediaSelected(String url) {
				updateURL(url);
			}
		};
		
		new ImageBrowserDlg(listener, mediaProvider);		
	}


	private void updateURL(String uploaderURL) {
		url = uploaderURL;
		urlLabel.setText(url);
	}
	
	
	@Override
	public void save() {
		
		if(url != null){
			property.setValue(url);
		}
	}
}
