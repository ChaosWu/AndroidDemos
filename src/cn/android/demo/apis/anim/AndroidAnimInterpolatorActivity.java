package cn.android.demo.apis.anim;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class AndroidAnimInterpolatorActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_interpolator);
		init();

	}

	private void init() {
		final Animation animAccelerateDecelerate = AnimationUtils
				.loadAnimation(this, R.anim.accelerate_decelerate);
		final Animation animAccelerate = AnimationUtils.loadAnimation(this,
				R.anim.accelerate);
		final Animation animAnticipate = AnimationUtils.loadAnimation(this,
				R.anim.anticipate);
		final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(
				this, R.anim.anticipate_overshoot);
		final Animation animBounce = AnimationUtils.loadAnimation(this,
				R.anim.bounce);
		final Animation animCycle = AnimationUtils.loadAnimation(this,
				R.anim.cycle);
		final Animation animDecelerate = AnimationUtils.loadAnimation(this,
				R.anim.decelerate);
		final Animation animLinear = AnimationUtils.loadAnimation(this,
				R.anim.linear);
		final Animation animOvershoot = AnimationUtils.loadAnimation(this,
				R.anim.overshoot);

		final ImageView image = (ImageView) findViewById(R.id.im_interpolator_image);
		Button btnAccelerateDecelerate = (Button) findViewById(R.id.bt_interpolator_acceleratedecelerate);
		Button btnAccelerate = (Button) findViewById(R.id.bt_interpolator_accelerate);
		Button btnAnticipate = (Button) findViewById(R.id.bt_interpolator_anticipate);
		Button btnAnticipateOvershoot = (Button) findViewById(R.id.bt_interpolator_anticipateovershoot);
		Button btnBounce = (Button) findViewById(R.id.bt_interpolator_bounce);
		Button btnCycle = (Button) findViewById(R.id.bt_interpolator_cycle);
		Button btnDecelerate = (Button) findViewById(R.id.bt_interpolator_decelerate);
		Button btnLinear = (Button) findViewById(R.id.bt_interpolator_linear);
		Button btnOvershoot = (Button) findViewById(R.id.bt_interpolator_overshoot);

		btnAccelerateDecelerate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				image.startAnimation(animAccelerateDecelerate);

			}
		});

		btnAccelerate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				image.startAnimation(animAccelerate);

			}
		});

		btnAnticipate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				image.startAnimation(animAnticipate);

			}
		});

		btnAnticipateOvershoot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				image.startAnimation(animAnticipateOvershoot);

			}
		});

		btnBounce.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				image.startAnimation(animBounce);

			}
		});

		btnCycle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				image.startAnimation(animCycle);

			}
		});

		btnDecelerate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				image.startAnimation(animDecelerate);

			}
		});

		btnLinear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				image.startAnimation(animLinear);

			}
		});

		btnOvershoot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				image.startAnimation(animOvershoot);

			}
		});

	}

}
