package cn.android.demo.apis.ui.widget;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;

@SuppressLint("NewApi")
public class MyHorizontalLayout extends LinearLayout {
	Context mContext;
	ArrayList<String> itemList = new ArrayList<String>();

	public MyHorizontalLayout(Context context) {
		super(context);
		mContext = context;
	}

	public void add(String path) {
		int newId = itemList.size();
		itemList.add(path);
		addView(getImageView(newId));
	}

	private View getImageView(int i) {
		Bitmap bm = null;
		if (i < itemList.size()) {
			bm = decodeSampledBitmapFromUri(itemList.get(i), 220, 220);

		}

		ImageView imageView = new ImageView(mContext);
		imageView.setLayoutParams(new LayoutParams(220, 220));
		imageView.setScaleType(ScaleType.CENTER_CROP);
		imageView.setImageBitmap(bm);

		return imageView;
	}

	public MyHorizontalLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public MyHorizontalLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth,
			int reqHeight) {
		Bitmap bm = null;

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(path, options);

		return bm;
	}

	public int calculateInSampleSize(

	BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}

		return inSampleSize;
	}

}
