package com.lorepo.icf.properties.ui;

import java.util.HashMap;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.IPropertyEditor;
import com.lorepo.icf.properties.IPropertyListener;
import com.lorepo.icf.properties.IPropertyProvider;
import com.lorepo.icf.properties.ui.editor.PropertyEditorFactory;

public class PropertyGrid extends Composite implements IPropertyListener{

	private IPropertyEditorFactory	propertyEditorFactory;
	private Grid	grid;
	private IPropertyProvider propertyProvider;
	private HashMap<IProperty, IPropertyEditor>	propertyEditors;
	
	
	public PropertyGrid(){

		this(new PropertyEditorFactory());
	}

	
	public PropertyGrid(IPropertyEditorFactory pef){
		
		propertyEditorFactory = pef;
		propertyEditors = new HashMap<IProperty, IPropertyEditor>();
		grid = createGrid(); 
		initWidget(grid);
	}

	
	public void setModule(IPropertyProvider module){
		
		if(propertyProvider != null){
			propertyProvider.removePropertyListener(this);
		}
		
		propertyProvider = module;
		populateGridWithProperties();

		if(propertyProvider != null){
			propertyProvider.addPropertyListener(this);
		}
	}
	
	
	@Override
	public void onPropertyChanged(IProperty property) {

		IPropertyEditor propertyEditor = propertyEditors.get(property);
		if(propertyEditor != null){
			propertyEditor.refresh();
		}
	}


	private Grid createGrid() {
	
		grid = new Grid(1, 2);
		grid.setBorderWidth(0);
		grid.setCellSpacing(2);
		grid.setCellPadding(0);
		grid.setStyleName("ice_propertyGrid");
		
		return grid;
	}


	private void removeGridRowsExceptHeader() {
	
		while(grid.getRowCount() > 0){
			grid.removeRow(0);
		}
	}


	private void populateGridWithProperties() {

		removeGridRowsExceptHeader();
		addPropertiesToGrid(propertyProvider);
	}


	private void addPropertiesToGrid(IPropertyProvider properties) {
		
		if(properties != null){
			
			int lastRow = grid.getRowCount();
			int rowCount = grid.getRowCount() + properties.getPropertyCount();
			grid.resizeRows(rowCount);

			propertyEditors.clear();
			for(int i = 0; i < properties.getPropertyCount(); i++){
	
				IProperty property = properties.getProperty(i);
				
				grid.setHTML(lastRow, 0, property.getName());
				grid.getCellFormatter().setStyleName(lastRow, 0, "ice_propertyGrid-name");
				grid.getCellFormatter().setStyleName(lastRow, 1, "ice_propertyGrid-value");
				
				IPropertyEditor propertyEditor = propertyEditorFactory.getPropertyEditor(property);
				propertyEditors.put(property, propertyEditor);
				Widget editorWidget = propertyEditor.getEditor();
				grid.setWidget(lastRow, 1, editorWidget);
	
				lastRow++;
			}
		}
	}

}
