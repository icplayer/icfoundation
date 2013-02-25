package com.lorepo.icf.widgets.mediabrowser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.Widget;
import com.lorepo.icf.widgets.BasicDialogBox;


/**
 * Upload image dlg
 * @author Krzysztof Langner
 *
 */
class UploadMediaDlg extends BasicDialogBox {

	private static final String BLOB_UPLOAD_DIR_API = "/editor/api/blobUploadDir";
	private static final int	DLG_WIDTH = 400;
	private static final int	DLG_HEIGHT = 100;

	private FormPanel form;
	private Button sendImageButton;
	private Button cancelButton;
	private FileUpload upload;
	private IMediaUploadListener uploadListener;
	
	
	public UploadMediaDlg(String title, IMediaUploadListener listener) {
		
		this.uploadListener = listener;
		
		setText(title);
		setAnimationEnabled(true);
		setGlassEnabled(true);

		setWidget(createUI());
		connectHandlers();
		initBlobUploadUrl();
  
		center();
	}

	  
	/**
	 * Create dialogbox UI
	 * @return
	 */
	private Widget createUI() {
	
		form = new FormPanel();
		form.setWidth("100%");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);              
		
		AbsolutePanel panel = new AbsolutePanel();
		panel.setPixelSize(DLG_WIDTH, DLG_HEIGHT);
		form.setWidget(panel);          

		// Create a FileUpload widget.
        upload = new FileUpload();
        upload.setName("file");
        panel.add(upload);

        sendImageButton = new Button("Upload");
        panel.add(sendImageButton);
        cancelButton = new Button("Cancel");
        panel.add(cancelButton);

        panel.setWidgetPosition(upload, 10, 10);
        panel.setWidgetPosition(sendImageButton, DLG_WIDTH-140, DLG_HEIGHT-40);
        panel.setWidgetPosition(cancelButton, DLG_WIDTH-70, DLG_HEIGHT-40);
        
	    return form;
	}


	private void connectHandlers() {
		
        form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
            
            @Override
            public void onSubmitComplete(SubmitCompleteEvent event) {
            	String result = event.getResults().trim();
            	if(uploadListener != null && result.compareToIgnoreCase("ERROR") != 0){
            		uploadListener.onImageUploaded(result);
            	}
                UploadMediaDlg.this.hide();
            }
        });

		// Add a 'submit' button.
		sendImageButton.addClickHandler(new ClickHandler() {
            
			@Override
            public void onClick(ClickEvent event) {
				if(!upload.getFilename().isEmpty()){
					form.submit();
				}
			}
		});            
		
	    cancelButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	UploadMediaDlg.this.hide();
	        }
	    });

	}


	/**
	 * Pobranie URL-a do uploadu obrazka do bloba
	 * @param uploader
	 */
	private void initBlobUploadUrl() {
		
		RequestBuilder builder = new RequestBuilder(
				RequestBuilder.GET, URL.encode(BLOB_UPLOAD_DIR_API));
		builder.setHeader("Content-Type", "text/xml");
		builder.setHeader("Content-Length", "0");

		try {
			builder.sendRequest("", new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(response.getStatusCode() == 200){
						form.setAction(response.getText());
					}
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
				}
			});
		} catch (RequestException e) {
		}		
	}

}