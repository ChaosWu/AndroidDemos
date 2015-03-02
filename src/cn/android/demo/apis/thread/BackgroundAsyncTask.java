package cn.android.demo.apis.thread;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

public class BackgroundAsyncTask extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.thread_android_background_async);

		textView = (TextView) findViewById(R.id.tv_async_mytext);
		new MyAsyncTask().execute();

	}

	public class MyAsyncTask extends AsyncTask<Void, Boolean, Void> {

		boolean isTextOn;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			ToastUtil.showToast(BackgroundAsyncTask.this, "onPreExecute!");
		}

		@Override
		protected Void doInBackground(Void... params) {

			while (true) {
				isTextOn = !isTextOn;
				publishProgress(isTextOn);
				SystemClock.sleep(1000);
			}

		}

		@Override
		protected void onProgressUpdate(Boolean... values) {
			super.onProgressUpdate(values);
			if (values[0]) {
				textView.setVisibility(View.GONE);

			} else {
				textView.setVisibility(View.VISIBLE);
			}

		}

	}
}
