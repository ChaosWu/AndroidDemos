package cn.android.demo.apis.thread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AndroidBackgroundThread extends Activity {
	public BackgroundThread backgroundThread;
	
	public TextView mTv;
	public boolean isTextOn = true;

	public TextView tvTxt;
	public Button btStart;
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

		tvTxt = (TextView) findViewById(R.id.tv_output_txt_data);

		btStart = (Button) findViewById(R.id.bt_output_txt);
		btStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvTxt.setText(readTxt());
			}
		});

		ToastUtil.showToast(this, "onCreate!");
	}

	private String readTxt() {

		InputStream is = getResources().openRawResource(R.raw.hello);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

		int i;

		try {
			i = is.read();

			while (i != -1) {
				arrayOutputStream.write(i);
				i = is.read();
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return arrayOutputStream.toString();
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
