package com.lorepo.icf.widgets;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.DialogBox;

public class KeyboarNavigationBasicDialog extends DialogBox {

    public KeyboarNavigationBasicDialog() {
        super();
    }

    @Override
    protected void onPreviewNativeEvent(NativePreviewEvent event) {
        int eventType = event.getTypeInt();
        if (eventType == Event.ONKEYDOWN) {
            switch (event.getNativeEvent().getKeyCode()) {
                case KeyCodes.KEY_ESCAPE:
                    hide();
                    event.consume();
                    break;

                case KeyCodes.KEY_ENTER:
                case KeyCodes.KEY_TAB:
                    event.consume();
                    break;
            }
        }
    }
}
