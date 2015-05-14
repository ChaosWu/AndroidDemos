package cn.android.demo.apis.ui.graphics;

import android.annotation.SuppressLint;
import android.app.Activity;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import cn.android.demo.apis.R;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 不同加载图片读取耗时
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class AndroidScaleBitmapWithInDitherAndInPreferQualityOverSpeed extends
		Activity {
	final int RQS_IMAGE1 = 1;

	Button btnLoadImage;
	ToggleButton optBtnInDither, optBtnInPreferQualityOverSpeed;
	TextView textInfo;
	ImageView imageResult;

	Uri source1;
	Date startTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_scale_bitmap_with_indither_and_inpreferqualityoverspeed);
		btnLoadImage = (Button) findViewById(R.id.sss_scale_bitmap_loadimage);
		optBtnInDither = (ToggleButton) findViewById(R.id.sss_scale_bitmap_optInDither);
		optBtnInPreferQualityOverSpeed = (ToggleButton) findViewById(R.id.sss_scale_bitmap_optInPreferQualityOverSpeed);
		textInfo = (TextView) findViewById(R.id.sss_scale_bitmap_info);
		imageResult = (ImageView) findViewById(R.id.sss_scale_bitmap_result);

		btnLoadImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, RQS_IMAGE1);
			}
		});

		optBtnInDither
				.setOnCheckedChangeListener(optBtnOnCheckedChangeListener);
		optBtnInPreferQualityOverSpeed
				.setOnCheckedChangeListener(optBtnOnCheckedChangeListener);
	}

	OnCheckedChangeListener optBtnOnCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			doScale(source1);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case RQS_IMAGE1:
				source1 = data.getData();
				optBtnInDither.setChecked(false);
				optBtnInPreferQualityOverSpeed.setChecked(false);
				doScale(source1);
				break;
			}
		}
	}

	private void doScale(Uri src) {

		try {
			Bitmap bm = loadScaledBitmap(source1);
			imageResult.setImageBitmap(bm);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Bitmap loadScaledBitmap(Uri src) throws FileNotFoundException {

		// required max width/height
		final int REQ_WIDTH = 800;
		final int REQ_HEIGHT = 800;

		Bitmap bm = null;

		if (src != null) {
			// First decode with inJustDecodeBounds=true to check dimensions
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(src), null, options);

			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, REQ_WIDTH,
					REQ_HEIGHT);

			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;

			// set options according to RadioButton setting
			options.inDither = optBtnInDither.isChecked();
			// inPreferQualityOverSpeed require API Level 10
			options.inPreferQualityOverSpeed = optBtnInPreferQualityOverSpeed
					.isChecked();

			InputStream inputStream = getContentResolver().openInputStream(src);
			startTime = new Date();

			bm = BitmapFactory.decodeStream(inputStream, null, options);

			// INACCURACY processing duration of scaling bitmap in millisecond
			long duration = new Date().getTime() - startTime.getTime();
			textInfo.setText("duration in ms: " + duration);
		}

		return bm;
	}

	public int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee a final image with both dimensions larger than or
			// equal to the requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}
}