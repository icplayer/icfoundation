package com.lorepo.icf.widgets.mediabrowser;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class BrowserCell extends AbsolutePanel {

	private static final int IMAGE_WIDTH = 150;
	private static final int IMAGE_HEIGHT = 130;
	private static final int LABEL_HEIGHT = 20;

	private Image image;
	private Label	titleLabel;
	private TextBox hrefEdit;

	
	public BrowserCell(String imageUrl, String title){
		
		titleLabel = new Label(title);
		titleLabel.setPixelSize(IMAGE_WIDTH-10, LABEL_HEIGHT);
		add(titleLabel);
		image = new Image(imageUrl);
		image.setPixelSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		add(image);
		hrefEdit = new TextBox();
		hrefEdit.setText(imageUrl);
		hrefEdit.setPixelSize(IMAGE_WIDTH-10, LABEL_HEIGHT);
		add(hrefEdit);
		
		setWidgetPosition(titleLabel, 0, 0);
		setWidgetPosition(image, 0, LABEL_HEIGHT + 4);
		setWidgetPosition(hrefEdit, 0, IMAGE_HEIGHT+LABEL_HEIGHT+4);
		setPixelSize(IMAGE_WIDTH, IMAGE_WIDTH+2*LABEL_HEIGHT+8);
	}
	
	public String getTitle(){
		return hrefEdit.getText();
	}

	public Image getImage() {
		return image;
	}
	
}
