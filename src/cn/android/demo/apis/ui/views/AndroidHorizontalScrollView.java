package cn.android.demo.apis.ui.views;

import java.io.File;

import cn.android.demo.apis.R;
import cn.android.demo.apis.ui.widget.MyHorizontalLayout;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 * @see android.widget.Gallery 已经被废弃
 * @author Elroy
 * 
 */
public class AndroidHorizontalScrollView extends Activity {
	LinearLayout linearLayout;
	MyHorizontalLayout horizontalLayout;
	MyHorizontalLayout vertical;

	String path = Environment.getExternalStorageDirectory().getPath();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_horizontal_scroll_view);
		linearLayout = (LinearLayout) findViewById(R.id.ll_gallery);
		horizontalLayout = (MyHorizontalLayout) findViewById(R.id.my_widget_linearlayout);
		vertical = (MyHorizontalLayout) findViewById(R.id.my_widget_linearlayout_vertical);

		String targetPath = path + "/DCIM/";
		File file = new File(targetPath);

		File[] files = file.listFiles();

		for (File f : files) {
			linearLayout.addView(insertPhoto(f.getAbsolutePath()));
			horizontalLayout.add(f.getAbsolutePath());
			vertical.add(f.getAbsolutePath());

		}
	}

	private View insertPhoto(String path) {
		Bitmap bm = decodeSampledBitmapFromUri(path, 220, 220);
		LinearLayout layout = new LinearLayout(getApplicationContext());
		layout.setLayoutParams(new LayoutParams(250, 250));
		layout.setGravity(Gravity.CENTER);

		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setLayoutParams(new LayoutParams(250, 250));
		imageView.setScaleType(ScaleType.CENTER_CROP);
		imageView.setImageBitmap(bm);

		layout.addView(imageView);

		return layout;

	}

	public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth,
			int reqHeight) {
		final BitmapFactory.Options options = new Options();

		Bitmap bm = null;

		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		options.inJustDecodeBounds = false;

		bm = BitmapFactory.decodeFile(path, options);

		return bm;

	}

	public int calculateInSampleSize(BitmapFactory.Options options, int reqW,
			int reqH) {
		final int h = options.outHeight;
		final int w = options.outWidth;

		int inSampleSize = 1;
		if (h > reqH || w > reqW) {
			if (w > h) {
				inSampleSize = Math.round((float) h / (float) reqH);
			} else {
				inSampleSize = Math.round((float) w / (float) reqW);
			}

		}

		return inSampleSize;

	}
}
