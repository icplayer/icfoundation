package com.lorepo.icf.widgets;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.Duration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

public class MessagePopup extends PopupPanel {

	private final boolean shouldHide = true;
	private final int DURATION = 600;
	private final  int HIDE_DELAY  = 2000;

	public MessagePopup(String baloonText) {
		super(true, false);
		setAutoHideEnabled(true);
		setAnimationEnabled(true);
		setStyleName("ic_messagePopup");
        // some sample widget will be content of the balloon
		HTML text = new HTML(baloonText);
		setWidget(text);
		center();
	}

	@Override
	public void show() {

		super.show();

		if (shouldHide)
		{
			BaloonAnimation hideAnim = new BaloonAnimation(false);
            // run hide animation after some time
			hideAnim.run(DURATION, Duration.currentTimeMillis() + HIDE_DELAY);
            // after animation will end, the balloon must be also hidden and deteached from the page
			Timer t = new Timer() {
				@Override
				public void run() {
					MessagePopup.this.hide();
				}
			};
			t.schedule(HIDE_DELAY + DURATION);
		}
	}
       /** animation which will change opacity of the balloon depending on the show value
       * if it will be false: balloon will start to disappear
       * if it will be true: balloon will start to appear
       */
 	class BaloonAnimation extends Animation {
		boolean show = true;

		BaloonAnimation(boolean show) {
			super();
			this.show = show;
		}

		public BaloonAnimation() {
			this(true);
		}

		@Override
		protected void onUpdate(double progress) {
			double opacityValue = progress;
			if (!show) {
				opacityValue = 1.0 - progress;
			}
			MessagePopup.this.getElement().getStyle().setOpacity(
					opacityValue);
		}
	}
}