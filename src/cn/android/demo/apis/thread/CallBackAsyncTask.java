package cn.android.demo.apis.thread;

import cn.android.demo.apis.java.DoSomeing;
import android.os.AsyncTask;
import android.os.SystemClock;

public class CallBackAsyncTask extends AsyncTask<Void, Void, Void> {
	DoSomeing doSomeing;
	int mMax;

	public CallBackAsyncTask(DoSomeing call, int max) {
		mMax = max;
		doSomeing = call;

	}

	@Override
	protected Void doInBackground(Void... params) {
		for (int i = 0; i <= mMax; i++) {
			SystemClock.sleep(100);
			doSomeing.doInBackground(i);
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		doSomeing.doPostExecute();
	}

}
