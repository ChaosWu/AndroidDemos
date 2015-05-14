package cn.android.demo.apis.ui.graphics;

import java.io.FileNotFoundException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 模糊滤镜效果
 * 
 * @author Elroy
 * 
 */
public class BlurMaskFilterActivity extends Activity {

	Button button;
	ImageView imageView;

	Uri uri;
	Bitmap bitmapMaster;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_graphics_blur_mask_filter);
		button = $id(R.id.blur_mask_filter_loadimage);
		imageView = $id(R.id.blur_mask_filter_result);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 1);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1:
				uri = data.getData();

				try {
					bitmapMaster = BitmapFactory
							.decodeStream(getContentResolver().openInputStream(
									uri));

					loadGrayBitmap(bitmapMaster);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			default:
				break;
			}

		}

	}

	private void loadGrayBitmap(Bitmap src) {
		if (src != null) {

			int w = src.getWidth();
			int h = src.getHeight();
			RectF rectF = new RectF(w / 4, h / 4, w * 3 / 4, h * 3 / 4);
			float blurRadius = 100.0f;

			Bitmap bitmapResult = Bitmap.createBitmap(w, h,
					Bitmap.Config.ARGB_8888);
			Canvas canvasResult = new Canvas(bitmapResult);

			Paint blurPaintOuter = new Paint();
			blurPaintOuter.setColor(0xFFffffff);
			blurPaintOuter.setMaskFilter(new BlurMaskFilter(blurRadius,
					BlurMaskFilter.Blur.INNER));
			canvasResult.drawBitmap(bitmapMaster, 0, 0, blurPaintOuter);

			Paint blurPaintInner = new Paint();
			blurPaintInner.setColor(0xFFffffff);
			blurPaintInner.setMaskFilter(new BlurMaskFilter(blurRadius,
					BlurMaskFilter.Blur.OUTER));
			canvasResult.drawRect(rectF, blurPaintInner);

			imageView.setImageBitmap(bitmapResult);
		}
	}

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}
}
