package cn.android.demo.apis.ui.views;

import java.util.Calendar;

import cn.android.demo.apis.R;
import cn.android.demo.apis.app.AlarmReceiver;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * 时间选择器
 * 
 * @author Elroy
 * 
 */
public class AndroidTimePicker extends Activity {
	Button btSet;
	TextView tv;

	TimePicker picker;
	TimePickerDialog pickerDialog;

	final static int TIME_RQS_1 = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_view_timepicker);
		tv = (TextView) findViewById(R.id.tv_time_prompt);
		btSet = (Button) findViewById(R.id.bt_time_start_set_dialog);
		btSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openTimePickerDialog(false);
			}
		});
	}

	protected void openTimePickerDialog(boolean is24r) {
		Calendar calendar = Calendar.getInstance();
		pickerDialog = new TimePickerDialog(this, onTimeSetListener,
				calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), is24r);

		pickerDialog.setTitle("TimePickerDialog Title");
		pickerDialog.setMessage("TimePickerDialog Message");
		pickerDialog.show();

	}

	private OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			Calendar calNow = Calendar.getInstance();
			Calendar calSet = (Calendar) calNow.clone();

			calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
			calSet.set(Calendar.MINUTE, minute);

			String strPrompt = "Current Time is: " + calNow.getTime() + " / "
					+ calNow.getTimeInMillis() + "\n" + "Set Time is: "
					+ calSet.getTime() + " / " + calSet.getTimeInMillis()
					+ "\n" + "calSet.compareTo(calNow) = "
					+ calSet.compareTo(calNow) + "\n\n";

			if (calSet.compareTo(calNow) > 0) {
				// Today Set time not yet passed
				long diff = calSet.getTimeInMillis() - calNow.getTimeInMillis();
				strPrompt += "Millis to set time = " + diff;

			} else {
				// Today Set time passed, count to tomorrow
				calSet.add(Calendar.DATE, 1);
				long diff = calSet.getTimeInMillis() - calNow.getTimeInMillis();
				strPrompt += "Millis to tomorrow set time = " + diff;
			}
			tv.setText(strPrompt);
			setAlarm(calSet);
		}
	};

	protected void setAlarm(Calendar calSet) {
		tv.append("\n" + "Alarm is set@ " + calSet.getTime() + "\n" + "***\n");

		Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				getBaseContext(), TIME_RQS_1, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(),
				pendingIntent);
	}

	@Override
	protected void onPause() {
		super.onPause();
		cancelAlarm();
	}

	private void cancelAlarm() {

		//tv.setText("\n\n***\n" + "Alarm Cancelled! \n" + "***\n");

		Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				getBaseContext(), TIME_RQS_1, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);

	}
}
