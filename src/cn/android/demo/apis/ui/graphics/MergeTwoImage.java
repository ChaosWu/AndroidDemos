package cn.android.demo.apis.ui.graphics;

import java.io.FileNotFoundException;

import org.osmdroid.ResourceProxy.bitmap;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 合并两张图片
 * 
 * @author Elroy
 * 
 */
public class MergeTwoImage extends Activity {
	Button btLoadImage1;
	Button btLoadImage2;

	Button btProcessing;

	ImageView ivResult;

	TextView tvPath1;
	TextView tvPath2;

	Uri uri1;
	Uri uri2;
	final int RQS_IMAGE1 = 1;
	final int RQS_IMAGE2 = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_merge_two_image);

		btLoadImage1 = (Button) findViewById(R.id.bt_load_merge_two_loadimage1);
		btLoadImage2 = (Button) findViewById(R.id.bt_load_merge_two_loadimage2);

		btProcessing = (Button) findViewById(R.id.bt_merge_two_image_processing);

		ivResult = (ImageView) findViewById(R.id.iv_merge_two_image_result);

		tvPath1 = (TextView) findViewById(R.id.tv_merge_two_image_path1);
		tvPath2 = (TextView) findViewById(R.id.tv_merge_two_image_path2);

		btLoadImage1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, RQS_IMAGE1);
			}
		});

		btLoadImage2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, RQS_IMAGE2);
			}
		});
		btProcessing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (uri1 != null && uri2 != null) {
					Bitmap processingBitmap = processingBitmap();

					if (processingBitmap != null) {
						ivResult.setImageBitmap(processingBitmap);

					} else {
						Toast.makeText(getApplicationContext(),
								"Something wrong in processing!",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Select both image!", Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	protected Bitmap processingBitmap() {
		Bitmap bm1 = null;
		Bitmap bm2 = null;

		Bitmap newBm = null;

		try {
			bm1 = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri1));
			bm2 = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri2));

			int w;
			if (bm1.getWidth() >= bm2.getWidth()) {
				w = bm1.getWidth();
			} else {
				w = bm2.getWidth();
			}

			int h;
			if (bm1.getHeight() >= bm2.getHeight()) {
				h = bm1.getHeight();
			} else {
				h = bm2.getHeight();
			}

			Config config = bm1.getConfig();
			if (config == null) {
				config = Config.ARGB_8888;
			}
			newBm = Bitmap.createBitmap(w, h, config);
			Canvas canvas = new Canvas(newBm);
			
			canvas.drawBitmap(bm1, 0, 0, null);
		
			Paint paint=new Paint();
			paint.setAlpha(128);
			
			canvas.drawBitmap(bm2, 0, 0, paint);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newBm;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case RQS_IMAGE1:
				uri1 = data.getData();
				tvPath1.setText(uri1.toString());
				break;
			case RQS_IMAGE2:
				uri2 = data.getData();
				tvPath2.setText(uri2.toString());
				break;
			default:
				break;
			}
		}

	}
}
