package com.lorepo.icf.properties.ui.editor;

import com.lorepo.icf.properties.IAudioProperty;
import com.lorepo.icf.properties.IBooleanProperty;
import com.lorepo.icf.properties.IEnumSetProperty;
import com.lorepo.icf.properties.IEventProperty;
import com.lorepo.icf.properties.IFileProperty;
import com.lorepo.icf.properties.IHtmlProperty;
import com.lorepo.icf.properties.IImageProperty;
import com.lorepo.icf.properties.IListProperty;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.properties.IPropertyEditor;
import com.lorepo.icf.properties.IStringListProperty;
import com.lorepo.icf.properties.ITextProperty;
import com.lorepo.icf.properties.ui.IPropertyEditorFactory;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider;

public class PropertyEditorFactory implements IPropertyEditorFactory{

	
	private IMediaProvider mediaProvider;
	
	
	public void setMediaProvider(IMediaProvider imageProvider) {
		this.mediaProvider = imageProvider;
	}

	
	public IMediaProvider getMediaProvider() {
		return mediaProvider;
	}

	
	public IPropertyEditor getPropertyEditor(IProperty property){
		
		if(property instanceof IAudioProperty){
			return new AudioPropertyEditor(property, mediaProvider);
		}
		else if(property instanceof IBooleanProperty){
			return new BooleanPropertyEditor(property);
		}
		else if(property instanceof IEventProperty){
			return new EventPropertyEditor(property);
		}
		else if(property instanceof IImageProperty){
			return new ImagePropertyEditor(property, getMediaProvider());
		}
		else if(property instanceof IListProperty){
			return new ListPropertyEditor((IListProperty)property, mediaProvider);
		}
		else if(property instanceof IEnumSetProperty){
			return new EnumPropertyEditor((IEnumSetProperty) property);
		}
		else if(property instanceof IFileProperty){
			return new FilePropertyEditor(property, mediaProvider);
		}
		else if(property instanceof IHtmlProperty){
			return new HtmlPropertyEditor(property);
		}
		else if(property instanceof IStringListProperty){
			return new StringListPropertyEditor(property);
		}
		else if(property instanceof ITextProperty){
			return new TextPropertyEditor(property);
		}
		
		return new StringPropertyEditor(property);
	}
}
