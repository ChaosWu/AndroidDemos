package cn.android.demo.apis.thread;

import cn.android.demo.apis.R;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 异常-无网络情况，直接退出当前界面
 * 
 * @author Elroy
 * 
 */
public class AsyncLoadImageListView extends Activity {
	public final static String[] imageThumbUrls = new String[] {
			"http://www.juzi2.com/uploads/allimg/130624/1_130624195816_1.jpg",
			"http://d.hiphotos.baidu.com/zhidao/pic/item/7c1ed21b0ef41bd56e22cd6653da81cb39db3d2a.jpg",
			"http://www.juzi2.com/uploads/allimg/130605/1_130605184002_1.jpg",
			"http://img2.3lian.com/2014/f2/29/d/81.jpg",
			"http://img2.3lian.com/2014/f2/182/d/25.jpg",
			"http://img3.3lian.com/2013/c1/91/d/30.jpg",
			"http://e.hiphotos.baidu.com/zhidao/pic/item/7aec54e736d12f2e7519284f4cc2d56285356885.jpg",
			"http://img3.3lian.com/2014/f1/1/d/45.jpg",
			"http://qzone.qqjay.com/u/files/2012/0406/38e203b1b0d92769b90c271129debe56.jpg",
			"http://img2.3lian.com/2014/f2/22/d/81.jpg",
			"http://www.qqya.com/userimg/863/130224121314.jpg",
			"http://img1.3lian.com/img13/c4/98/d/61.jpg",
			"http://g.hiphotos.baidu.com/zhidao/pic/item/d833c895d143ad4bc780a63b83025aafa50f069a.jpg",
			"http://img2.3lian.com/2014/f4/190/d/17.jpg",
			"http://img1.3lian.com/img013/v4/64/d/21.jpg",
			"http://tupian.enterdesk.com/2013/mxy/10/15/4/1.jpg",
			"http://img2.3lian.com/2014/f2/96/d/100.jpg",
			"http://tupian.qqjay.com/u/2012/0204/ef418cbaf8e66353d258c023ae5f6f76.jpg",
			"http://e.hiphotos.baidu.com/zhidao/pic/item/96dda144ad345982dee7d2150cf431adcaef84c3.jpg",
			"http://images.ccoo.cn/ablum/20111224/20111224233708425.jpg",
			"http://f.hiphotos.baidu.com/zhidao/pic/item/0dd7912397dda144e8437e51b2b7d0a20df4868e.jpg",
			"http://s10.sinaimg.cn/middle/5f05d29bhb77b6b528c09&690",
			"http://img.dapixie.com/uploads/allimg/120118/1-12011Q20329.jpg"

	};

	ListView imageList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		imageList = new ListView(this);
		setContentView(imageList);
		ArrayList<String> srcList = new ArrayList<String>(
				Arrays.asList(imageThumbUrls));
		imageList.setAdapter(new CustomListAdapter(this, srcList));
	}

	// ----------------------------------------------------

	public class CustomListAdapter extends BaseAdapter {
		private ArrayList<String> listData;
		private LayoutInflater layoutInflater;

		public CustomListAdapter(Context context, ArrayList<String> listData) {
			this.listData = listData;
			layoutInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return listData.size();
		}

		@Override
		public Object getItem(int position) {
			return listData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = layoutInflater.inflate(R.layout.list_image_row,
						null);
				holder = new ViewHolder();
				holder.icon = (ImageView) convertView.findViewById(R.id.thumb);
				holder.text = (TextView) convertView.findViewById(R.id.text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.text.setText(String.valueOf(position));

			if (holder.icon != null) {
				new BitmapWorkerTask(holder.icon).execute(listData
						.get(position));
			}
			return convertView;
		}

		class ViewHolder {
			ImageView icon;
			TextView text;
		}
	}

	// ----------------------------------------------------
	// Load bitmap in AsyncTask
	class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
		private final WeakReference<ImageView> imageViewReference;
		private String imageUrl;

		public BitmapWorkerTask(ImageView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			imageViewReference = new WeakReference<ImageView>(imageView);
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(String... params) {
			imageUrl = params[0];
			return LoadImage(imageUrl);
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (imageViewReference != null && bitmap != null) {
				final ImageView imageView = imageViewReference.get();
				if (imageView != null) {
					imageView.setImageBitmap(bitmap);
				}
			}
		}

		private Bitmap LoadImage(String URL) {
			Bitmap bitmap = null;
			InputStream in = null;
			try {
				in = OpenHttpConnection(URL);
				bitmap = BitmapFactory.decodeStream(in);
				in.close();
			} catch (IOException e1) {
			}
			return bitmap;
		}

		private InputStream OpenHttpConnection(String strURL) {
			InputStream inputStream = null;
			try {
				URL url = new URL(strURL);
				URLConnection conn = url.openConnection();

				HttpURLConnection httpConn = (HttpURLConnection) conn;
				httpConn.setRequestMethod("GET");
				httpConn.connect();

				if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					inputStream = httpConn.getInputStream();
				}
			} catch (Exception ex) {
			}
			return inputStream;
		}
	}
}
