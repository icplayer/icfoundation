package com.lorepo.icf.properties.ui.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.IPropertyEditor;
import com.lorepo.icf.utils.StringUtils;

/**
 * Dialog box property editor
 * 
 * @author Krzysztof Langner
 *
 */
public class DlgPropertyEditor implements IPropertyEditor{

	private final IProperty 	property;
	private Button	editorButton;
	private Label label;
	
	
	public DlgPropertyEditor(IProperty property) {
		
		this.property = property;
	}

	
	@Override
	public Widget getEditor(){

		
		label = new Label();
		label.setStyleName("ice_propertyGrid-dlg-label");
		label.setWidth("76%");
		editorButton = new Button();
		editorButton.setText("...");
		editorButton.setSize("20%", "100%");
		
		
		FlowPanel panel = new FlowPanel();
		panel.add(label);
		panel.add(editorButton);
		
		updateEditorText();

		editorButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showEditorDlg();
			}
		});
		
		return panel;
	}

	
	public IProperty getProperty(){
		return property;
	}
	
	
	private void updateEditorText() {

		String strippedText = StringUtils.removeAllFormatting(property.getValue());
		if(strippedText.length() > 30){
			strippedText = strippedText.substring(0, 30) + "...";
		}
		else if(strippedText.isEmpty()){
			strippedText = " ";
		}
		label.setText("");
		TextBox txtBox = new TextBox();
		txtBox.setValue(strippedText);
		txtBox.setReadOnly(true);
		label.asWidget().getElement().appendChild(txtBox.getElement());
	}


	private void showEditorDlg(){
		
		DialogBox editor = createEditor();
		editor.show();
	}


	protected DialogBox createEditor() {
		throw null;
	}
	
	@Override
	public void refresh() {

		if(editorButton != null){
			updateEditorText();
		}
	}
	
}
