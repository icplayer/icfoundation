package com.lorepo.icf.properties.ui.editor;

import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.ui.dlg.AbstractEditorDlg;
import com.lorepo.icf.properties.ui.dlg.EventEditorDlg;

class EventPropertyEditor extends DlgPropertyEditor{

	public EventPropertyEditor(IProperty property) {
		super(property);
	}

	protected AbstractEditorDlg createEditor() {
		return new EventEditorDlg(getProperty());
	}
	
}
