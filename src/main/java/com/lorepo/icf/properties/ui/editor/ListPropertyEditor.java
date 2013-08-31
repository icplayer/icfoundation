package com.lorepo.icf.properties.ui.editor;

import com.lorepo.icf.properties.IListProperty;
import com.lorepo.icf.properties.ui.dlg.AbstractEditorDlg;
import com.lorepo.icf.properties.ui.dlg.list.ItemsEditorDlg;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider;

class ListPropertyEditor extends DlgPropertyEditor{

	private final IMediaProvider imageProvider;
	
	public ListPropertyEditor(IListProperty property, IMediaProvider imageProvider) {
		super(property);
		
		this.imageProvider = imageProvider;
	}

	
	protected AbstractEditorDlg createEditor() {
		
		ItemsEditorDlg dlg = new ItemsEditorDlg((IListProperty)getProperty(), imageProvider);
		return dlg;
	}
	
}
