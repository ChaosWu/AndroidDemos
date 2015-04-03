package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import cn.android.demo.apis.ui.widget.DetechTouchView.DetechChangeListener;
import android.app.Activity;
import android.os.Bundle;

public class DetechTouchActivity extends Activity {
	DetechTouchView detechTouchView;
//	MaskView myMask;
	static InfoView infoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_widget_detech_touch);

		detechTouchView = (DetechTouchView) findViewById(R.id.detech_touch_android);
		infoView = (InfoView) findViewById(R.id.detech_touch_infoview);
		//myMask = (MaskView) findViewById(R.id.detech_touch_mask);
	}

	public static void notifyDetech(String touchInfo, float tX, float tY,
			int maskColor) {
		infoView.updateInfo(touchInfo, tX, tY, maskColor);
	}

//	public long getMaskColor(float x, float y) {
//		return myMask.getColor(x, y);
//	}
}
