package com.lorepo.icf.properties.ui.dlg.list;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
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
import com.lorepo.icf.properties.ui.dlg.list.icons.ItemHeaderResources;
import com.lorepo.icf.utils.JavaScriptUtils;
import com.lorepo.icf.utils.i18n.DictionaryWrapper;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider;
import com.lorepo.icf.widgets.richeditor.RichTextToolbar;

public class ItemsEditorDlg extends AbstractEditorDlg {

	private ItemHeaderResources resource = ItemHeaderResources.INSTANCE;
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
		editorsGrid.setWidth("98%");
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
	
	protected AbsolutePanel createDefaultInnerPanel(int editorWidth, int editorHeight) {
		AbsolutePanel	innerPanel = new AbsolutePanel();
		
		innerPanel.setPixelSize(editorWidth, editorHeight);
		
		saveButton = new Button(DictionaryWrapper.get("close_strong"));
	    innerPanel.add(saveButton);
	    
	    // Set widget positions
	    innerPanel.setWidgetPosition(saveButton, editorWidth-70, editorHeight-40);
		return innerPanel;
	}
	
	protected void connectHandlers() {
		
	    saveButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	saveValue();
	        	ItemsEditorDlg.this.hide();
	        }
	    });
	    
	}	
	

	
	private Widget createItemCountEditor() {
		
		HorizontalPanel panel = new HorizontalPanel();
		Label itemCountLabel = new Label(DictionaryWrapper.get("number_of_items"));
		itemCountTextBox = new TextBox();
		itemCountTextBox.setText("1");
		Button addItemsButton = new Button(DictionaryWrapper.get("add"));
		
		addItemsButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addItems();
			}
		});

		panel.add(itemCountLabel);
		itemCountTextBox.setWidth("50px");
		panel.add(itemCountTextBox);
		panel.add(addItemsButton);
		
		panel.setCellVerticalAlignment(itemCountLabel, VerticalPanel.ALIGN_MIDDLE);
		panel.setSpacing(6);
		
		return panel;
	}


	private void addItems() {
		
		String value = itemCountTextBox.getText();
		int count = Integer.parseInt(value);

		if(count > 0 && count < 100){
			saveValue();
			property.addChildren(count);
			initData();
		}
	}

	private void removeItem(int index) {
		IPropertyProvider provider = property.getChild(index);
		String name = provider.getProviderName() + " " + (index+1);
		String text = DictionaryWrapper.get("delete_item_confirmation") + name;
		if(property.getChildrenCount() > 1 && Window.confirm(text)){
			saveValue();
			property.removeChildren(index);
			initData();
		}
	}

	private void moveItemUp(int index) {
		saveValue();
		property.moveChildUp(index);
		initData();
	}

	private void moveItemDown(int index) {
		saveValue();
		property.moveChildDown(index);
		initData();
	}


	private void initData() {

		long time = System.currentTimeMillis();
		editors.clear();
		editorsGrid.resize(calculateRowCount(), 2);

		int row = 0;
		for(int i = 0; i < property.getChildrenCount(); i++){
			IPropertyProvider provider = property.getChild(i);
			addItemHeader(row, i, provider.getProviderName() + " " + (i+1));
			row++;
			for(int j = 0; j < provider.getPropertyCount(); j++){
				IProperty property = provider.getProperty(j); 
				String displayName = property.getDisplayName();
				if (displayName == null || displayName.length() == 0 ) {
					editorsGrid.setText(row, 0, property.getName());
				} else {
					editorsGrid.setText(row, 0, displayName);
				}
				editorsGrid.setWidget(row, 1, createCellFromPropertyType(property));
				row++;
			}
		}
JavaScriptUtils.log("Init grid time: " + (System.currentTimeMillis()-time));		
	}

	private void addItemHeader(int row, int index, String title) {
		CellFormatter cellFormatter = editorsGrid.getCellFormatter();
		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("ic_cell_header_box");
		Label label = new Label(title);
		label.setStyleName("ic_cell_header_label");
		panel.add(label);
		panel.add(createItemButtons(index));
		panel.setCellVerticalAlignment(label, HasVerticalAlignment.ALIGN_MIDDLE);
		editorsGrid.setWidget(row, 1, panel);
		cellFormatter.setStyleName(row, 0, "ic_cell_header");
		cellFormatter.setStyleName(row, 1, "ic_cell_header");
	}


	private Widget createItemButtons(final int index) {
		FlowPanel panel = new FlowPanel();
		PushButton upButton= new PushButton(new Image(resource.up()));
		upButton.setStyleName("ic_cell_header_button");
		upButton.setPixelSize(16, 16);
		if(index == 0){
			upButton.setEnabled(false);
		}
		PushButton downButton= new PushButton(new Image(resource.down()));
		downButton.setStyleName("ic_cell_header_button");
		downButton.setPixelSize(16, 16);
		if(index == property.getChildrenCount()-1){
			downButton.setEnabled(false);
		}
		PushButton removeButton= new PushButton(new Image(resource.remove()));
		removeButton.setStyleName("ic_cell_header_button");
		removeButton.setPixelSize(16, 16);
		if(property.getChildrenCount() < 2){
			removeButton.setEnabled(false);
		}
		panel.add(upButton);
		panel.add(downButton);
		panel.add(removeButton);
		
		removeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				removeItem(index);
			}
		});
		
		
		upButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				moveItemUp(index);
			}
		});
		
		downButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				moveItemDown(index);
			}
		});
		return panel;
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
		property.setValue(Integer.toString(property.getChildrenCount()));
	}
	
}