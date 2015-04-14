package cn.android.demo.utils;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;

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

	public static Bitmap converColorHSVColor(Bitmap src) {
		int w = src.getWidth();
		int h = src.getHeight();

		int[] srcColor = new int[w * h];
		int[] destColor = new int[w * h];

		float[] pixelHSV = new float[3];
		/*
		 * pixelHSV[0] : Hue (0 .. 360)
		 * 
		 * pixelHSV[1] : Saturation (0...1)
		 * 
		 * pixelHSV[2] : Value (0...1)
		 */

		src.getPixels(srcColor, 0, w, 0, 0, w, h);

		/*
		 * getPixels (int[] pixels, int offset, int stride, int x, int y, int
		 * width, int height) - Returns in pixels[] a copy of the data in the
		 * bitmap. Each value is a packed int representing a Color.
		 * 
		 * pixels: The array to receive the bitmap's colors offset: The first
		 * index to write into pixels[] stride: The number of entries in
		 * pixels[] to skip between rows (must be >= bitmap's width). Can be
		 * negative. x: The x coordinate of the first pixel to read from the
		 * bitmap y: The y coordinate of the first pixel to read from the bitmap
		 * width: The number of pixels to read from each row height: The number
		 * of rows to read
		 */

		int index = 0;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				// Convert from Color to HSV
				Color.colorToHSV(srcColor[index], pixelHSV);

				// Convert back from HSV to Color
				destColor[index] = Color.HSVToColor(pixelHSV);

				/*
				 * Represent Hue, Saturation and Value in separated color of R,
				 * G, B. 变色RGB
				 */

				// destColor[index] = Color.rgb((int)(pixelHSV[0] * 255/360), 0,
				// 0);
				// destColor[index] = Color.rgb(0, (int)(pixelHSV[1] * 255), 0);
				// destColor[index] = Color.rgb(0, 0, (int)(pixelHSV[2] * 255));

				index++;
			}
		}

		Config config = src.getConfig();
		/*
		 * If the bitmap's internal config is in one of the public formats,
		 * return that config, otherwise return null.
		 */

		if (config == null) {
			config = Config.RGB_565;

		}
		Bitmap newBitmap = Bitmap.createBitmap(destColor, w, h, config);
		return newBitmap;
	}
}
