package com.lorepo.icf.uidesigner;

import com.google.gwt.user.client.ui.HTML;

public class ItemViewFactory<T, M> implements IItemViewFactory<T,M> {

	@Override
	public ItemView<T> getWidget(T item, M calculateMaxScore) {
		
		ItemView<T> widget = new ItemView<T>(item);
		widget.setInnerWidget(new HTML(item.toString()));
		
		return widget;
	}
}
