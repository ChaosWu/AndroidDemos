package cn.android.demo.apis.ui.widget;

import android.app.Activity;
import android.os.Bundle;

public class MultisTouch extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new cn.android.demo.apis.ui.widget.MultiTouchsView(this));
	}
}
