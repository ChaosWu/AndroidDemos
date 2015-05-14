package cn.android.demo.apis.ui.graphics;

import java.io.FileNotFoundException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 设置镜像效果
 * 
 * @author Elroy
 * 
 */
public class MirrorBitmapWithMatrix extends Activity {

	Button button;
	TextView textView;
	ImageView imageView;

	Uri uri;
	Canvas canvas;
	Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_mirror_bitmap_with_matrix);

		button = $id(R.id.mirror_bitmap_loadimage);
		textView = $id(R.id.mirror_bitmap_sourceuri);
		imageView = $id(R.id.mirror_bitmap_result);

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

		Bitmap tempBitmap;
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1:
				uri = data.getData();
				textView.setText(uri.toString());

				try {
					tempBitmap = BitmapFactory
							.decodeStream(getContentResolver().openInputStream(
									uri));

					// create a mirror bitmap
					Matrix matrixMirror = new Matrix();
					matrixMirror.preScale(-1.0f, 1.0f);
					Bitmap bitmapMirror = Bitmap.createBitmap(tempBitmap, 0, 0,
							tempBitmap.getWidth(), tempBitmap.getHeight(),
							matrixMirror, false);

					// define half/half area
					Rect rect1Half = new Rect(0, 0, tempBitmap.getWidth() / 2,
							tempBitmap.getHeight());
					Rect rect2Half = new Rect((tempBitmap.getWidth() / 2) + 1,
							0, tempBitmap.getWidth(), tempBitmap.getHeight());

					Config config = tempBitmap.getConfig();
					if (config == null) {
						config = Bitmap.Config.ARGB_8888;
					}

					// craete the master bitmap
					bitmap = Bitmap.createBitmap(tempBitmap.getWidth(),
							tempBitmap.getHeight(), config);
					canvas = new Canvas(bitmap);

					// merge bitmap half/half
					canvas.drawBitmap(tempBitmap, rect1Half, rect1Half, null);
					canvas.drawBitmap(bitmapMirror, rect2Half, rect2Half, null);

					imageView.setImageBitmap(bitmap);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
		}
	}

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}
}
