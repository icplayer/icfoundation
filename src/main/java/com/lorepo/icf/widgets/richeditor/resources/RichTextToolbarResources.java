package com.lorepo.icf.widgets.richeditor.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface RichTextToolbarResources extends ClientBundle{
	// I added this line below
    public static final RichTextToolbarResources INSTANCE =  GWT.create(RichTextToolbarResources.class);
 

    /**
     * @gwt.resource bold.gif
     */
    ImageResource bold();

    /**
     * @gwt.resource createLink.gif
     */
    ImageResource createLink();

    /**
     * @gwt.resource hr.gif
     */
    ImageResource hr();

    /**
     * @gwt.resource indent.gif
     */
    ImageResource indent();

    /**
     * @gwt.resource insertImage.gif
     */
    ImageResource insertImage();

    /**
     * @gwt.resource insertAudio.gif
     */
    ImageResource insertAudio();

    /**
     * @gwt.resource italic.gif
     */
    ImageResource italic();

    /**
     * @gwt.resource justifyCenter.gif
     */
    ImageResource justifyCenter();

    /**
     * @gwt.resource justifyLeft.gif
     */
    ImageResource justifyLeft();

    /**
     * @gwt.resource justifyRight.gif
     */
    ImageResource justifyRight();

    /**
     * @gwt.resource ol.gif
     */
    ImageResource ol();

    /**
     * @gwt.resource outdent.gif
     */
    ImageResource outdent();

    /**
     * @gwt.resource removeFormat.gif
     */
    ImageResource removeFormat();

    /**
     * @gwt.resource removeLink.gif
     */
    ImageResource removeLink();

    /**
     * @gwt.resource strikeThrough.gif
     */
    ImageResource strikeThrough();

    /**
     * @gwt.resource subscript.gif
     */
    ImageResource subscript();

    /**
     * @gwt.resource superscript.gif
     */
    ImageResource superscript();

    /**
     * @gwt.resource ul.gif
     */
    ImageResource ul();

    /**
     * @gwt.resource underline.gif
     */
    ImageResource underline();

    /**
     * @gwt.resource foreColors.gif
     */
	ImageResource foreColors();

    /**
     * @gwt.resource backColors.gif
     */
	ImageResource backColors();
	
    /**
     * @gwt.resource hardSpace.png
     */
	ImageResource hardSpace();
}
