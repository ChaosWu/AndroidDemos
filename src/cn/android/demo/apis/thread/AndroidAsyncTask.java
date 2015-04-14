package cn.android.demo.apis.thread;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AndroidAsyncTask extends Activity {
	TextView myText;
	ProgressBar bar;
	Button btStart;
	Button buttonStop;
	Button btDowload;
	ImageView iv;
	boolean stopRun = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_async_task);
		myText = (TextView) findViewById(R.id.mytext);
		bar = (ProgressBar) findViewById(R.id.pb_async_task);

		btDowload = (Button) findViewById(R.id.bt_dowload_async_image);
		iv = (ImageView) findViewById(R.id.iv_dowload_async);

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

		btDowload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				URL url = null;
				try {
					url = new URL(
							"http://img2.imgtn.bdimg.com/it/u=2964947280,2953713804&fm=21&gp=0.jpg");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new DowloadImageAsyncTask(iv).execute(url);
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

	public class DowloadImageAsyncTask extends AsyncTask<URL, Void, Bitmap> {
		Bitmap bitmap = null;
		ImageView imageView;

		public DowloadImageAsyncTask(ImageView iv) {
			imageView = iv;
		}

		@Override
		protected Bitmap doInBackground(URL... params) {
			URL url = params[0];
			try {
				bitmap = BitmapFactory.decodeStream(url.openConnection()
						.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);

			iv.setImageBitmap(result);
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