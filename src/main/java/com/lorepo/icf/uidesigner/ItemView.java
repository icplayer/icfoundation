package com.lorepo.icf.uidesigner;

import com.google.gwt.user.client.Element;
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
	private Widget innerWidget;

	
	public ItemView(T module){
		
		this.model = module;
	}
	
	public void setInnerWidget(Widget innerWidget){
		this.innerWidget = innerWidget;
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

	public void move(int dx, int dy){
		left += dx;
		top += dy;
	}

	public void resize(int dx, int dy){
		width += dx;
		height += dy;
		super.setPixelSize(width, height);
	}
	
	public boolean isLocked(){
		return true;
	}
	
	public Element getInnerElement() {
		return innerWidget.getElement();
	}
}
