package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

/**
 * 
 * ELAPSED_REALTIME > 在指定的延时过后，发送广播，但不唤醒设备。
 * 
 * ELAPSED_REALTIME_WAKEUP > 在指定的演示后，发送广播，并唤醒设备
 * 
 * RTC > 在指定的时刻，发送广播，但不唤醒设备
 * 
 * RTC_WAKEUP > 在指定的时刻，发送广播，并唤醒设备
 * 
 * @author Elroy
 * 
 */
public class AndroidChronometer extends Activity implements OnClickListener {

	private Button btStart;
	private Button btStop;
	private Button btReset;
	private Chronometer myChronometer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_android_chronometer);

		myChronometer = (Chronometer) findViewById(R.id.tv_chronometer_time);
		myChronometer
				.setOnChronometerTickListener(new OnChronometerTickListener() {

					@Override
					public void onChronometerTick(Chronometer chronometer) {
						long myElapsedMillis = SystemClock.elapsedRealtime()
								- chronometer.getBase();

						String strElapsedMills = "Elapsed milliseconds: "
								+ myElapsedMillis;
						ToastUtil.showToast(AndroidChronometer.this, strElapsedMills);
					}
				});
		btStart = (Button) findViewById(R.id.bt_start_time);
		btStop = (Button) findViewById(R.id.bt_stop_time);
		btReset = (Button) findViewById(R.id.bt_reset_time);

		btStart.setOnClickListener(this);
		btStop.setOnClickListener(this);
		btReset.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_start_time:
			myChronometer.start();
			break;
		case R.id.bt_stop_time:
			myChronometer.stop();
			break;
		case R.id.bt_reset_time:
			myChronometer.setBase(SystemClock.elapsedRealtime());
			break;

		default:
			break;
		}
	}
}
