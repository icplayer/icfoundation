package com.lorepo.icf.uidesigner;

import com.google.gwt.user.client.ui.HTML;

public class WidgetFactory<T> implements IWidgetFactory<T> {

	@Override
	public DesignerWidget<T> getWidget(T item) {
		
		DesignerWidget<T> widget = new DesignerWidget<T>(item);
		widget.setInnerWidget(new HTML(item.toString()));
		
		return widget;
	}

}
