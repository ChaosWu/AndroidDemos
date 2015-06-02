package cn.android.demo.apis.ui.graphics;

import cn.android.demo.apis.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicConvolve3x3;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class AndroidBlur2 extends Activity {

	ImageView image1, image2;
	SeekBar coeff0, coeff1, coeff2;
	SeekBar coeff3, coeff4, coeff5;
	SeekBar coeff6, coeff7, coeff8;
	SeekBar devBy;
	Button btnBlur, btnOrg, btnSharpen;

	TextView textCoeff;

	float[] matrix = { 0, 0, 0, 0, 1, 0, 0, 0, 0 };

	Bitmap bitmapOriginal, bitmapCoeff;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_blur_2);

		coeff0 = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeff0);
		coeff1 = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeff1);
		coeff2 = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeff2);
		coeff3 = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeff3);
		coeff4 = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeff4);
		coeff5 = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeff5);
		coeff6 = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeff6);
		coeff7 = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeff7);
		coeff8 = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeff8);
		devBy = (SeekBar) findViewById(R.id.ui_graphics_blur_2_coeffdivby);
		textCoeff = (TextView) findViewById(R.id.ui_graphics_blur_2_textcoeff);
		btnBlur = (Button) findViewById(R.id.ui_graphics_blur_2_blur);
		btnOrg = (Button) findViewById(R.id.ui_graphics_blur_2_org);
		btnSharpen = (Button) findViewById(R.id.ui_graphics_blur_2_sharpen);
		image1 = (ImageView) findViewById(R.id.ui_graphics_blur_2_image1);
		image2 = (ImageView) findViewById(R.id.ui_graphics_blur_2_image2);

		bitmapOriginal = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);

		coeff0.setOnSeekBarChangeListener(OnCoeffChangeListener);
		coeff1.setOnSeekBarChangeListener(OnCoeffChangeListener);
		coeff2.setOnSeekBarChangeListener(OnCoeffChangeListener);
		coeff3.setOnSeekBarChangeListener(OnCoeffChangeListener);
		coeff4.setOnSeekBarChangeListener(OnCoeffChangeListener);
		coeff5.setOnSeekBarChangeListener(OnCoeffChangeListener);
		coeff6.setOnSeekBarChangeListener(OnCoeffChangeListener);
		coeff7.setOnSeekBarChangeListener(OnCoeffChangeListener);
		coeff8.setOnSeekBarChangeListener(OnCoeffChangeListener);
		devBy.setOnSeekBarChangeListener(OnCoeffChangeListener);

		ReloadImage();

		btnBlur.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				coeff0.setProgress(1 + 10);
				coeff1.setProgress(1 + 10);
				coeff2.setProgress(1 + 10);
				coeff3.setProgress(1 + 10);
				coeff4.setProgress(1 + 10);
				coeff5.setProgress(1 + 10);
				coeff6.setProgress(1 + 10);
				coeff7.setProgress(1 + 10);
				coeff8.setProgress(1 + 10);
				devBy.setProgress(9 - 1);
				ReloadImage();
			}
		});

		btnOrg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				coeff0.setProgress(0 + 10);
				coeff1.setProgress(0 + 10);
				coeff2.setProgress(0 + 10);
				coeff3.setProgress(0 + 10);
				coeff4.setProgress(1 + 10);
				coeff5.setProgress(0 + 10);
				coeff6.setProgress(0 + 10);
				coeff7.setProgress(0 + 10);
				coeff8.setProgress(0 + 10);
				devBy.setProgress(1 - 1);
				ReloadImage();
			}
		});

		btnSharpen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				coeff0.setProgress(0 + 10);
				coeff1.setProgress(-1 + 10);
				coeff2.setProgress(0 + 10);
				coeff3.setProgress(-1 + 10);
				coeff4.setProgress(5 + 10);
				coeff5.setProgress(-1 + 10);
				coeff6.setProgress(0 + 10);
				coeff7.setProgress(-1 + 10);
				coeff8.setProgress(0 + 10);
				devBy.setProgress(1 - 1);
				ReloadImage();
			}
		});
	}

	OnSeekBarChangeListener OnCoeffChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			ReloadImage();
		}
	};

	private void ReloadImage() {
		updateMatrix();
		bitmapCoeff = createBitmap_convolve(bitmapOriginal, matrix);
		image1.setImageBitmap(bitmapCoeff);
		image2.setImageBitmap(bitmapCoeff);
	}

	private void updateMatrix() {
		float div = devBy.getProgress() + 1;
		matrix[0] = (coeff0.getProgress() - 10) / div;
		matrix[1] = (coeff1.getProgress() - 10) / div;
		matrix[2] = (coeff2.getProgress() - 10) / div;
		matrix[3] = (coeff3.getProgress() - 10) / div;
		matrix[4] = (coeff4.getProgress() - 10) / div;
		matrix[5] = (coeff5.getProgress() - 10) / div;
		matrix[6] = (coeff6.getProgress() - 10) / div;
		matrix[7] = (coeff7.getProgress() - 10) / div;
		matrix[8] = (coeff8.getProgress() - 10) / div;

		textCoeff.setText(matrix[0] + " , " + matrix[1] + " , " + matrix[2]
				+ " , \n" + matrix[3] + " , " + matrix[4] + " , " + matrix[5]
				+ " , \n" + matrix[6] + " , " + matrix[7] + " , " + matrix[8]);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	private Bitmap createBitmap_convolve(Bitmap src, float[] coefficients) {

		Bitmap result = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
				src.getConfig());

		RenderScript renderScript = RenderScript.create(this);

		Allocation input = Allocation.createFromBitmap(renderScript, src);
		Allocation output = Allocation.createFromBitmap(renderScript, result);

		ScriptIntrinsicConvolve3x3 convolution = ScriptIntrinsicConvolve3x3
				.create(renderScript, Element.U8_4(renderScript));
		convolution.setInput(input);
		convolution.setCoefficients(coefficients);
		convolution.forEach(output);

		output.copyTo(result);
		renderScript.destroy();
		return result;
	}

}