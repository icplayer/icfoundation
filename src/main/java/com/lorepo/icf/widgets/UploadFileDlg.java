package com.lorepo.icf.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


/**
 * Upload audio dlg
 * @author Krzysztof Langner
 *
 */
public class UploadFileDlg extends BasicDialogBox {

	private static final String BLOB_UPLOAD_DIR_API = "/editor/api/blobUploadDir";
	private static final int	DLG_WIDTH = 440;
	private static final int	DLG_HEIGHT = 200;

	private FormPanel form;
	private Button saveButton;
	private Button cancelButton;
	private FileUpload upload;
	private IFileUploadListener uploadListener;
	private RadioButton uploadRadioButton;
	private RadioButton urlRadioButton;
	private Label urlLabel;
	private TextBox urlTextBox;
	
	
	public UploadFileDlg(IFileUploadListener listener) {
		
		this.uploadListener = listener;
		
		setText("Upload file");
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

		// Create upload radio
		uploadRadioButton = new RadioButton("upload", "Load from hard drive");
		uploadRadioButton.setValue(true);
		panel.add(uploadRadioButton);
		// Create a FileUpload widget.
        upload = new FileUpload();
        upload.setName("file");
        panel.add(upload);

		// Create url radio
		urlRadioButton = new RadioButton("upload", "Online resource");
		panel.add(urlRadioButton);
		urlLabel = new Label("URL");
		panel.add(urlLabel);
		urlTextBox = new TextBox();
		urlTextBox.setEnabled(false);
		panel.add(urlTextBox);
		
        saveButton = new Button("Save");
        saveButton.setEnabled(false);
        panel.add(saveButton);
        cancelButton = new Button("Cancel");
        panel.add(cancelButton);

        panel.setWidgetPosition(uploadRadioButton, 10, 10);
        panel.setWidgetPosition(upload, 40, 40);

        panel.setWidgetPosition(urlRadioButton, 10, 80);
        panel.setWidgetPosition(urlLabel, 40, 118);
        panel.setWidgetPosition(urlTextBox, 80, 110);
        urlTextBox.setWidth(DLG_WIDTH-120+"px");

        panel.setWidgetPosition(saveButton, DLG_WIDTH-140, DLG_HEIGHT-40);
        panel.setWidgetPosition(cancelButton, DLG_WIDTH-70, DLG_HEIGHT-40);
        
	    return form;
	}


	private void connectHandlers() {
		
        form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
            
            @Override
            public void onSubmitComplete(SubmitCompleteEvent event) {
            	
            	if(uploadListener != null){
            		String result = event.getResults().trim();
            		uploadListener.onFileUploaded(result);
            	}
                UploadFileDlg.this.hide();
            }
        });

		// Add a 'submit' button.
		saveButton.addClickHandler(new ClickHandler() {
            
			@Override
            public void onClick(ClickEvent event) {
				save();
			}
		});            
		
	    cancelButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	UploadFileDlg.this.hide();
	        }
	    });

	    uploadRadioButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				urlTextBox.setEnabled(false);
				upload.setEnabled(true);
			}
		});

	    urlRadioButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				urlTextBox.setEnabled(true);
				upload.setEnabled(false);
			}
		});
	}


	protected void save() {
		
		if(uploadRadioButton.getValue()){
		
			if(!upload.getFilename().isEmpty()){
				form.submit();
			}
		}
		else{
			String json = "{'href' : '" + urlTextBox.getText() + "','contentType' : 'image/png','' : ''}";
    		uploadListener.onFileUploaded(json);
    		hide();
		}
	}


	/**
	 * Pobranie URL-a do uploadu obrazka do bloba
	 * @param uploader
	 */
	private void initBlobUploadUrl() {
		
		RequestBuilder builder = new RequestBuilder(
				RequestBuilder.GET, URL.encode(BLOB_UPLOAD_DIR_API));

		try {
			builder.sendRequest("", new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(response.getStatusCode() == 200){
						form.setAction(response.getText());
						saveButton.setEnabled(true);
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