package cn.android.demo.apis.ui.graphics;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 拼图游戏 切图方法， 图片变换颜色方法
 * 
 * @author Elroy
 * 
 */
public class BitmapPixels extends Activity {
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private ImageView imageView5;

	private Button button;

	int width;
	int height;
	int[] srcPixels;
	int[] destPixels;
	Bitmap bm;
	Bitmap bm5;
	int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_bitmap_pixels);

		imageView1 = (ImageView) findViewById(R.id.iv_bitmap_pixels_1);
		imageView2 = (ImageView) findViewById(R.id.iv_bitmap_pixels_2);
		imageView3 = (ImageView) findViewById(R.id.iv_bitmap_pixels_3);
		imageView4 = (ImageView) findViewById(R.id.iv_bitmap_pixels_4);
		imageView5 = (ImageView) findViewById(R.id.iv_bitmap_pixels_5);

		button = (Button) findViewById(R.id.bt_bitmap_pixels);

		bm = BitmapFactory.decodeResource(getResources(), R.drawable.chaoswu);

		width = bm.getWidth();
		height = bm.getHeight();

		imageView1.setImageBitmap(bm);

		int halfW = width / 2;
		int halfH = height / 2;
		// filter true if the source should be filtered.
		Bitmap halfBm = Bitmap.createScaledBitmap(bm, halfW, halfH, false);
		imageView2.setImageBitmap(halfBm);

		Bitmap bm3 = Bitmap.createBitmap(halfW, halfH, Config.ARGB_8888);
		int[] pixels3 = new int[halfW * halfH];

		// pixels The array to receive the bitmap's colors该阵列接收位图的颜色
		// offset The first index to write into pixels[]第一个指标写入像素[]
		// stride The number of entries in pixels[] to skip between rows (must
		// be >= bitmap's width). Can be negative.
		// x The x coordinate of the first pixel to read from the bitmap
		// y The y coordinate of the first pixel to read from the bitmap
		// width The number of pixels to read from each row
		// height The number of rows to read

		bm.getPixels(pixels3, 0, halfW, 0, 0, halfW, halfH);
		bm3.setPixels(pixels3, 0, halfW, 0, 0, halfW, halfH);
		imageView3.setImageBitmap(bm3);

		Bitmap bm4 = Bitmap.createBitmap(halfW, halfH, Config.ARGB_8888);
		int[] pixels4 = new int[halfW * halfH];
		bm.getPixels(pixels4, 0, halfW, halfW / 2, halfH / 2, halfW, halfH);
		bm4.setPixels(pixels4, 0, halfW, 0, 0, halfW, halfH);
		imageView4.setImageBitmap(bm4);

		bm5 = Bitmap.createBitmap(halfW, halfH, Config.ARGB_8888);
		int[] pixels5 = new int[halfW * halfH];
		bm.getPixels(pixels5, 0, halfW, halfW, halfH, halfW, halfH);
		bm5.setPixels(pixels5, 0, halfW, 0, 0, halfW, halfH);
		imageView5.setImageBitmap(bm5);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FileOutputStream fos;
				// 通过自带的文件读取 ，传递图片数据
				try {
					fos = openFileOutput("BITMAP_A", Context.MODE_PRIVATE);
					bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
					fos.close();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				Intent intent = new Intent(BitmapPixels.this,
						SecondBitmapPixels.class);
				startActivity(intent);
			}
		});

	}

	public static void swapGB(int[] src, int[] dest) {
		for (int i = 0; i < src.length; i++) {
			dest[i] = (src[i] & 0xffff0000) | ((src[i] & 0x000000ff) << 8)
					| ((src[i] & 0x0000ff00) >> 8);
		}
	}

	public static void swapRB(int[] src, int[] dest) {
		for (int i = 0; i < src.length; i++) {
			dest[i] = (src[i] & 0xff00ff00) | ((src[i] & 0x000000ff) << 16)
					| ((src[i] & 0x00ff0000) >> 16);
		}
	}

	public static void swapRG(int[] src, int[] dest) {
		for (int i = 0; i < src.length; i++) {
			dest[i] = (src[i] & 0xff0000ff) | ((src[i] & 0x0000ff00) << 8)
					| ((src[i] & 0x00ff0000) >> 8);
		}
	}

	// TODO图片变色方法
	// ImageView image1, image2, image3, image4;
	//
	// /** Called when the activity is first created. */
	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.main);
	// image1 = (ImageView)findViewById(R.id.image1);
	// image2 = (ImageView)findViewById(R.id.image2);
	// image3 = (ImageView)findViewById(R.id.image3);
	// image4 = (ImageView)findViewById(R.id.image4);
	//
	// Bitmap bmOriginal = BitmapFactory.decodeResource(getResources(),
	// R.drawable.icon);
	// image1.setImageBitmap(bmOriginal);
	//
	// int width = bmOriginal.getWidth();
	// int height = bmOriginal.getHeight();
	// int halfWidth = width/2;
	// int halfHeight = height/2;
	//
	// Bitmap bmDulicated2 = Bitmap.createBitmap(width, height,
	// Bitmap.Config.ARGB_8888);
	// Bitmap bmDulicated3 = Bitmap.createBitmap(width, height,
	// Bitmap.Config.ARGB_8888);
	// Bitmap bmDulicated4 = Bitmap.createBitmap(width, height,
	// Bitmap.Config.ARGB_8888);
	// int[] srcPixels = new int[width * height];
	// bmOriginal.getPixels(srcPixels, 0, width, 0, 0, width, height);
	// int[] destPixels = new int[width * height];
	//
	// swapGB(srcPixels, destPixels);
	// bmDulicated2.setPixels(destPixels, 0, width, 0, 0, width, height);
	// image2.setImageBitmap(bmDulicated2);
	//
	// swapRB(srcPixels, destPixels);
	// bmDulicated3.setPixels(destPixels, 0, width, 0, 0, width, height);
	// image3.setImageBitmap(bmDulicated3);
	//
	// swapRG(srcPixels, destPixels);
	// bmDulicated4.setPixels(destPixels, 0, width, 0, 0, width, height);
	// image4.setImageBitmap(bmDulicated4);
	//
	//
	// }
	//
	// void swapGB(int[] src, int[] dest){
	// for(int i = 0; i < src.length; i++){
	// dest[i] = (src[i] & 0xffff0000)
	// | ((src[i] & 0x000000ff)<<8)
	// | ((src[i] & 0x0000ff00)>>8);
	// }
	// }
	//
	// void swapRB(int[] src, int[] dest){
	// for(int i = 0; i < src.length; i++){
	// dest[i] = (src[i] & 0xff00ff00)
	// | ((src[i] & 0x000000ff)<<16)
	// | ((src[i] & 0x00ff0000)>>16);
	// }
	// }
	//
	// void swapRG(int[] src, int[] dest){
	// for(int i = 0; i < src.length; i++){
	// dest[i] = (src[i] & 0xff0000ff)
	// | ((src[i] & 0x0000ff00)<<8)
	// | ((src[i] & 0x00ff0000)>>8);
	// }
	// }

}
