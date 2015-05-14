package cn.android.demo.apis.ui.graphics;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class BlurBitmap extends Activity {
	Button load;
	ImageView result;
	ImageView original;
	TextView dur;

	Spinner sp;

	Uri source;
	Bitmap bitmapMaster;
	Canvas canvasMaster;

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_graphics_blur_bitmap);
		load = $id(R.id.blur_bm_loadimage);
		original = $id(R.id.blur_bm_original);
		result = $id(R.id.blur_bm_result);
		dur = $id(R.id.blur_bm_dur);

		load.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK,
						Media.EXTERNAL_CONTENT_URI);

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
				source = data.getData();

				try {
					bitmapMaster = BitmapFactory
							.decodeStream(getContentResolver().openInputStream(
									source));

					original.setImageBitmap(bitmapMaster);
					// loadBlurBitmap(bitmapMaster);

					loadBlurMatrix(bitmapMaster);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
		}
	}

	private void loadBlurMatrix(Bitmap src) {
		if (src != null) {
			Bitmap bmBlur;
			Convolution convolution = new Convolution();

			long startTime = System.currentTimeMillis();
			bmBlur = convolution.convBitmap(src);
			long duration = System.currentTimeMillis() - startTime;

			result.setImageBitmap(bmBlur);
			dur.setText("processing duration(ms): " + duration);
		}
	}

	class Convolution {
		// matrix to blur image
		// int[][] matrix = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		int[][] matrix = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
		// kernal_width x kernal_height = dimension pf matrix
		// halfWidth = (kernal_width - 1)/2
		// halfHeight = (kernal_height - 1)/2
		int kernal_width = 3;
		int kernal_height = 3;
		int kernal_halfWidth = 1;
		int kernal_halfHeight = 1;

		public Bitmap convBitmap(Bitmap src) {

			int[][] sourceMatrix = new int[kernal_width][kernal_height];

			// averageWeight = total of matrix[][]. The result of each
			// pixel will be divided by averageWeight to get the average
			int averageWeight = 0;
			for (int i = 0; i < kernal_width; i++) {
				for (int j = 0; j < kernal_height; j++) {
					averageWeight += matrix[i][j];
				}
			}
			if (averageWeight == 0) {
				averageWeight = 1;
			}

			int pixelR, pixelG, pixelB, pixelA;

			int w = src.getWidth();
			int h = src.getHeight();
			Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

			// fill sourceMatrix with surrounding pixel
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {

					for (int xk = 0; xk < kernal_width; xk++) {
						for (int yk = 0; yk < kernal_height; yk++) {
							int px = x + xk - kernal_halfWidth;
							int py = y + yk - kernal_halfHeight;

							if (px < 0) {
								px = 0;
							} else if (px >= w) {
								px = w - 1;
							}

							if (py < 0) {
								py = 0;
							} else if (py >= h) {
								py = h - 1;
							}

							sourceMatrix[xk][yk] = src.getPixel(px, py);

						}
					}

					pixelR = pixelG = pixelB = pixelA = 0;

					for (int k = 0; k < kernal_width; k++) {
						for (int l = 0; l < kernal_height; l++) {

							pixelR += Color.red(sourceMatrix[k][l])
									* matrix[k][l];
							pixelG += Color.green(sourceMatrix[k][l])
									* matrix[k][l];
							pixelB += Color.blue(sourceMatrix[k][l])
									* matrix[k][l];
							pixelA += Color.alpha(sourceMatrix[k][l])
									* matrix[k][l];
						}
					}
					pixelR = pixelR / averageWeight;
					pixelG = pixelG / averageWeight;
					pixelB = pixelB / averageWeight;
					pixelA = pixelA / averageWeight;

					if (pixelR < 0) {
						pixelR = 0;
					} else if (pixelR > 255) {
						pixelR = 255;
					}

					if (pixelG < 0) {
						pixelG = 0;
					} else if (pixelG > 255) {
						pixelG = 255;
					}

					if (pixelB < 0) {
						pixelB = 0;
					} else if (pixelB > 255) {
						pixelB = 255;
					}

					if (pixelA < 0) {
						pixelA = 0;
					} else if (pixelA > 255) {
						pixelA = 255;
					}

					bm.setPixel(x, y,
							Color.argb(pixelA, pixelR, pixelG, pixelB));
				}
			}

			return bm;
		}
	}

	private void loadBlurBitmap(Bitmap src) {
		if (src != null) {

			Bitmap bmBlur;

			long startTime = System.currentTimeMillis();
			bmBlur = getBlurBitmap(src);
			long duration = System.currentTimeMillis() - startTime;

			result.setImageBitmap(bmBlur);
			dur.setText("processing duration(ms): " + duration);
		}
	}

	private Bitmap getBlurBitmap(Bitmap src) {

		final int widthKernal = 5;
		final int heightKernal = 5;

		int w = src.getWidth();
		int h = src.getHeight();

		Bitmap blurBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {

				int r = 0;
				int g = 0;
				int b = 0;
				int a = 0;

				for (int xk = 0; xk < widthKernal; xk++) {
					for (int yk = 0; yk < heightKernal; yk++) {
						int px = x + xk - 2;
						int py = y + yk - 2;

						if (px < 0) {
							px = 0;
						} else if (px >= w) {
							px = w - 1;
						}

						if (py < 0) {
							py = 0;
						} else if (py >= h) {
							py = h - 1;
						}

						int intColor = src.getPixel(px, py);
						r += Color.red(intColor);
						g += Color.green(intColor);
						b += Color.blue(intColor);
						a += Color.alpha(intColor);

					}
				}

				blurBitmap.setPixel(x, y,
						Color.argb(a / 25, r / 25, g / 25, b / 25));

			}
		}

		return blurBitmap;
	}

}