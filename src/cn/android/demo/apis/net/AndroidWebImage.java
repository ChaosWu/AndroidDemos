package cn.android.demo.apis.net;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.prefs.Preferences;

import cn.android.demo.apis.R;
import cn.android.demo.utils.BitmapUtil;
import cn.android.demo.utils.ToastUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class AndroidWebImage extends Activity {
	// private String imageUrl =
	// "https://avatars2.githubusercontent.com/u/4157575?v=3&s=460";
	private String imageUrl = "http://cdn.iciba.com/news/word/2015-03-25.jpg";
	final String strPref_Download_ID = "PREF_DOWNLOAD_ID";

	ImageView imageView;
	ImageView imageView2;

	BitmapFactory.Options options;
	private ProgressDialog progressDialog;

	SharedPreferences preferences;
	DownloadManager downloadManager;

	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_web_image);
		button = (Button) findViewById(R.id.dowload_iv);
		imageView = (ImageView) findViewById(R.id.iv_web_head);
		imageView2 = (ImageView) findViewById(R.id.iv_dowload_head);
		options = new Options();
		options.inSampleSize = 1;

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse(imageUrl);
				DownloadManager.Request request = new DownloadManager.Request(
						uri);
				long id = downloadManager.enqueue(request);

				Editor editor = preferences.edit();
				editor.putLong(strPref_Download_ID, id);
				editor.commit();
			}
		});
		// StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		// .detectDiskReads().detectDiskWrites().detectNetwork()
		// .penaltyLog().build());
		// StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		// .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		// .penaltyLog().penaltyDeath().build());
		// progressDialog = ProgressDialog.show(getBaseContext(), "", "进度条");
		new MyAsyncTask().execute();

		// Bitmap bitmap = LoadImage(imageUrl, options);
		// imageView.setImageBitmap(bitmap);

	}

	@Override
	protected void onResume() {
		super.onResume();

		IntentFilter filter = new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		registerReceiver(receiver, filter);

	}

	@Override
	protected void onPause() {
		super.onPause();

		unregisterReceiver(receiver);

	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.v("DDD", "onReceive");
			DownloadManager.Query query = new Query();
			query.setFilterById(preferences.getLong(strPref_Download_ID, 0));
			Cursor cursor = downloadManager.query(query);
			if (cursor.moveToFirst()) {
				int columnIndex = cursor
						.getColumnIndex(DownloadManager.COLUMN_STATUS);
				int status = cursor.getInt(columnIndex);
				if (status == DownloadManager.STATUS_SUCCESSFUL) {
					long downloadID = preferences.getLong(strPref_Download_ID,
							0);
					ParcelFileDescriptor fileDescriptor;

					try {
						fileDescriptor = downloadManager
								.openDownloadedFile(downloadID);
						FileInputStream fis = new ParcelFileDescriptor.AutoCloseInputStream(
								fileDescriptor);

						Bitmap bm = BitmapFactory.decodeStream(fis);

						imageView2.setImageBitmap(bm);

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}
	};

	// private Bitmap LoadImage(String URL, BitmapFactory.Options options) {
	// Bitmap bitmap = null;
	// InputStream inputStream = null;
	//
	// try {
	// inputStream = HttpUtil.OpenHttpConnection(URL);
	// bitmap = BitmapFactory.decodeStream(inputStream, null, options);
	// inputStream.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// return bitmap;
	//
	// }

	// private InputStream OpenHttpConnection(String strUrl) throws IOException
	// {
	// InputStream inputStream = null;
	// URL url = new URL(strUrl);
	// URLConnection connection = url.openConnection();
	//
	// HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
	// httpURLConnection.setRequestMethod("GET");
	// httpURLConnection.connect();
	//
	// if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	// inputStream = httpURLConnection.getInputStream();
	// }
	//
	// return inputStream;
	//
	// }

	public class MyAsyncTask extends AsyncTask<Void, Void, Bitmap> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			ToastUtil.showToast(AndroidWebImage.this, "线程执行");

		}

		@Override
		protected Bitmap doInBackground(Void... params) {
			Bitmap bitmap = BitmapUtil.LoadImage(imageUrl, options);
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			imageView.setImageBitmap(result);
		}

	}

}
