package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewAnimator;

/**
 * ViewSwitcher
 * 
 * ImageSwitcher
 * 
 * TextSwitcher
 * 
 * ViewFilipper
 * 
 * 
 * 
 * @author Elroy
 * 
 */
public class AndroidViewSwitcher extends Activity {
	Button btPrev;
	Button btNext;
	ViewAnimator viewAnimator;

	Animation slideLeft;
	Animation slideRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_view_switcher);

		btNext = (Button) findViewById(R.id.bt_view_switcher_next);
		btPrev = (Button) findViewById(R.id.bt_view_switcher_prev);

		viewAnimator = (ViewAnimator) findViewById(R.id.vs_viewswitcher);

		slideLeft = AnimationUtils.loadAnimation(this, R.anim.flip_in);
		slideRight = AnimationUtils.loadAnimation(this, R.anim.flip_out);

		viewAnimator.setInAnimation(slideLeft);
		viewAnimator.setOutAnimation(slideRight);

		btPrev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewAnimator.showPrevious();
			}
		});

		btNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewAnimator.showNext();
			}
		});

	}
}
