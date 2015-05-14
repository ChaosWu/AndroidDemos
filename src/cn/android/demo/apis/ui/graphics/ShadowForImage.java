package cn.android.demo.apis.ui.graphics;

import java.io.FileNotFoundException;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 创建图片阴影效果
 * 
 * 获取当前点击图片的颜色
 * 
 * @author Elroy
 * 
 */
public class ShadowForImage extends Activity {
	Button btLoad;
	TextView tvPath;
	TextView tvTouch;
	Button btProcessing;
	ImageView ivResult;

	Uri uri;
	private Bitmap bitmapMaster;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_shadow_image);

		btLoad = $id(R.id.bt_load_shadow_image);
		tvPath = $id(R.id.tv_load_shadow_path);
		btProcessing = $id(R.id.bt_load_shadow_processing);
		ivResult = $id(R.id.iv_load_shadow_result);

		tvTouch = $id(R.id.tv_load_ontouch_image_xy);
		btLoad.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 1);
			}
		});

		btProcessing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (tvPath != null) {
					final Bitmap bm = ProcessingBitmap();
					if (bm != null) {
						ivResult.setImageBitmap(bm);
						ivResult.setOnTouchListener(new OnTouchListener() {

							@Override
							public boolean onTouch(View v, MotionEvent event) {
								int action = event.getAction();
								int x;
								int y;
								x = (int) event.getX();
								y = (int) event.getY();
								switch (action) {
								case MotionEvent.ACTION_DOWN:
									tvTouch.setText("DOWN>>> x:" + x + "   y:"
											+ y);
									tvTouch.setBackgroundColor(getProjectedColor(
											ivResult, bm, x, y));
									break;
								case MotionEvent.ACTION_MOVE:
									tvTouch.setText("MOVE>>> x:" + x + "   y:"
											+ y);
									tvTouch.setBackgroundColor(getProjectedColor(
											ivResult, bm, x, y));
									break;

								case MotionEvent.ACTION_UP:
									tvTouch.setText("UP>>> x:" + x + "   y:"
											+ y);
									tvTouch.setBackgroundColor(getProjectedColor(
											ivResult, bm, x, y));
									break;
								default:
									break;
								}

								return true;
							}
						});
					} else {
						ToastUtil.showToast(getApplicationContext(),
								"something wrong in processing!");
					}
				} else {
					ToastUtil.showToast(getApplicationContext(),
							"please select image!");
				}
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
				tvPath.setText(uri.toString());

				try {
					bitmapMaster = BitmapFactory
							.decodeStream(getContentResolver().openInputStream(
									uri));
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

	/**
	 * 项目位置上的ImageView到位置上的位图回报的颜色上的位置
	 * 
	 */

	private int getProjectedColor(ImageView iv, Bitmap bm, int x, int y) {
		if (x < 0 || y < 0 || x > iv.getWidth() || y > iv.getHeight()) {

			return color.background_light;
		} else {
			int projectedX = (int) ((double) x * ((double) bm.getWidth() / (double) iv
					.getWidth()));
			int projectedY = (int) ((double) y * ((double) bm.getHeight() / (double) iv
					.getHeight()));

			tvTouch.setText(x + ":" + y + "/" + iv.getWidth() + " : "
					+ iv.getHeight() + "\n" + projectedX + " : " + projectedY
					+ "/" + bm.getWidth() + " : " + bm.getHeight());

			return bm.getPixel(projectedX, projectedY);
		}

	}

	/**
	 * 将图片转变为阴影
	 * 
	 * 
	 * 处理大图的时候很容易 OOM
	 * 
	 * @return
	 */
	private Bitmap ProcessingBitmap() {
		Bitmap bm1 = null;
		Bitmap newBitmap = null;
		Bitmap newShadowBitmap = null;

		try {
			bm1 = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri));

			int w = bm1.getWidth();
			int h = bm1.getHeight();

			Config config = bm1.getConfig();
			if (config == null) {
				config = Bitmap.Config.ARGB_8888;
			}

			newBitmap = Bitmap.createBitmap(w, h, config);
			Canvas newCanvas = new Canvas(newBitmap);
			newCanvas.drawColor(Color.BLACK);

			Paint paint = new Paint();
			paint.setColor(Color.WHITE);
			Rect frame = new Rect((int) (w * 0.05), (int) (w * 0.05),
					(int) (w * 0.95), (int) (h * 0.95));
			RectF frameF = new RectF(frame);
			newCanvas.drawRect(frameF, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.DARKEN));
			newCanvas.drawBitmap(bm1, 0, 0, paint);

			/*
			 * Create shadow like outer frame
			 */

			// create BLACK bitmap with same size of the image
			Bitmap bitmapFullGray = Bitmap.createBitmap(w, h, config);
			Canvas canvasFullGray = new Canvas(bitmapFullGray);
			canvasFullGray.drawColor(0xFF808080);

			// create bigger bitmap with shadow frame
			int shadowThick = 100;
			int shadowRadius = 50;
			newShadowBitmap = Bitmap.createBitmap(w + shadowThick
					+ shadowRadius, h + shadowThick + shadowRadius, config);
			Canvas newShadowCanvas = new Canvas(newShadowBitmap);
			newShadowCanvas.drawColor(Color.WHITE);

			// generate shadow
			Paint paintShadow = new Paint();
			paintShadow.setShadowLayer(shadowRadius, shadowThick, shadowThick,
					0xFF000000);
			newShadowCanvas.drawBitmap(bitmapFullGray, 0, 0, paintShadow);

			// Place the image
			paintShadow.clearShadowLayer();
			newShadowCanvas.drawBitmap(newBitmap, 0, 0, paintShadow);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newShadowBitmap;
	}

	public <V extends View> V $id(int id) {
		return (V) findViewById(id);
	}
}
