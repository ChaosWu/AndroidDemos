package cn.android.demo.apis.ui.views;

import java.util.Calendar;

import cn.android.demo.apis.R;
import cn.android.demo.apis.app.AlarmReceiver;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AndroidDatePicker extends Activity {
	DatePicker datePicker;
	TimePicker timePicker;
	TextView textView;
	Button button;

	RadioGroup radioGroup;
	RadioButton rb1;
	RadioButton rb2;
	RadioButton rb3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_datepicker);

		datePicker = (DatePicker) findViewById(R.id.ui_view_date_picker);
		timePicker = (TimePicker) findViewById(R.id.ui_view_pickertime);

		textView = (TextView) findViewById(R.id.tv_ui_view_date_picker_info);
		button = (Button) findViewById(R.id.bt_ui_view_start_alarm);

		radioGroup = (RadioGroup) findViewById(R.id.rg_opt_group);
		rb1 = (RadioButton) findViewById(R.id.rb_opt_both);

		rb2 = (RadioButton) findViewById(R.id.rb_opt_calendar);

		rb3 = (RadioButton) findViewById(R.id.rb_opt_spinners);

		Calendar today = Calendar.getInstance();
		datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {

					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						textView.setText("Year: " + year + "\n"
								+ "Month of Year: " + monthOfYear + "\n"
								+ "Day of Month: " + dayOfMonth);
					}
				});

		timePicker.setCurrentHour(today.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(today.get(Calendar.MINUTE));
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				textView.append("\n" + "Hour of Day: " + hourOfDay + "\n"
						+ "Minute: " + minute

				);

			}
		});
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (rb1.isChecked()) {
					datePicker.setCalendarViewShown(true);
					datePicker.setSpinnersShown(true);
				} else if (rb2.isChecked()) {
					datePicker.setCalendarViewShown(true);
					datePicker.setSpinnersShown(false);
				} else if (rb3.isChecked()) {
					datePicker.setCalendarViewShown(false);
					datePicker.setSpinnersShown(true);
				}
			}
		});
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar current = Calendar.getInstance();

				Calendar cal = Calendar.getInstance();

				cal.set(datePicker.getYear(), datePicker.getMonth(),
						datePicker.getDayOfMonth(),
						timePicker.getCurrentHour(),
						timePicker.getCurrentMinute(), 00);

				if (cal.compareTo(current) <= 0) {
					Toast.makeText(getApplicationContext(),
							"Invalid Date/Time", Toast.LENGTH_LONG).show();
				} else {
					setAlarm(cal);
				}

			}
		});
	}

	public void setAlarm(Calendar calendar) {
		textView.setText("\n\n***\n" + "Alarm is set@ " + calendar.getTime()
				+ "\n" + "***\n");

		Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				getBaseContext(), 1, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
				pendingIntent);
	}
}
