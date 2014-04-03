package com.lorepo.icf.properties.ui.dlg;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.utils.i18n.DictionaryWrapper;

/**
 * Edytor kodu eventów
 * @author Krzysztof Langner
 *
 */
public class EventEditorDlg extends AbstractEditorDlg {

	protected static final int	EDITOR_WIDTH = 600;
	protected static final int	EDITOR_HEIGHT = 400;
	private TextArea textArea;
	

	public EventEditorDlg(IProperty property) {

		super(property);

		setText(DictionaryWrapper.get("event_editor"));
	}

	
	@Override
	protected AbsolutePanel createUI() {

		AbsolutePanel	innerPanel = new AbsolutePanel();
		
		innerPanel.setPixelSize(EDITOR_WIDTH, EDITOR_HEIGHT);
		
		saveButton = new Button(DictionaryWrapper.get("save"));
	    innerPanel.add(saveButton);
	    cancelButton = new Button(DictionaryWrapper.get("cancel"));
	    innerPanel.add(cancelButton);
	    
	    // Set widget positions
	    innerPanel.setWidgetPosition(saveButton, EDITOR_WIDTH-140, EDITOR_HEIGHT-40);
	    innerPanel.setWidgetPosition(cancelButton, EDITOR_WIDTH-70, EDITOR_HEIGHT-40);
	    
		textArea = new TextArea();
		textArea.setPixelSize(EDITOR_WIDTH-15, EDITOR_HEIGHT-60);
		textArea.setText(getProperty().getValue());
		textArea.setStyleName("ic_scriptEditor");
		innerPanel.add(textArea);
		
	    // Set widget positions
		innerPanel.setWidgetPosition(textArea, 5, 5);
	    
	    return innerPanel;
	}


	@Override
	public void saveValue(){
		
		getProperty().setValue(textArea.getText());
	}


}