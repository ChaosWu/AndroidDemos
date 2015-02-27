package cn.android.demo.apis.thread;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AndroidBackgroundThread extends Activity {
	public BackgroundThread backgroundThread;
	public TextView mTv;
	public boolean isTextOn = true;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (isTextOn) {
				Log.v("DDD", "isTextOn: true");
				isTextOn = false;
				mTv.setVisibility(View.GONE);
				ToastUtil.showToast(AndroidBackgroundThread.this, "true");
			} else {
				Log.v("DDD", "isTextOn: false");
				isTextOn = true;
				mTv.setVisibility(View.VISIBLE);
				ToastUtil.showToast(AndroidBackgroundThread.this, "false");

			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_android_background);
		mTv = (TextView) findViewById(R.id.mytext);
		ToastUtil.showToast(this, "onCreate!");
	}

	@Override
	protected void onStart() {
		super.onStart();
		backgroundThread = new BackgroundThread();
		backgroundThread.setRunning(true);
		backgroundThread.start();

		ToastUtil.showToast(this, "onStart!");
	}

	@Override
	protected void onStop() {
		super.onStop();
		boolean retry = true;
		backgroundThread.setRunning(false);

		while (retry) {
			try {
				backgroundThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		ToastUtil.showToast(this, "onStop!");

	}

	public class BackgroundThread extends Thread {
		boolean running = false;

		void setRunning(boolean b) {
			running = b;
		}

		@Override
		public void run() {
			while (running) {
				try {
					sleep(500);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);

			}

		}
	}
}
