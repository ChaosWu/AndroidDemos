package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class FllowerAnimationViewActivity extends Activity {
	private RelativeLayout relativeLayout;
	private FllowerAnimation fllowerView;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_widget_fllower_animation_view);
		relativeLayout = (RelativeLayout) findViewById(R.id.fllower_anim_layout);
		relativeLayout.setVisibility(View.VISIBLE);

		fllowerView = new FllowerAnimation(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		fllowerView.setLayoutParams(params);

		relativeLayout.addView(fllowerView);

		button = (Button) findViewById(R.id.but_run_fllower);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				fllowerView.startAnimation();
			}
		});
	}

}
