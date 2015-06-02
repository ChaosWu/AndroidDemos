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
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.ScriptIntrinsicConvolve3x3;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * API Level 17
 * 
 * @author Elroy
 * 
 */
public class AndroidBlur extends Activity {

	private SeekBar bar;
	private ImageView imageView;
	private Bitmap bm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_blur);
		bar = (SeekBar) findViewById(R.id.ui_graphics_sb_blur);
		imageView = (ImageView) findViewById(R.id.ui_graphics_imageview);

		bm = BitmapFactory.decodeResource(getResources(), R.drawable.chaoswu);
		imageView.setImageBitmap(bm);

		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float radius = (float) bar.getProgress();
				imageView.setImageBitmap(createBitmapBlur(bm, radius));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});

	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	protected Bitmap createBitmapBlur(Bitmap src, float r) {
		// Radius range (0 < r <= 25)
		if (r <= 0) {
			r = 0.1f;
		} else if (r > 25) {
			r = 25.0f;
		}
		
		Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
				Bitmap.Config.ARGB_8888);

		RenderScript renderScript = RenderScript.create(this);

		Allocation blurInput = Allocation.createFromBitmap(renderScript, src);
		Allocation blurOutput = Allocation.createFromBitmap(renderScript,
				bitmap);

		ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript,
				Element.U8_4(renderScript));
		blur.setInput(blurInput);
		blur.setRadius(r);
		blur.forEach(blurOutput);

		blurOutput.copyTo(bitmap);
		renderScript.destroy();

		return bitmap;
	}
}
