package com.lorepo.icf.widgets.mediabrowser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lorepo.icf.widgets.BasicDialogBox;


/**
 * Media browser base class
 * 
 * @author Krzysztof Langner
 *
 */
public class MediaBrowserDlg extends BasicDialogBox {

	private static final int	DLG_WIDTH = 640;
	private static final int	DLG_HEIGHT = 640;
	
	private IMediaProvider 	mediaProvider;
	private IMediaBrowserListener listener;
	private AbsolutePanel	innerPanel;
	private Grid 			mediaGrid;
	private Button addMediaButton;
	private Button cancelButton;
	
	
	public MediaBrowserDlg(	IMediaProvider 	mediaProvider, IMediaBrowserListener listener) {

		this.mediaProvider = mediaProvider;
		this.listener = listener;
		
		setAnimationEnabled(true);
		setGlassEnabled(true);

		setWidget(createUI());
		populateGridWithImages();
		connectHandlers();
  
		center();
	}

	  
	/**
	 * Create dialogbox UI
	 * @return
	 */
	private Widget createUI() {
	
		innerPanel = new AbsolutePanel();
		innerPanel.setPixelSize(DLG_WIDTH, DLG_HEIGHT);
		
		int rowCount = getCellCount()/3+1;
		mediaGrid = new Grid(rowCount, 3);
		mediaGrid.setWidth("100%");
		
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.addStyleName("ice_imageList");
		scrollPanel.setPixelSize(DLG_WIDTH-10, DLG_HEIGHT-70);
		scrollPanel.add(mediaGrid);
	
		innerPanel.add(scrollPanel);
		
	    addMediaButton = new Button("New...");
	    innerPanel.add(addMediaButton);
	    cancelButton = new Button("Cancel");
	    innerPanel.add(cancelButton);
	    
	    
	    // Set widget positions
	    innerPanel.setWidgetPosition(scrollPanel, 5, 5);
	    innerPanel.setWidgetPosition(cancelButton, DLG_WIDTH-70, DLG_HEIGHT-40);
	    innerPanel.setWidgetPosition(addMediaButton, DLG_WIDTH-170, DLG_HEIGHT-40);
	
	    return innerPanel;
	}


	private void connectHandlers() {

		addMediaButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	UploadMediaDlg dlg = new UploadMediaDlg("Upload Image", new IMediaUploadListener() {
					
					@Override
					public void onImageUploaded(String json) {
						UploadInfo uploadInfo = UploadInfo.create(json);
						addNewMedia(uploadInfo);
		    			MediaBrowserDlg.this.hide();
		    			listener.onMediaSelected(uploadInfo.getHref());
					}
				});
	        	dlg.show();
	        }
	    });
	
		cancelButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	MediaBrowserDlg.this.hide();
	        }
	    });
	
	}


	/**
	 * Pobranie zasobów z modelu
	 * @return
	 */
	private void populateGridWithImages() {
		
		int row = 0;
		int col = 0;
		for(int i = 0; i < getCellCount(); i++){
			final BrowserCell cell = createCellWidget(i);
			mediaGrid.setWidget(row, col, cell);
			cell.getImage().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					listener.onMediaSelected(cell.getTitle());
					MediaBrowserDlg.this.hide();
				}
			});
			
			if(col < 2){
				col ++;
			}
			else{
				col = 0;
				row ++;
			}
			
		}
	}
	
	protected IMediaProvider getMediaProvider(){
		return mediaProvider;
	}
	

	protected void addNewMedia(UploadInfo uploadInfo){
	}
	
	
	protected int getCellCount(){
		return 0;
	}

	protected BrowserCell createCellWidget(int i) {
		return null;
	}

}