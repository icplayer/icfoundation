package com.lorepo.icf.widgets.richeditor;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RichTextArea;
import com.lorepo.icf.widgets.mediabrowser.IMediaBrowserListener;
import com.lorepo.icf.widgets.mediabrowser.ImageBrowserDlg;

/**
 * We use an inner EventListener class to avoid exposing event methods on the
 * RichTextToolbar itself.
 */
class EventListener implements ClickHandler, ChangeHandler, KeyUpHandler {

	private final RichTextToolbar richTextToolbar;

	
	public EventListener(RichTextToolbar richTextToolbar) {
		this.richTextToolbar = richTextToolbar;
	}

	
	@Override
	public void onChange(ChangeEvent event) {

		if(richTextToolbar.textFormatter == null){
			return;
		}
		
		Object sender = event.getSource();
		if (sender == this.richTextToolbar.backColors) {
			this.richTextToolbar.textFormatter.setBackColor(this.richTextToolbar.backColors.getValue(this.richTextToolbar.backColors.getSelectedIndex()));
			this.richTextToolbar.backColors.setSelectedIndex(0);
		} else if (sender == this.richTextToolbar.foreColors) {
			this.richTextToolbar.textFormatter.setForeColor(this.richTextToolbar.foreColors.getValue(this.richTextToolbar.foreColors.getSelectedIndex()));
			this.richTextToolbar.foreColors.setSelectedIndex(0);
		} else if (sender == this.richTextToolbar.fonts) {
			this.richTextToolbar.textFormatter.setFontName(this.richTextToolbar.fonts.getValue(this.richTextToolbar.fonts.getSelectedIndex()));
			this.richTextToolbar.fonts.setSelectedIndex(0);
		} else if (sender == this.richTextToolbar.fontSizes) {
			this.richTextToolbar.textFormatter.setFontSize(RichTextToolbar.fontSizesConstants[this.richTextToolbar.fontSizes.getSelectedIndex() - 1]);
			this.richTextToolbar.fontSizes.setSelectedIndex(0);
		}
	}

	
	@Override
	public void onClick(ClickEvent event) {

		if(richTextToolbar.textFormatter == null){
			return;
		}
		
		Object sender = event.getSource();

		if (sender == this.richTextToolbar.bold) {
			this.richTextToolbar.textFormatter.toggleBold();
		} else if (sender == this.richTextToolbar.italic) {
		    this.richTextToolbar.textFormatter.toggleItalic();
		} else if (sender == this.richTextToolbar.underline) {
		    this.richTextToolbar.textFormatter.toggleUnderline();
		} else if (sender == this.richTextToolbar.subscript) {
		    this.richTextToolbar.textFormatter.toggleSubscript();
		} else if (sender == this.richTextToolbar.superscript) {
		    this.richTextToolbar.textFormatter.toggleSuperscript();
		} else if (sender == this.richTextToolbar.strikethrough) {
			this.richTextToolbar.textFormatter.toggleStrikethrough();
		} else if (sender == this.richTextToolbar.indent) {
			this.richTextToolbar.textFormatter.rightIndent();
		} else if (sender == this.richTextToolbar.outdent) {
			this.richTextToolbar.textFormatter.leftIndent();
		} else if (sender == this.richTextToolbar.justifyLeft) {
		    this.richTextToolbar.textFormatter.setJustification(RichTextArea.Justification.LEFT);
		} else if (sender == this.richTextToolbar.justifyCenter) {
		    this.richTextToolbar.textFormatter.setJustification(RichTextArea.Justification.CENTER);
		} else if (sender == this.richTextToolbar.justifyRight) {
		    this.richTextToolbar.textFormatter.setJustification(RichTextArea.Justification.RIGHT);
		} else if (sender == this.richTextToolbar.insertImage) {
		    insertImage();
		} else if (sender == this.richTextToolbar.createLink) {
		    String url = Window.prompt("Enter a link URL:", "http://");
		    if (url != null) {
		    	this.richTextToolbar.textFormatter.createLink(url);
		    }
		} else if (sender == this.richTextToolbar.removeLink) {
			this.richTextToolbar.textFormatter.removeLink();
		} else if (sender == this.richTextToolbar.hr) {
			this.richTextToolbar.textFormatter.insertHorizontalRule();
		} else if (sender == this.richTextToolbar.ol) {
			this.richTextToolbar.textFormatter.insertOrderedList();
		} else if (sender == this.richTextToolbar.ul) {
			this.richTextToolbar.textFormatter.insertUnorderedList();
		} else if (sender == this.richTextToolbar.removeFormat) {
			removeAllFormatting();
		} else if (sender == this.richTextToolbar.richText) {
		    // We use the RichTextArea's onKeyUp event to update the toolbar status.
			// This will catch any cases where the user moves the cursur using the
			// keyboard, or uses one of the browser's built-in keyboard shortcuts.
		    this.richTextToolbar.updateStatus();
		}
	}

	
	private void insertImage() {
		
		if(this.richTextToolbar.imageProvider != null){
			
			IMediaBrowserListener listener = new IMediaBrowserListener() {
				
				@Override
				public void onMediaSelected(String url) {
					EventListener.this.richTextToolbar.textFormatter.insertImage(url);
				}
			};
			ImageBrowserDlg dlg = new ImageBrowserDlg(listener, this.richTextToolbar.imageProvider);
			dlg.show();
		}
		else{
			String url = Window.prompt("Enter an image URL:", "http://");
			if (url != null) {
				this.richTextToolbar.textFormatter.insertImage(url);
			}
		}
	}

	
	private void removeAllFormatting() {
		
		String text = richTextToolbar.getEditor().getText();
		text = text.replaceAll("\\<!--.*\\-->", "");
		richTextToolbar.getEditor().setText(text);
	}


	@Override
	public void onKeyUp(KeyUpEvent event) {

		if (event.getSource() == this.richTextToolbar.richText) {
	        // We use the RichTextArea's onKeyUp event to update the toolbar status.
	        // This will catch any cases where the user moves the cursor using the
	        // keyboard, or uses one of the browser's built-in keyboard shortcuts.
	        this.richTextToolbar.updateStatus();
		}
    }

  }