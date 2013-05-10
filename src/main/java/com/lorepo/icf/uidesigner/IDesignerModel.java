package com.lorepo.icf.uidesigner;

import java.util.List;


/*
 * Contains list of modules which are displayed in UIDesigner
 */
public interface IDesignerModel<T>{

	public interface IListener<T>{

		void onItemChanged(T item);
		void onItemAdded(T item);
		void onItemRemoved(T item);
	}
	
	public void addListener(IListener<T> l);
	public void removeListener(IListener<T> l);
	public int	getItemsCount();
	public T getItem(int index);
	public void createGroup(List<T> group);
	public void removeGroupWithItem(T item);
	public List<T> findGroupByItem(T item);
}
