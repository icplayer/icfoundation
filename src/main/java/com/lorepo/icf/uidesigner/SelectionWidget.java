package com.lorepo.icf.uidesigner;

import java.util.ArrayList;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.lorepo.icf.utils.StringUtils;

/**
 * Selection box. Can select multiple modules.
 * Contains widget to move and resize selection.
 * 
 * @author Krzysztof Langner
 */
public class SelectionWidget<T> extends AbsolutePanel {

	enum WorkMode{
		none,
		move,
		resize,
	};

	private final static int RESIZE_AREA = 15;

	private WorkMode workMode;
	private int left;
	private int top;
	private ArrayList<ItemView<T>> currentSelection = new ArrayList<ItemView<T>>();
	private AbsolutePanel resizeWidget;
	private HTML 	infoWidget;
	private int gridSize;
	private AbsolutePanel parentPanel;
	
	
	public SelectionWidget(AbsolutePanel parentPanel){
	
		this.parentPanel = parentPanel;
		setStyleName("ic_selectionWidget");
		createResizeWidgets();
		createInfoWidget();
		setVisible(false);
	}


	private void createResizeWidgets() {
		resizeWidget = new AbsolutePanel();
		resizeWidget.setPixelSize(RESIZE_AREA, RESIZE_AREA);
		resizeWidget.setStyleName("ic_resizeWidget");
		add(resizeWidget);
	}

	
	private void createInfoWidget() {
		infoWidget = new HTML();
		infoWidget.setStyleName("ic_resizeInfoWidget");
		infoWidget.setVisible(false);
		add(infoWidget, 0, 0);
	}

	
	public boolean startMoving(int x, int y){
		
		if(isInResizeBox(x, y)){
			workMode = WorkMode.resize;
			infoWidget.setVisible(true);
		}
		else if(isIn(x, y)){
			workMode = WorkMode.move;
			infoWidget.setVisible(true);
		}
		else{
			return false;
		}
		
		return true;
	}
	
	
	private boolean isIn(int x, int y) {
		
		if(isVisible()){
		
			int right = getAbsoluteLeft() + getOffsetWidth();
			int bottom = getAbsoluteTop() + getOffsetHeight();
			if(x > getAbsoluteLeft() && y > getAbsoluteTop() && x < right && y < bottom){
				return true;
			}
		}
		
		return false;
	}


	private boolean isInResizeBox(int x, int y) {
		
		if(resizeWidget.isVisible()){
		
			int right = resizeWidget.getAbsoluteLeft() + resizeWidget.getOffsetWidth();
			int bottom = resizeWidget.getAbsoluteTop() + resizeWidget.getOffsetHeight();
			if(x > resizeWidget.getAbsoluteLeft() && y > resizeWidget.getAbsoluteTop() && 
					x < right && y < bottom)
			{
				return true;
			}
		}
		
		return false;
	}


	public void clear() {
		currentSelection.clear();
		setVisible(false);
	}


	public void addToSelection(ItemView<T> widget) {
		currentSelection.add(widget);
		updateSize();
		setVisible(true);
		resizeWidget.setVisible(currentSelection.size() == 1);
	}


	public void updateSize() {

		int right = 0;
		int bottom = 0;

		left = Integer.MAX_VALUE;
		top = Integer.MAX_VALUE;
		for(ItemView<T> widget : currentSelection){
				
			int widgetLeft = widget.getAbsoluteLeft()-parentPanel.getAbsoluteLeft();
			if(left > widgetLeft){
				left = widgetLeft;
			}
			
			int widgetTop = widget.getAbsoluteTop()-parentPanel.getAbsoluteTop();
			if(top > widgetTop){
				top = widgetTop;
			}

			int widgetRight = widgetLeft + widget.getOffsetWidth();
			if(right < widgetRight){
				right = widgetRight;
			}

			int widgetBottom = widgetTop + widget.getOffsetHeight();
			if(bottom < widgetBottom){
				bottom = widgetBottom;
			}
		}
		
		int width = right - left;
		int height = bottom - top;
		if(width > 0 && height > 0){
			setPixelSize(right-left, bottom-top);
			setWidgetPosition(resizeWidget, width-RESIZE_AREA, height-RESIZE_AREA);
		}
	}


	public int getLeft() {
		return left;
	}


	public int getTop() {
		return top;
	}


	public String getCursorType(int clientX, int clientY) {

		if(isInResizeBox(clientX, clientY)){
			return "se-resize";
		}
		else if(isIn(clientX, clientY)){
			return "move";
		}
		
		return null;
	}


