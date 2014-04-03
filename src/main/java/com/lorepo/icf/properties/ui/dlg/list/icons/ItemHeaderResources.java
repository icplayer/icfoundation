package com.lorepo.icf.properties.ui.dlg.list.icons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ItemHeaderResources extends ClientBundle{
	// I added this line below
    public static final ItemHeaderResources INSTANCE =  GWT.create(ItemHeaderResources.class);
 
    ImageResource up();
    ImageResource down();
    ImageResource remove();
}
