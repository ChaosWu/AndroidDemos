package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ScheduledActivity extends Activity {
	private Button dismiss;
	private Button stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_scheduled);
		dismiss = (Button) findViewById(R.id.bt_scheduled_dismiss);
		stop = (Button) findViewById(R.id.bt_scheduled_stop);

		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						ScheduledReceiver.class);

				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						getBaseContext(), 0, intent, 0);

				AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

				alarmManager.cancel(pendingIntent);

				Intent intent2 = new Intent();
				intent2.setClass(ScheduledActivity.this,
						AlarmManagerActivity.class);

				startActivity(intent2);
				finish();

			}
		});
		dismiss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}
