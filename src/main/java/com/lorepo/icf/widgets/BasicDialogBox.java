package com.lorepo.icf.widgets;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * Klasa bazowa dla dialogbox-ów dodająca kilka podstawowych funkcjonalności
 * @author Krzysztof Langner
 *
 */
public class BasicDialogBox extends DialogBox {

	/**
	 * Obsługa zamykania klawiszem Esc
	 */
	@Override
    protected void onPreviewNativeEvent(NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                }
                break;
        }
    }
}
