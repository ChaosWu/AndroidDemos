package cn.android.demo.apis.thread;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreadHandlerLooper extends Activity {
	Button btn1, btn2;
	TextView textInfo;

	MyThread myThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_android_handler_looper);
		btn1 = (Button) findViewById(R.id.thread_handler_looper_button1);
		btn2 = (Button) findViewById(R.id.thread_handler_looper_button2);
		textInfo = (TextView) findViewById(R.id.thread_handler_looper_info);

		btn1.setOnClickListener(btnOnClickListener);
		btn2.setOnClickListener(btnOnClickListener);

		myThread = new MyThread();
		myThread.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// stop and quit the background Thread
		myThread.handler.getLooper().quit();
	}

	View.OnClickListener btnOnClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (myThread.handler != null) {
				Message message;
				if (v == btn1) {
					message = myThread.handler.obtainMessage(MyThread.MSG1);
				} else {
					message = myThread.handler.obtainMessage(MyThread.MSG2);
				}
				myThread.handler.sendMessage(message);
			}
		}
	};

	private class MyThread extends Thread {

		static final int MSG1 = 1;
		static final int MSG2 = 2;

		public Handler handler;

		public void run() {
			Looper.prepare();
			handler = new MyHandler();
			Looper.loop();
		}

		private class MyHandler extends Handler {
			@Override
			public void handleMessage(Message msg) {
				// ...Run in background

				int what = msg.what;
				switch (what) {
				case MSG1:

					// doing something...
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							textInfo.setText("Message 1");
						}
					});

					break;
				case MSG2:

					// doing something...
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							textInfo.setText("Message 2");
						}
					});

					break;

				}
			}
		}
	}
}
