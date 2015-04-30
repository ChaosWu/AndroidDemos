package cn.android.demo.apis.java;

import cn.android.demo.apis.R;
import cn.android.demo.apis.thread.CallBackAsyncTask;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 回调接口
 * 
 * @author Elroy
 * 
 */
public class JavaCallBack extends Activity implements DoSomeing {
	ProgressBar progressBar;
	CallBackAsyncTask asyncTask;
	int mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_call_back);
		progressBar = (ProgressBar) findViewById(R.id.pb_java_call_back_myprogressbar);
		mProgress = 0;

		asyncTask = new CallBackAsyncTask(this, 100);
		asyncTask.execute();

	}

	@Override
	public void doInBackground(int progress) {
		progressBar.setProgress(progress);
	}

	@Override
	public void doPostExecute() {
		Toast.makeText(JavaCallBack.this, "Finish", Toast.LENGTH_LONG).show();

	}

}
