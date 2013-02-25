package com.lorepo.icf.properties.ui;

import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.IPropertyEditor;

public interface IPropertyEditorFactory {

	public IPropertyEditor getPropertyEditor(IProperty property);
}
