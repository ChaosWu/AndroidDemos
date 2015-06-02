package cn.android.demo.apis.app;

import java.util.Calendar;

import cn.android.demo.apis.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class AlarmBroadcastReceierActivity extends Activity {
	Chronometer chronometer;
	Button btnSetNoCheck, btnSetWithVerCheck;
	final static int RQS_1 = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_android_alarm_broadcast_receier);

		chronometer = (Chronometer) findViewById(R.id.alarm_broadcast_receier_chronometer);
		btnSetNoCheck = (Button) findViewById(R.id.alarm_broadcast_receier_setnocheck);
		btnSetNoCheck.setOnClickListener(onClickListener);
		btnSetWithVerCheck = (Button) findViewById(R.id.alarm_broadcast_receier_setwithversioncheck);
		btnSetWithVerCheck.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@TargetApi(Build.VERSION_CODES.KITKAT)
		@Override
		public void onClick(View v) {
			chronometer.setBase(SystemClock.elapsedRealtime());
			chronometer.start();

			// 10 seconds later
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, 10);

			Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					getBaseContext(), RQS_1, intent,
					PendingIntent.FLAG_ONE_SHOT);
			AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

			if (v == btnSetNoCheck) {
				alarmManager.set(AlarmManager.RTC_WAKEUP,
						cal.getTimeInMillis(), pendingIntent);
				Toast.makeText(getBaseContext(), "call alarmManager.set()",
						Toast.LENGTH_LONG).show();
			} else {
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
					alarmManager.set(AlarmManager.RTC_WAKEUP,
							cal.getTimeInMillis(), pendingIntent);
					Toast.makeText(getBaseContext(), "call alarmManager.set()",
							Toast.LENGTH_LONG).show();
				} else {
					alarmManager.setExact(AlarmManager.RTC_WAKEUP,
							cal.getTimeInMillis(), pendingIntent);
					Toast.makeText(getBaseContext(),
							"call alarmManager.setExact()", Toast.LENGTH_LONG)
							.show();
				}
			}

		}

	};

}