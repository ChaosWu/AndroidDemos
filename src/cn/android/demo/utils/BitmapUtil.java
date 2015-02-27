package cn.android.demo.utils;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
	public static Bitmap LoadImage(String URL, BitmapFactory.Options options) {
		Bitmap bitmap = null;
		InputStream inputStream = null;

		try {
			inputStream = HttpUtil.OpenHttpConnection(URL);
			bitmap = BitmapFactory.decodeStream(inputStream, null, options);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bitmap;

	}
}
