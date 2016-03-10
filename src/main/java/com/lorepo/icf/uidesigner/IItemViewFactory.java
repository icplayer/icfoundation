package com.lorepo.icf.uidesigner;


public interface IItemViewFactory<T, M> {

	public ItemView<T> getWidget(T item, M calculateMaxScore);
}
