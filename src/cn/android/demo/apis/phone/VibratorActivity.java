package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

/**
 * 振动器
 * 
 * @author Elroy
 * 
 */
public class VibratorActivity extends Activity {
	Button bt1;
	Button bt2;
	Button bt3;

	Vibrator vibrator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_vibrator_activity);
		bt1 = (Button) findViewById(R.id.bt_vibrate1);
		bt2 = (Button) findViewById(R.id.bt_vibrate2);
		bt3 = (Button) findViewById(R.id.bt_vibrate3);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				vibrator.vibrate(500);
			}
		});

		bt2.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				int action = event.getAction();
				if (action == MotionEvent.ACTION_DOWN) {
					vibrator.vibrate(1000);
				} else {
					vibrator.cancel();
				}

				return true;
			}
		});

		bt3.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				int action = event.getAction();

				if (action == MotionEvent.ACTION_DOWN) {
					long[] pattern = { 50, // Off before vibration
							100, 100, // on-off
							100, 1000 // on-off
					};
					vibrator.vibrate(pattern, 0);// repeat from pattern[0]
				} else {
					vibrator.cancel();
				}

				return true;
			}
		});
	}
}
