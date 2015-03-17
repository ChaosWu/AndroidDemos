package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DanceTimer extends Activity {

	private TextView tvMoney;
	private DanceWageTimer danceWageTimer;
	private float mMoney = 70442.89F;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_widget_dance_timer);
		tvMoney = (TextView) findViewById(R.id.tvMoney);
		danceWageTimer = new DanceWageTimer(DanceWageTimer.getTotalExecuteTime(mMoney, 100), 100, tvMoney, mMoney);
		danceWageTimer.start();

	}
}
