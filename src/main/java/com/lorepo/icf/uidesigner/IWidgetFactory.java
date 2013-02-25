package com.lorepo.icf.uidesigner;


public interface IWidgetFactory<T> {

	public DesignerWidget<T> getWidget(T item);
}
