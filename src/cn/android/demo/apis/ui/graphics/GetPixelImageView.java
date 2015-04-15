package cn.android.demo.apis.ui.graphics;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 获取动态图片像素；
 * 
 * 对位图进行缩放，如layout_width和layout_height这是行不通的
 * 
 * @author Elroy
 * 
 */
public class GetPixelImageView extends Activity {

	ImageView source1;
	ImageView source2;

	TextView touchXY;
	TextView invertedXY;
	TextView imgSize;
	TextView colorRGB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_get_pixel_imageview);

		touchXY = (TextView) findViewById(R.id.xy);
		invertedXY = (TextView) findViewById(R.id.invertedxy);
		imgSize = (TextView) findViewById(R.id.size);
		colorRGB = (TextView) findViewById(R.id.colorrgb);

		source1 = (ImageView) findViewById(R.id.source1);
		source2 = (ImageView) findViewById(R.id.source2);

		source1.setOnTouchListener(imageOnTouchListener);
		source2.setOnTouchListener(imageOnTouchListener);
	}

	OnTouchListener imageOnTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			float eventX = event.getX();
			float eventY = event.getY();
			float[] eventXY = new float[] { eventX, eventY };
			
			Matrix matrix = new Matrix();
			((ImageView) v).getImageMatrix().invert(matrix);
			matrix.mapPoints(eventXY);

			int x = Integer.valueOf((int) eventXY[0]);
			int y = Integer.valueOf((int) eventXY[1]);

			touchXY.setText("touched position: " + String.valueOf(eventX)
					+ " / " + String.valueOf(eventY));
			invertedXY.setText("touched position: " + String.valueOf(x) + " / "
					+ String.valueOf(y));

			Drawable drawable = ((ImageView) v).getDrawable();
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

			imgSize.setText("drawable size: "
					+ String.valueOf(bitmap.getWidth()) + " / "
					+ String.valueOf(bitmap.getHeight()));

			// Limit x, y range within bitmap
			if (x < 0) {
				x = 0;
			} else if (x > bitmap.getWidth() - 1) {
				x = bitmap.getWidth() - 1;
			}
			if (y < 0) {
				y = 0;
			} else if (y > bitmap.getHeight() - 1) {
				y = bitmap.getHeight() - 1;
			}

			int touchedRGB = bitmap.getPixel(x, y);
			colorRGB.setText("touched color: " + "#"
					+ Integer.toHexString(touchedRGB));
			colorRGB.setTextColor(touchedRGB);
			return true;
		}
	};
}
