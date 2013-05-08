package com.lorepo.icf.uidesigner;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget which represents module of type T in designer.
 * 
 * @author Krzysztof Langner
 */
public class ItemView<T> extends Composite{

	private T model;
	private int	left;
	private int	top;
	private int	width;
	private int	height;

	
	public ItemView(T module){
		
		this.model = module;
	}
	
	public void setInnerWidget(Widget innerWidget){
		
		initWidget(innerWidget);
	}
	
	public T getModel(){
		return model;
	}
	
	
	public int getLeft() {
		return left;
	}

	public int getTop() {
		return top;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setSize(int width, int height){
		super.setPixelSize(width, height);
		setWidth(width);
		setHeight(height);
	}
}
