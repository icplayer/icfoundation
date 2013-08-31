package com.lorepo.icf.properties.ui.dlg.list;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lorepo.icf.properties.IBooleanProperty;
import com.lorepo.icf.properties.IEventProperty;
import com.lorepo.icf.properties.IFileProperty;
import com.lorepo.icf.properties.IHtmlProperty;
import com.lorepo.icf.properties.IImageProperty;
import com.lorepo.icf.properties.IListProperty;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.IPropertyProvider;
import com.lorepo.icf.properties.ITextProperty;
import com.lorepo.icf.properties.ui.dlg.AbstractEditorDlg;
import com.lorepo.icf.utils.i18n.DictionaryWrapper;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider;
import com.lorepo.icf.widgets.richeditor.RichTextToolbar;

public class ItemsEditorDlg extends AbstractEditorDlg {

	private final IListProperty property;
	private final IMediaProvider mediaProvider;
	private RichTextToolbar toolbar;
	private Grid editorsGrid;
	private TextBox itemCountTextBox;
	private List<IItemCellEditor>	editors = new ArrayList<IItemCellEditor>();
	

	public ItemsEditorDlg(IListProperty property, IMediaProvider mediaProvider) {

		this.property = property;
		this.mediaProvider = mediaProvider;
		setAnimationEnabled(true);
		setGlassEnabled(true);

		setWidget(createUI());
		connectHandlers();
		initData();
		setText(DictionaryWrapper.get("items_editor"));
		center();

	}

	@Override
	protected AbsolutePanel createUI() {

		AbsolutePanel	innerPanel = super.createUI();
		
		toolbar = new RichTextToolbar();
		toolbar.setPixelSize(EDITOR_WIDTH-10, 70);
		toolbar.setImageProvider(mediaProvider);
		innerPanel.add(toolbar);

		editorsGrid = new Grid(2,2);
		editorsGrid.setWidth("95%");
		editorsGrid.setStyleName("ic_itemEditorGrid");
		ScrollPanel scroll = new ScrollPanel();
		scroll.add(editorsGrid);
		scroll.setPixelSize(EDITOR_WIDTH-10, EDITOR_HEIGHT-130);
		innerPanel.add(scroll);
		
		Widget countEditor = createItemCountEditor();
		innerPanel.add(countEditor);

		// Set widget positions
		innerPanel.setWidgetPosition(toolbar, 5, 5);
		innerPanel.setWidgetPosition(scroll, 5, 75);
		innerPanel.setWidgetPosition(countEditor, 5, EDITOR_HEIGHT-40);
	    
	    return innerPanel;
	}

	
	private Widget createItemCountEditor() {
		
		HorizontalPanel panel = new HorizontalPanel();
		Label itemCountLabel = new Label(DictionaryWrapper.get("item_count"));
		itemCountTextBox = new TextBox();
		Button changeButton = new Button(DictionaryWrapper.get("change"));
		
		changeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				changeItemCount();
			}
		});

		panel.add(itemCountLabel);
		itemCountTextBox.setText(property.getValue());
		itemCountTextBox.setWidth("50px");
		panel.add(itemCountTextBox);
		panel.add(changeButton);
		
		panel.setCellVerticalAlignment(itemCountLabel, VerticalPanel.ALIGN_MIDDLE);
		panel.setSpacing(6);
		
		return panel;
	}


	protected void changeItemCount() {
		
		String value = itemCountTextBox.getText();
		int count = Integer.parseInt(value);

		if(count > 0 && count < 100){
			saveValue();
			property.setChildrenCount(count);
			initData();
		}
	}


	private void initData() {

		editors.clear();
		editorsGrid.resize(calculateRowCount(), 2);

		int row = 0;
		for(int i = 0; i < property.getChildrenCount(); i++){
			IPropertyProvider provider = property.getChild(i);
			editorsGrid.setText(row, 1, provider.getProviderName() + " " + (i+1));
			row++;
			for(int j = 0; j < provider.getPropertyCount(); j++){
				IProperty property = provider.getProperty(j); 
				editorsGrid.setText(row, 0, property.getName());
				editorsGrid.setWidget(row, 1, createCellFromPropertyType(property));
				row++;
			}
		}
	}


	private int calculateRowCount() {

		int count = 0;
		for(int i = 0; i < property.getChildrenCount(); i++){
			IPropertyProvider provider = property.getChild(i);
			count += provider.getPropertyCount()+1;
		}
		
		return count;
	}


	private Widget createCellFromPropertyType(IProperty property){
		
		Widget widget;
		
		if(property instanceof IHtmlProperty){
			widget = new HTMLPropertyCell((IHtmlProperty) property, toolbar);
		}
		else if(property instanceof IBooleanProperty){
			widget = new BooleanPropertyCell((IBooleanProperty) property);
		}
		else if(property instanceof IEventProperty){
			widget = new EventPropertyCell((IEventProperty) property);
		}
		else if(property instanceof IImageProperty){
			widget = new MediaPropertyCell((IImageProperty) property, mediaProvider);
		}
		else if(property instanceof IFileProperty){
			widget = new FilePropertyCell((IFileProperty) property, mediaProvider);
		}
		else if(property instanceof ITextProperty){
			widget = new TextPropertyCell((ITextProperty) property);
		}
		else{
			widget = new StringPropertyCell(property, toolbar);
		}

		if(widget instanceof IItemCellEditor){
			editors.add((IItemCellEditor) widget);
		}
		
		return widget;
	}
	

	@Override
	public void saveValue(){
		
		for(IItemCellEditor editor : editors){
			editor.save();
		}
		
		// Wymuszenie odswiezenie property listy
		property.setValue(Integer.toString(property.getChildrenCount()));
	}
	
}