package com.lorepo.icf.properties.ui.dlg;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.utils.i18n.DictionaryWrapper;

/**
 * Edytor z wielolinijkowym polem tekstowym
 * @author Krzysztof Langner
 *
 */
public class TextEditorDlg extends AbstractEditorDlg {

	private TextArea textArea;
	

	public TextEditorDlg(IProperty property) {

		super(property);

		setText(DictionaryWrapper.get("text_editor"));
	}

	
	@Override
	protected AbsolutePanel createUI() {

		AbsolutePanel	innerPanel = super.createUI();
		
		textArea = new TextArea();
		textArea.setPixelSize(EDITOR_WIDTH-15, EDITOR_HEIGHT-60);
		textArea.setText(getProperty().getValue());
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