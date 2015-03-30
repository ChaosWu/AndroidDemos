package cn.android.demo.apis.ui.graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class SecondBitmapPixels extends Activity {
	Bitmap bitmap;
	FileInputStream fis;
	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageView = new ImageView(this);
		setContentView(imageView);

		try {
			fis = openFileInput("BITMAP_A");
			bitmap = BitmapFactory.decodeStream(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		imageView.setImageBitmap(swapBitmap(bitmap));

	}

	private Bitmap swapBitmap(Bitmap bm) {
		int width = bm.getWidth();
		int height = bm.getHeight();

		final Bitmap bitmap = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		int[] srcPixels = new int[width * height];
		bm.getPixels(srcPixels, 0, width, 0, 0, width, height);
		int[] destPixels = new int[width * height];

		BitmapPixels.swapRB (srcPixels, destPixels);
		bitmap.setPixels(destPixels, 0, width, 0, 0, width, height);
		//imageView.setImageBitmap(bitmap);
		return bitmap;
	}
}
