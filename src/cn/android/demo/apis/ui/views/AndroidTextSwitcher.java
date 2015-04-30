package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class AndroidTextSwitcher extends Activity {
	Button buttonNext;
	TextSwitcher textSwitcher;
	Animation slide_in_left, slide_out_right;

	String[] TextToSwitched = { "Sunday", "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday" };
	int curIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_view_android_text_switcher);

		buttonNext = (Button) findViewById(R.id.bt_text_switcher_next);
		textSwitcher = (TextSwitcher) findViewById(R.id.ts_textswitcher);

		slide_in_left = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left);
		slide_out_right = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);

		textSwitcher.setInAnimation(slide_in_left);
		textSwitcher.setOutAnimation(slide_out_right);
		textSwitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				TextView textView = new TextView(AndroidTextSwitcher.this);
				textView.setTextSize(30);
				textView.setTextColor(Color.RED);
				textView.setGravity(Gravity.CENTER_HORIZONTAL);
				textView.setTypeface(Typeface.DEFAULT_BOLD);
				textView.setShadowLayer(10, 10, 10, Color.BLACK);
				return textView;
			}
		});
		curIndex = 0;
		textSwitcher.setText(TextToSwitched[curIndex]);

		buttonNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (curIndex == TextToSwitched.length - 1) {
					curIndex = 0;
					textSwitcher.setText(TextToSwitched[curIndex]);
				} else {
					textSwitcher.setText(TextToSwitched[++curIndex]);
				}
			}
		});
	}

}
