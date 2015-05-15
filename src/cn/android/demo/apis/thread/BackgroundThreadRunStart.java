package cn.android.demo.apis.thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 后台线程执行
 * 
 * 
 * start：后台线程
 * 
 * run：当前线程
 * 
 * @author Elroy
 * 
 */
public class BackgroundThreadRunStart extends Activity {
	TextView info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		LayoutParams btParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(layoutParams);
		setContentView(layout);

		Button start = new Button(this);
		Button run = new Button(this);
		start.setText("Thread start!");
		run.setText("Thread run!");
		run.setLayoutParams(btParams);
		start.setLayoutParams(btParams);

		info = new TextView(this);
		info.setText("***");
		info.setLayoutParams(btParams);

		layout.addView(start, 0);
		layout.addView(run, 1);
		layout.addView(info, 2);

		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Thread thread = new Thread(new MyRunnable());
				thread.setName("start thread!");
				thread.start();// 后台线程
			}
		});

		run.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Thread thread = new Thread(new MyRunnable());
				thread.setName("run thread!");
				thread.run();// 当前线程

			}
		});

	}

	private class MyRunnable implements Runnable {

		@Override
		public void run() {
			final String mThreadName = Thread.currentThread().getName();
			if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
				// 在主线程
				info.setText("运行在主线程:" + mThreadName);
			} else {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {

						info.setText("运行在后台线程:" + mThreadName);

					}
				});
			}
		}
	}
}
