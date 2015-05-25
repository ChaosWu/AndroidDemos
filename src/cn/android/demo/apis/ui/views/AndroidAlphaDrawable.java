package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * 通过Alpha 设置透明度
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class AndroidAlphaDrawable extends Activity {
	SeekBar barOpacityButton;
	SeekBar barOpacityDrawableLeft, barOpacityDrawableTop,
			barOpacityDrawableRight, barOpacityDrawableBottom;
	Button button;
	Drawable drawableLeft, drawableTop, drawableRight, drawableBottom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_alpha_drawable);

		drawableLeft = getResources().getDrawable(
				android.R.drawable.ic_menu_call);
		drawableTop = getResources().getDrawable(
				android.R.drawable.ic_menu_agenda);
		drawableRight = getResources().getDrawable(
				android.R.drawable.ic_menu_compass);
		drawableBottom = getResources().getDrawable(
				android.R.drawable.ic_menu_camera);

		button = (Button) findViewById(R.id.alpha_drawable_button);

		// display CompoundDrawables on Button programmatically
		button.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
				drawableTop, drawableRight, drawableBottom);

		barOpacityButton = (SeekBar) findViewById(R.id.alpha_drawable_opacitybutton);
		barOpacityDrawableLeft = (SeekBar) findViewById(R.id.alpha_drawable_opacitydrawableleft);
		barOpacityDrawableTop = (SeekBar) findViewById(R.id.alpha_drawable_opacitydrawabletop);
		barOpacityDrawableRight = (SeekBar) findViewById(R.id.alpha_drawable_opacitydrawableright);
		barOpacityDrawableBottom = (SeekBar) findViewById(R.id.alpha_drawable_opacitydrawablebottom);

		barOpacityButton
				.setOnSeekBarChangeListener(barOpacityButtonOnSeekBarChangeListener);

		barOpacityDrawableLeft
				.setOnSeekBarChangeListener(barOpacityDrawableOnSeekBarChangeListener);
		barOpacityDrawableTop
				.setOnSeekBarChangeListener(barOpacityDrawableOnSeekBarChangeListener);
		barOpacityDrawableRight
				.setOnSeekBarChangeListener(barOpacityDrawableOnSeekBarChangeListener);
		barOpacityDrawableBottom
				.setOnSeekBarChangeListener(barOpacityDrawableOnSeekBarChangeListener);
	}

	OnSeekBarChangeListener barOpacityButtonOnSeekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			float alpha = (float) progress / (float) seekBar.getMax();
			button.setAlpha(alpha); // for API Level 11+
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

	};

	OnSeekBarChangeListener barOpacityDrawableOnSeekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			drawableLeft.setAlpha(barOpacityDrawableLeft.getProgress());
			drawableTop.setAlpha(barOpacityDrawableTop.getProgress());
			drawableRight.setAlpha(barOpacityDrawableRight.getProgress());
			drawableBottom.setAlpha(barOpacityDrawableBottom.getProgress());

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

	};

}
