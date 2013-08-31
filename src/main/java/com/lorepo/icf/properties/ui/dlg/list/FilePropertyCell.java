package com.lorepo.icf.properties.ui.dlg.list;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.lorepo.icf.properties.IFileProperty;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.utils.i18n.DictionaryWrapper;
import com.lorepo.icf.widgets.IFileUploadListener;
import com.lorepo.icf.widgets.UploadFileDlg;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider.MediaType;
import com.lorepo.icf.widgets.mediabrowser.UploadInfo;

public class FilePropertyCell extends HorizontalPanel implements IItemCellEditor{

	private final IProperty property;
	private final IMediaProvider mediaProvider;
	private String url;
	private Button uploadButton;
	private Label urlLabel;
	
	
	public FilePropertyCell(IFileProperty property, IMediaProvider mediaProvider){
	
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

		new UploadFileDlg(new IFileUploadListener() {
			public void onFileUploaded(String json) {
				UploadInfo uploadInfo = UploadInfo.create(json);
				updateURL(uploadInfo);
			}

		});
	}


	private void updateURL(UploadInfo uploadInfo) {
		url = uploadInfo.getHref();
		urlLabel.setText(url);
		mediaProvider.addMediaUrl(MediaType.FILE, uploadInfo.getHref(),
				uploadInfo.getFileName(), uploadInfo.getContentType());
	}
	
	
	@Override
	public void save() {
		
		if(url != null){
			property.setValue(url);
		}
	}
}
