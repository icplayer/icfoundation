package com.lorepo.icf.properties.ui.editor;

import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.ui.dlg.AbstractEditorDlg;
import com.lorepo.icf.properties.ui.dlg.StringListEditorDlg;

class StringListPropertyEditor extends DlgPropertyEditor{

	
	public StringListPropertyEditor(IProperty property) {
		super(property);
	}

	
	@Override
	protected AbstractEditorDlg createEditor() {
		return new StringListEditorDlg(getProperty());
	}
}
