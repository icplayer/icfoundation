package com.lorepo.icf.properties.ui.editor;

import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.ui.dlg.AbstractEditorDlg;
import com.lorepo.icf.properties.ui.dlg.HtmlEditorDlg;

class HtmlPropertyEditor extends DlgPropertyEditor{

	public HtmlPropertyEditor(IProperty property) {
		super(property);
	}

	protected AbstractEditorDlg createEditor() {
		return new HtmlEditorDlg(getProperty());
	}
	
}
