package cn.android.demo.apis.thread;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AndroidAsyncTask extends Activity {
	TextView myText;
	boolean stopRun = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_async_task);
		myText = (TextView) findViewById(R.id.mytext);

		Button buttonStop = (Button) findViewById(R.id.stop);
		buttonStop.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopRun = true;
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
	public class BackgroundAsyncTask extends AsyncTask<Void, Boolean, Void> {

		boolean myTextOn;

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			while (!stopRun) {
				myTextOn = !myTextOn;
				publishProgress(myTextOn);//每次传递boolean ，由onProgressUpdate
				SystemClock.sleep(1000);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			ToastUtil.showToast(AndroidAsyncTask.this, "onPost Execute");
		}

		@Override
		protected void onPreExecute() {//执行耗时操作之前处理UI线程事件
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