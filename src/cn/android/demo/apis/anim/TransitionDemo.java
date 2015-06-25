package cn.android.demo.apis.anim;

import cn.android.demo.apis.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class TransitionDemo extends Activity {
	LinearLayout root;
	View red, blue, green, yellow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_transition_demo);
		root = (LinearLayout) findViewById(R.id.transition_root_view);

		red = findViewById(R.id.transition_red_500);
		blue = findViewById(R.id.transition_blue_500);
		green = findViewById(R.id.transition_green_500);
		yellow = findViewById(R.id.transition_yellow_500);

		root.setOnClickListener(new OnClickListener() {

			@TargetApi(Build.VERSION_CODES.KITKAT)
			@Override
			public void onClick(View v) {
				TransitionManager.beginDelayedTransition(root, new Fade());
				toggleVisibility(red, blue, green, yellow);
			}
		});
	}

	protected void toggleVisibility(View... views) {
		for (View view : views) {
			boolean isVisible = view.getVisibility() == View.VISIBLE;
			view.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
		}
	}
}
