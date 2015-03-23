package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore.Video.Thumbnails;
import android.widget.ImageView;

public class AndroidThumbnailUtils extends Activity {
	private ImageView ivMini;
	private ImageView ivMicro;

	// private String url_1 =
	// "http://us.sinaimg.cn/003iWlrLjx06QBVWGP1501040100lXnU0k01.mp4";
	// private String url_2 =
	// "http://us.sinaimg.cn/001N65zEjx06PA0XKpNd01040100G0pz0k01.mp4";
	private String url = "/storage/sdcard0/DCIM/Camera/movie_20141005_135845.mp4";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_android_thumbnail);

		ivMini = (ImageView) findViewById(R.id.iv_thumbnail_mini);
		ivMicro = (ImageView) findViewById(R.id.iv_thumbnail_micro);

		Bitmap bitmap;
		bitmap = ThumbnailUtils.createVideoThumbnail(url,
				Thumbnails.MINI_KIND);
		ivMini.setImageBitmap(bitmap);
		bitmap = ThumbnailUtils.createVideoThumbnail(url,
				Thumbnails.MICRO_KIND);
		ivMicro.setImageBitmap(bitmap);

	}
}
