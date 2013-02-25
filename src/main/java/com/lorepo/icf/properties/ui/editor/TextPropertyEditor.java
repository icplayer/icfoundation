package com.lorepo.icf.properties.ui.editor;

import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.ui.dlg.AbstractEditorDlg;
import com.lorepo.icf.properties.ui.dlg.TextEditorDlg;

class TextPropertyEditor extends DlgPropertyEditor{

	public TextPropertyEditor(IProperty property) {
		super(property);
	}

	protected AbstractEditorDlg createEditor() {
		return new TextEditorDlg(getProperty());
	}
	
}
