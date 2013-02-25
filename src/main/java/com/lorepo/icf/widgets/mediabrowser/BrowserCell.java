package com.lorepo.icf.widgets.mediabrowser;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;

public class BrowserCell extends AbsolutePanel {

	private static final int IMAGE_WIDTH = 150;
	private static final int IMAGE_HEIGHT = 130;
	private static final int LABEL_HEIGHT = 20;

	private Image image;
	private TextBox label;

	
	public BrowserCell(String imageUrl, String title){
		
		image = new Image(imageUrl);
		image.setPixelSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		add(image);
		label = new TextBox();
		label.setText(title);
		label.setPixelSize(IMAGE_WIDTH-10, LABEL_HEIGHT);
		add(label);
		
		setWidgetPosition(image, 0, 0);
		setWidgetPosition(label, 0, IMAGE_HEIGHT+4);
		setPixelSize(IMAGE_WIDTH, IMAGE_WIDTH+LABEL_HEIGHT+8);
	}
	
	public String getTitle(){
		return label.getText();
	}

	public Image getImage() {
		return image;
	}
	
}
