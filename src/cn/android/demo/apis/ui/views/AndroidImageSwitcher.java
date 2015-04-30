package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class AndroidImageSwitcher extends Activity {

	Button buttonNext;
	ImageSwitcher imageSwitcher;
	Animation slide_in_left, slide_out_right;
	int imageResources[] = { android.R.drawable.ic_dialog_alert,
			android.R.drawable.ic_dialog_dialer,
			android.R.drawable.ic_dialog_email,
			android.R.drawable.ic_dialog_info, android.R.drawable.ic_dialog_map };

	int curIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_image_switcher);
		buttonNext = (Button) findViewById(R.id.bt_image_switcher_next);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.is_imageswitcher);
		slide_in_left = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left);
		slide_out_right = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);

		imageSwitcher.setInAnimation(slide_in_left);
		imageSwitcher.setOutAnimation(slide_out_right);

		imageSwitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {

				ImageView imageView = new ImageView(AndroidImageSwitcher.this);
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

				ImageSwitcher.LayoutParams params = new ImageSwitcher.LayoutParams(
						ImageSwitcher.LayoutParams.MATCH_PARENT,
						ImageSwitcher.LayoutParams.MATCH_PARENT);

				imageView.setLayoutParams(params);
				return imageView;
			}
		});

		curIndex = 0;
		imageSwitcher.setImageResource(imageResources[curIndex]);
		buttonNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (curIndex == imageResources.length - 1) {
					curIndex = 0;
					imageSwitcher.setImageResource(imageResources[curIndex]);
				} else {
					imageSwitcher.setImageResource(imageResources[++curIndex]);
				}
			}
		});
	}
}