	public void onMouseMove(int dx, int dy) {

		if(workMode == WorkMode.move){
			moveSelectedWidgets(dx, dy);
		}
		else{
			resizeSelectedWidget(dx, dy);
		}
		
		updateInfoWidget();
	}
	
	
	private void moveSelectedWidgets(int dx, int dy) {

		for(ItemView<T> widget : currentSelection){
			
			Element element = widget.getElement();
			int left = StringUtils.px2int(DOM.getStyleAttribute(element, "left")) + dx;
			int top = StringUtils.px2int(DOM.getStyleAttribute(element, "top")) + dy;
			
			DOM.setStyleAttribute(element, "left", left + "px");
		    DOM.setStyleAttribute(element, "top", top + "px");
		    widget.move(dx, dy);
//			widget.setLeft(left);
//			widget.setTop(top);
		}

		if(currentSelection.size() > 0){
			Element element = getElement();
			int left = StringUtils.px2int(DOM.getStyleAttribute(element, "left")) + dx;
			int top = StringUtils.px2int(DOM.getStyleAttribute(element, "top")) + dy;
			DOM.setStyleAttribute(element, "left", left + "px");
		    DOM.setStyleAttribute(element, "top", top + "px");
		}
	}

	private void resizeSelectedWidget(int dx, int dy) {

		if(currentSelection.size() == 1){
			ItemView<T> widget = currentSelection.get(0);
			widget.resize(dx, dy);
			resize(dx, dy);
		}
	}


	private void resize(int dx, int dy) {

		int width = StringUtils.px2int(DOM.getStyleAttribute(getElement(), "width")) + dx;
		int height = StringUtils.px2int(DOM.getStyleAttribute(getElement(), "height")) + dy;
		setPixelSize(width, height);

		moveResizeWidget(dx, dy);
	}


	private void moveResizeWidget(int dx, int dy) {
		Element element = resizeWidget.getElement();
		int left = StringUtils.px2int(DOM.getStyleAttribute(element, "left")) + dx;
		int top = StringUtils.px2int(DOM.getStyleAttribute(element, "top")) + dy;
		
		DOM.setStyleAttribute(element, "left", left + "px");
	    DOM.setStyleAttribute(element, "top", top + "px");
	}


	public void stopMoving() {
		
		if(gridSize > 1){
			if(workMode == WorkMode.move){
				snapPositionToGrid();
			}
			else{
				snapSizeToGrid();
			}
		}

		workMode = WorkMode.none;
		infoWidget.setVisible(false);
	}

	
	private void snapSizeToGrid() {
		if(currentSelection.size() == 1){
			ItemView<T> widget = currentSelection.get(0);
			Element element = widget.getElement();
			int width = StringUtils.px2int(DOM.getStyleAttribute(element, "width"));
			int height = StringUtils.px2int(DOM.getStyleAttribute(element, "height"));
			int dx = - width % gridSize;
			int dy = - height % gridSize;
		
			widget.resize(dx, dy);			
		}
		
	}


	private void snapPositionToGrid() {
		for(ItemView<T> widget : currentSelection){
			Element element = widget.getElement();
			int left = StringUtils.px2int(DOM.getStyleAttribute(element, "left"));
			int top = StringUtils.px2int(DOM.getStyleAttribute(element, "top"));
			int dx = -left % gridSize;
			int dy = -top % gridSize;
			left = left - left % gridSize;
			top = top - top % gridSize;
			
			DOM.setStyleAttribute(element, "left", left + "px");
		    DOM.setStyleAttribute(element, "top", top + "px");
		    widget.move(dx, dy);
//			widget.setLeft(left);
//			widget.setTop(top);
		}
	}


	private void updateInfoWidget(){
		
		int left = StringUtils.px2int(DOM.getStyleAttribute(getElement(), "left"));
		int top = StringUtils.px2int(DOM.getStyleAttribute(getElement(), "top"));
		int width = StringUtils.px2int(DOM.getStyleAttribute(getElement(), "width"));
		int height = StringUtils.px2int(DOM.getStyleAttribute(getElement(), "height"));
		int right = left + width;
		int bottom = top + height;
		
		String text = "(" + left + "," + top + ") (" + right +"," + bottom + ")";
		infoWidget.setText(text);
		updateInfoWidgetPosition();
	}
	
	
	private void updateInfoWidgetPosition() {
		
		int top = getOffsetHeight()-infoWidget.getOffsetHeight();
		
		setWidgetPosition(infoWidget, 0, top);
	}


	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}

}
