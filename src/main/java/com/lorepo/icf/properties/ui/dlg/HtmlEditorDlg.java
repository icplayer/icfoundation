package com.lorepo.icf.properties.ui.dlg;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.utils.i18n.DictionaryWrapper;
import com.lorepo.icf.widgets.richeditor.RichTextToolbar;

public class HtmlEditorDlg extends AbstractEditorDlg {

	private RichTextArea textArea;
	

	public HtmlEditorDlg(IProperty property) {

		super(property);

		setText(DictionaryWrapper.get("html_editor"));
	}

	
	@Override
	protected AbsolutePanel createUI() {

		AbsolutePanel	innerPanel = super.createUI();
		
		textArea = new RichTextArea();
		textArea.setPixelSize(EDITOR_WIDTH-15, EDITOR_HEIGHT-130);
		textArea.setHTML(getProperty().getValue());
		innerPanel.add(textArea);
		RichTextToolbar toolbar = new RichTextToolbar();
		toolbar.setRichTextWidget(textArea);
		toolbar.setPixelSize(EDITOR_WIDTH-10, 70);
		innerPanel.add(toolbar);

		
	    // Set widget positions
		innerPanel.setWidgetPosition(toolbar, 5, 5);
		innerPanel.setWidgetPosition(textArea, 5, 75);
	    
	    return innerPanel;
	}


	@Override
	public void saveValue(){
		getProperty().setValue(textArea.getHTML());
	}


}