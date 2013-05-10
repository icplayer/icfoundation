package com.lorepo.icf.uidesigner;

import java.util.ArrayList;
import java.util.List;


public class DesignerModel<T> implements IDesignerModel<T>{

	private List<IListener<T>>	listeners = new ArrayList<IListener<T>>();
	private ArrayList<T> items = new ArrayList<T>(); 

	
	@Override
	public void addListener(IListener<T> l){
		listeners.add(l);
	}
	
	
	@Override
	public void removeListener(IListener<T> l){
		listeners.remove(l);
	}
	
	
	public void add(T module){
		
		items.add(module);
		
		for(IListener<T> l : listeners){
			l.onItemAdded(module);
		}
	}

	
	public T remove(int index){
		
		T module = items.remove(index);
		
		for(IListener<T> l : listeners){
			l.onItemRemoved(module);
		}
		
		return module;
	}


	public boolean remove(Object page){
		
		int index = items.indexOf(page);
		
		if(index >= 0){
			remove(index);
			return true;
		}
		
		return false;
	}


	@Override
	public int getItemsCount() {
		return items.size();
	}


	@Override
	public T getItem(int index) {
		return items.get(index);
	}


	@Override
	public void createGroup(List<T> group) {
	}


	@Override
	public void removeGroupWithItem(T item) {
	}


	@Override
	public List<T> findGroupByItem(T item) {
		return null;
	}

}
