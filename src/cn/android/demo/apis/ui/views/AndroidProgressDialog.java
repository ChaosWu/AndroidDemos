package cn.android.demo.apis.ui.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class AndroidProgressDialog extends Activity {
	MyAsyncTask asyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		Button button = new Button(this);
		button.setLayoutParams(params);
		button.setText("ProgressDialog");

		layout.addView(button);
		setContentView(layout);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				asyncTask = new MyAsyncTask();
				asyncTask.execute();
			}
		});
	}

	class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
		boolean isRunning;
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			isRunning = true;

			progressDialog = ProgressDialog.show(AndroidProgressDialog.this,
					"ProgressDialog", "Wait!");
			progressDialog.setCanceledOnTouchOutside(true);
			progressDialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					isRunning = false;
				}
			});

		}

		@Override
		protected Void doInBackground(Void... params) {

			int i = 5;
			while (isRunning) {
				SystemClock.sleep(1000);
				if (i-- == 0) {
					isRunning = false;
				}

				publishProgress(i);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			progressDialog.dismiss();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

			progressDialog.setMessage(String.valueOf(values[0]));
		}
	}
}
