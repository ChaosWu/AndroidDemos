package cn.android.demo.apis.java;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * java.util.Timer中是一个工具，用于线程安排在后台线程将来执行任务。 可安排在一次执行任务，或者定期重复执行。
 * java.util.TimerTask中是可以被调度为一次性或由定时器重复执行的任务。
 * 
 * @author Elroy
 * 
 */
public class UseTimerTask extends Activity {
	CheckBox checkBox;

	Button start;
	Button cancel;

	TextView counter;

	Timer timer;
	MyTimerTask myTimerTask;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.v("DDD", (String) msg.obj);
			counter.setText((String) msg.obj);

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_use_timertask);
		checkBox = (CheckBox) findViewById(R.id.cb_use_timertask_singleshot);
		counter = (TextView) findViewById(R.id.tv_use_timertask_counter);

		start = (Button) findViewById(R.id.bt_use_timertask_start);
		cancel = (Button) findViewById(R.id.bt_use_timertask_cancel);

		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (timer != null) {
					timer.cancel();
				}
				timer = new Timer();
				myTimerTask = new MyTimerTask();

				if (checkBox.isChecked()) {
					// singleshot delay 1000 ms
					timer.schedule(myTimerTask, 1000);

				} else {
					// 延迟1000毫秒，重复5000毫秒
					timer.schedule(myTimerTask, 1000, 3000);
				}
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (timer != null) {
					timer.cancel();
					timer = null;

				}
			}
		});

	}

	class MyTimerTask extends TimerTask {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd:MMMM:yyyy HH:mm:ss:SSSZ a");

		final String strDate = simpleDateFormat.format(calendar.getTime());

		@Override
		public void run() {
			Message msg = new Message();
			msg.obj = strDate;
			handler.sendMessage(msg);
		}

	}
}
