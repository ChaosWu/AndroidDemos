package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class AndroidViewFlipper extends Activity implements OnClickListener {
	private ViewFlipper flipper;
	private Button bt1;
	private Button bt2;

	private Button autoFlipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_viewflipper);
		flipper = (ViewFlipper) findViewById(R.id.viewflipper);

		bt1 = (Button) findViewById(R.id.button1);
		bt2 = (Button) findViewById(R.id.button2);

		autoFlipper = (Button) findViewById(R.id.bt_auto_flipper);

		Animation animationFlipIn = AnimationUtils.loadAnimation(this,
				R.anim.flip_in);
		Animation animationFlipOut = AnimationUtils.loadAnimation(this,
				R.anim.flip_out);

		flipper.setFlipInterval(500);
		flipper.setInAnimation(animationFlipIn);
		flipper.setOutAnimation(animationFlipOut);

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		autoFlipper.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			flipper.showNext();
			break;
		case R.id.button2:
			flipper.showPrevious();
			break;
		case R.id.bt_auto_flipper:
			// Returns true if the child views are flipping.
			if (flipper.isFlipping()) {
				flipper.stopFlipping();
				autoFlipper.setText("开始自动翻转");
			} else {
				flipper.startFlipping();
				autoFlipper.setText("停止自动翻转");
			}

			break;
		default:
			break;
		}
	}
}
