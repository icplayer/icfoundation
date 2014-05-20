package com.lorepo.icf.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.lorepo.icf.utils.i18n.DictionaryWrapper;

public class EditValueDialog extends BasicDialogBox {

	private static final int	DLG_WIDTH = 300;
	private static final int	DLG_HEIGHT = 100;

	private String prompt;
	private Button 	saveButton;
	private Button cancelButton;
	private TextBox	textBox;
	
	
	public EditValueDialog(String title, String prompt) {
		
		this.prompt = prompt;
		
		// Set the dialog box's caption.
		setText(title);
		setAnimationEnabled(true);
		setGlassEnabled(true);

		setWidget(createUI());
		connectHandlers();
  
		center();
	    textBox.setFocus(true);
	}

	  
	/**
	 * Create dialogbox UI
	 * @return
	 */
	private Widget createUI() {
	
		AbsolutePanel	innerPanel = new AbsolutePanel();
		innerPanel.setPixelSize(DLG_WIDTH, DLG_HEIGHT);
	
		Label nameLabel = new Label(prompt + ": ");
		innerPanel.add(nameLabel);
		textBox = new TextBox();
		textBox.setWidth("195px");
		innerPanel.add(textBox);
		
	    saveButton = new Button(DictionaryWrapper.get("save_strong"));
		innerPanel.add(saveButton);
	    cancelButton = new Button(DictionaryWrapper.get("cancel"));
	    innerPanel.add(cancelButton);
	    
	    innerPanel.setWidgetPosition(nameLabel, 10, 15);
	    innerPanel.setWidgetPosition(textBox, 65, 10);
	    innerPanel.setWidgetPosition(saveButton, DLG_WIDTH-130, DLG_HEIGHT-40);
	    innerPanel.setWidgetPosition(cancelButton, DLG_WIDTH-70, DLG_HEIGHT-40);
	
	    return innerPanel;
	}


	private void connectHandlers() {

		cancelButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	EditValueDialog.this.hide();
	        }
	    });
	    
		textBox.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
					saveButton.click();
				}
			}
		});
		
	}


	/**
	 * @return saveButton
	 */
	public Button getSaveButton(){
		return saveButton;
	}
	
	
	/**
	 * @return textBox
	 */
	public TextBox getTextBox(){
		return textBox;
	}
}