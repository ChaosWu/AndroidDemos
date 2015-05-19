package cn.android.demo.apis.thread;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * 线程池
 * 
 * @author Elroy
 * 
 */
public class AsyncTaskPool extends Activity {
	ProgressBar bar1;
	ProgressBar bar2;
	ProgressBar bar3;
	ProgressBar bar4;
	ProgressBar bar5;

	Button start;

	MyAsyncTask asyncTask1;
	MyAsyncTask asyncTask2;
	MyAsyncTask asyncTask3;
	MyAsyncTask asyncTask4;
	MyAsyncTask asyncTask5;

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_android_asynctask_pool);

		bar1 = $id(R.id.asynctask_pool_progressbar1);
		bar2 = $id(R.id.asynctask_pool_progressbar2);
		bar3 = $id(R.id.asynctask_pool_progressbar3);
		bar4 = $id(R.id.asynctask_pool_progressbar4);
		bar5 = $id(R.id.asynctask_pool_progressbar5);

		start = $id(R.id.asynctask_pool_start);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				asyncTask1 = new MyAsyncTask(bar1);
				asyncTask1.execute();

				asyncTask2 = new MyAsyncTask(bar2);
				asyncTask2.execute();

				asyncTask3 = new MyAsyncTask(bar3);
				asyncTask3.execute();

				asyncTask4 = new MyAsyncTask(bar4);
				startAsyncTaskInParallel(asyncTask4);

				asyncTask5 = new MyAsyncTask(bar5);
				startAsyncTaskInParallel(asyncTask5);

			}
		});

	}

	@SuppressLint("NewApi")
	private void startAsyncTaskInParallel(MyAsyncTask task) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			task.execute();
		}

	}

	public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
		ProgressBar bar;

		public MyAsyncTask(ProgressBar target) {
			this.bar = target;
		}

		@Override
		protected Void doInBackground(Void... params) {
			for (int i = 0; i < 100; i++) {
				publishProgress(i);
				SystemClock.sleep(100);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			bar.setProgress(values[0]);
		}

	}
}
