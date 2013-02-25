package com.lorepo.icf.properties.ui.dlg.list;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.RichTextArea;
import com.lorepo.icf.properties.IHtmlProperty;
import com.lorepo.icf.widgets.richeditor.RichTextToolbar;

public class HTMLPropertyCell extends RichTextArea implements IItemCellEditor{

	private IHtmlProperty property;
	private RichTextToolbar toolbar;
	
	
	public HTMLPropertyCell(IHtmlProperty property, RichTextToolbar toolbar){
	
		this.property = property;
		this.toolbar = toolbar;
		setHTML(property.getValue());
		setWidth("100%");
		setHeight("4em");

		connectHandlers();
	}


	private void connectHandlers() {

		addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				onFocusReceived();
			}
		});
		
	}


	protected void onFocusReceived() {
		toolbar.setRichTextWidget(this);
	}


	@Override
	public void save() {
		property.setValue(getHTML());
	}
}
