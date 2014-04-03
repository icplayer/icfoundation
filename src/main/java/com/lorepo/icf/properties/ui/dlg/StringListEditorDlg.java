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
public class StringListEditorDlg extends AbstractEditorDlg {

	protected static final int	EDITOR_WIDTH = 600;
	protected static final int	EDITOR_HEIGHT = 400;
	private TextArea textArea;
	

	public StringListEditorDlg(IProperty property) {

		super(property);

		setText(DictionaryWrapper.get("string_list_editor"));
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
		textArea.setText(getPropertyText());
		textArea.setStyleName("ic_scriptEditor");
		innerPanel.add(textArea);
		
	    // Set widget positions
		innerPanel.setWidgetPosition(textArea, 5, 5);
	    
	    return innerPanel;
	}


	private String getPropertyText() {
		
		String text = getProperty().getValue(); 
		return text; 
	}


	@Override
	public void saveValue(){
		
		String text = textArea.getText();
		getProperty().setValue(text);
	}


}