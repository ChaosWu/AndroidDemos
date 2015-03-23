package cn.android.demo.apis.app;

import java.util.Calendar;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlarmManagerActivity extends Activity {
	private Button start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_alarm_manager);
		start = (Button) findViewById(R.id.bt_alarm_start);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						ScheduledReceiver.class);

				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						getBaseContext(), 0, intent, 0);

				AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.SECOND, 5);
				long interval = 2 * 1000;
				alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
						calendar.getTimeInMillis(), interval, pendingIntent);

				finish();

			}
		});

	}
}
