package com.lorepo.icf.properties.ui.dlg;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.utils.i18n.DictionaryWrapper;

public abstract class AbstractEditorDlg extends DialogBox {

	protected static final int	EDITOR_WIDTH = 800;
	protected static final int	EDITOR_HEIGHT = 600;

	protected IProperty property;
	protected Button 	saveButton;
	protected Button 	closeButton;
	

	public AbstractEditorDlg() {
	}

	
	public AbstractEditorDlg(IProperty property) {

		this.property = property;
		
		setAnimationEnabled(true);
		setGlassEnabled(true);

		setWidget(createUI());
		connectHandlers();
		
		center();
	}

	
	/**
	 * Create dialogbox UI
	 * @return
	 */
	protected AbsolutePanel createUI() {
	
		AbsolutePanel innerPanel = createDefaultInnerPanel(EDITOR_WIDTH, EDITOR_HEIGHT);
	    return innerPanel;
	}


	protected AbsolutePanel createDefaultInnerPanel(int editorWidth, int editorHeight) {
		AbsolutePanel	innerPanel = new AbsolutePanel();
		
		innerPanel.setPixelSize(editorWidth, editorHeight);
		
		saveButton = new Button(DictionaryWrapper.get("save"));
	    innerPanel.add(saveButton);
	    closeButton = new Button(DictionaryWrapper.get("cancel"));
	    innerPanel.add(closeButton);
	    
	    // Set widget positions
	    innerPanel.setWidgetPosition(saveButton, editorWidth-140, editorHeight-40);
	    innerPanel.setWidgetPosition(closeButton, editorWidth-70, editorHeight-40);
		return innerPanel;
	}


	protected void connectHandlers() {
		
	    closeButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	AbstractEditorDlg.this.hide();
	        }
	    });
	    
	    saveButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	saveValue();
	        	AbstractEditorDlg.this.hide();
	        }
	    });
	    
	}
	
	
	public void saveValue(){
	}

	
	public IProperty getProperty(){
		return property;
	}
	
	
	@Override
    protected void onPreviewNativeEvent(NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                }
                break;
        }
    }

}