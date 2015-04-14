package cn.android.demo.apis.thread;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * 如果下载多个图片，开启多个线程明显是耗费性能的一件事
 * 
 * 可以考虑用线程池 ，循环利用，AsyncTask内部其实就有个简单的线程池机制
 * 
 * @author Elroy
 * 
 */
public class MultiAsyncTask extends Activity {
	ProgressBar loadBar;
	ImageView[] targetImage = new ImageView[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.thread_android_multi_asynctask);
		targetImage[0] = (ImageView) findViewById(R.id.multi_async_target0);
		targetImage[1] = (ImageView) findViewById(R.id.multi_async_target1);
		targetImage[2] = (ImageView) findViewById(R.id.multi_async_target2);
		targetImage[3] = (ImageView) findViewById(R.id.multi_async_target3);
		targetImage[4] = (ImageView) findViewById(R.id.multi_async_target4);

		loadBar = (ProgressBar) findViewById(R.id.loadingprogress);

		String loadIm0 = "http://imgsrc.baidu.com/forum/w%3D580/sign=046388fd52da81cb4ee683c56266d0a4/851c0a55b319ebc41e4816908126cffc1e1716b0.jpg";
		String loadIm1 = "http://img2.imgtn.bdimg.com/it/u=685359157,1269566533&fm=21&gp=0.jpg";
		String loadIm2 = "http://img1.imgtn.bdimg.com/it/u=1870396989,3816241280&fm=21&gp=0.jpg";
		String loadIm3 = "http://img1.imgtn.bdimg.com/it/u=3496771687,32633256&fm=21&gp=0.jpg";
		String loadIm4 = "http://img4.imgtn.bdimg.com/it/u=327404788,649039164&fm=21&gp=0.jpg";
		URL url0 = null, url1 = null, url2 = null, url3 = null, url4 = null;
		try {
			url0 = new URL(loadIm0);
			url1 = new URL(loadIm1);
			url2 = new URL(loadIm2);
			url3 = new URL(loadIm3);
			url4 = new URL(loadIm4);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new NetworkTask(5, targetImage, loadBar).execute(url0, url1, url2,
				url3, url4);
	}

	private class NetworkTask extends AsyncTask<URL, Integer, Void> {
		ImageView[] ivs;
		Bitmap[] bms;
		ProgressBar progressBar;

		public NetworkTask(int numberOfImage, ImageView[] imageViews,
				ProgressBar pb) {
			bms = new Bitmap[numberOfImage];

			ivs = new ImageView[numberOfImage];
			for (int i = 0; i < imageViews.length; i++) {
				ivs[i] = imageViews[i];
			}
			progressBar = pb;
		}

		@Override
		protected Void doInBackground(URL... params) {

			if (params.length > 0) {
				for (int j = 0; j < params.length; j++) {
					URL networkUrl = params[j];

					try {
						bms[j] = BitmapFactory.decodeStream(networkUrl
								.openConnection().getInputStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					publishProgress(j);

					SystemClock.sleep(500);
				}

			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

			if (values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					ivs[values[i]].setImageBitmap(bms[values[i]]);
					progressBar.setProgress(values[i] + 1);

				}

			}

		}
	}
}
