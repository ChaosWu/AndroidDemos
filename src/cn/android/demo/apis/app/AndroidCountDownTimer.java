package cn.android.demo.apis.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class AndroidCountDownTimer extends Activity {
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		setContentView(textView);

		new CountDownTimer(30000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				textView.setText("Millisecond Until Finished: "
						+ String.valueOf(millisUntilFinished/1000));
			}

			@Override
			public void onFinish() {
				textView.setText("onFinish");
			}
		}.start();
	}
}
