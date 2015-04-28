package cn.android.demo.apis.anim;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * TASR: translate alpha scale rotate
 * 
 * @author Elroy
 * 
 */
public class AndroidAnimButtonTASR extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_button_tasr);

		final Animation animTranslate = AnimationUtils.loadAnimation(this,
				R.anim.anim_translate);
		final Animation animAlpha = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);
		final Animation animScale = AnimationUtils.loadAnimation(this,
				R.anim.anim_scale);
		final Animation animRotate = AnimationUtils.loadAnimation(this,
				R.anim.anim_rotate);

		final Animation scaleXY = AnimationUtils.loadAnimation(this, R.anim.scale_xy);

		Button btT = (Button) findViewById(R.id.bt_translate);
		Button btA = (Button) findViewById(R.id.bt_alpha);
		Button btS = (Button) findViewById(R.id.bt_scale);
		Button btR = (Button) findViewById(R.id.bt_rotate);

		final TextView tvScaleXy = (TextView) findViewById(R.id.tv_anim_scale_xy);
		tvScaleXy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tvScaleXy.startAnimation(scaleXY);
			}
		});

		btT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animTranslate);
			}
		});

		btA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animAlpha);
			}
		});
		btS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animScale);
			}
		});
		btR.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animRotate);
			}
		});

	}
}
