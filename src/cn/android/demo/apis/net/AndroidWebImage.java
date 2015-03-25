package cn.android.demo.apis.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import cn.android.demo.apis.R;
import cn.android.demo.utils.BitmapUtil;
import cn.android.demo.utils.HttpUtil;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class AndroidWebImage extends Activity {
	// private String imageUrl =
	// "https://avatars2.githubusercontent.com/u/4157575?v=3&s=460";
	private String imageUrl = "http://cdn.iciba.com/news/word/2015-03-25.jpg";
	ImageView imageView;
	BitmapFactory.Options options;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_web_image);

		imageView = (ImageView) findViewById(R.id.iv_web_head);
		options = new Options();
		options.inSampleSize = 1;

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
