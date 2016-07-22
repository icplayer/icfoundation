package com.lorepo.icf.uidesigner;


public interface IItemViewFactory<T> {

	public ItemView<T> getWidget(T item);
}
