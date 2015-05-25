package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import cn.android.demo.apis.ui.widget.SpotView;
import cn.android.demo.apis.ui.widget.SpotView.SpotCallBack;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AndroidCountDownTimer extends Activity implements SpotCallBack {
	TextView mState;
	TextView mCounter;

	Button start;
	SpotView spotView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_count_down_timer);
		start = (Button) findViewById(R.id.app_count_down_timer_start);

		mState = (TextView) findViewById(R.id.app_count_down_timer_mystate);
		mCounter = (TextView) findViewById(R.id.app_count_down_timer_mycounter);
		spotView = (SpotView) findViewById(R.id.app_count_down_timer_myspotview);
		spotView.setCallback(this);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				spotView.startRun();
			}
		});

	}

	@Override
	public void onTick(String msg) {
		mCounter.setText(msg);
	}

	@Override
	public void onFinish(String msg) {
		mState.setText(msg);
	}
}
