package cn.android.demo.apis.thread;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AndroidAsyncTask extends Activity {
	TextView myText;
	ProgressBar bar;
	Button btStart;
	Button buttonStop;

	boolean stopRun = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_async_task);
		myText = (TextView) findViewById(R.id.mytext);
		bar = (ProgressBar) findViewById(R.id.pb_async_task);

		buttonStop = (Button) findViewById(R.id.stop);
		buttonStop.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				stopRun = true;
			}
		});
		btStart = (Button) findViewById(R.id.bt_start_pb);
		btStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// do
				new ProgressAsyncTask().execute();
				btStart.setClickable(false);
			}
		});
		new BackgroundAsyncTask().execute();
	}

	/**
	 * 1.Params:UI线程传过来的参数。
	 * 
	 * 2.Progress:发布进度的类型。
	 * 
	 * 3.Result:返回结果的类型。耗时操作doInBackground的返回结果传给执行之后的参数类型。
	 * 
	 * 
	 * 执行流程：
	 * 
	 * 1.onPreExecute()
	 * 
	 * 2.doInBackground()-->onProgressUpdate()
	 * 
	 * 3.onPostExecute()
	 * 
	 * @author Elroy
	 * 
	 */

	public class ProgressAsyncTask extends AsyncTask<Void, Integer, Void> {
		int mProgress;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			ToastUtil.showToast(AndroidAsyncTask.this,
					"ProgressAsyncTask onPre Execute");
			mProgress = 0;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			bar.setProgress(values[0]);
		}

		@Override
		protected Void doInBackground(Void... params) {
			while (mProgress < 100) {
				mProgress++;
				publishProgress(mProgress);
				SystemClock.sleep(300);
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	}

	public class BackgroundAsyncTask extends AsyncTask<Void, Boolean, Void> {

		boolean myTextOn;

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			while (!stopRun) {
				myTextOn = !myTextOn;
				publishProgress(myTextOn);// 每次传递boolean ，由onProgressUpdate
				SystemClock.sleep(1000);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			ToastUtil.showToast(AndroidAsyncTask.this, "onPost Execute");
			btStart.setClickable(true);
		}

		@Override
		protected void onPreExecute() {// 执行耗时操作之前处理UI线程事件
			myTextOn = true;
			ToastUtil.showToast(AndroidAsyncTask.this, "onPre Execute");
		}

		@Override
		protected void onProgressUpdate(Boolean... values) {
			if (values[0]) {
				myText.setVisibility(View.GONE);
			} else {
				myText.setVisibility(View.VISIBLE);
			}
		}

	}

}