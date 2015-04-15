package cn.android.demo.apis.anim;


import com.nineoldandroids.animation.Animator;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;

public class AndroidAnimMatrix extends Activity {

	Button button;
	ImageView imageView;
	ImageView imageView2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_matrix);

		button = (Button) findViewById(R.id.bt_start_anim_matrix);
		imageView = (ImageView) findViewById(R.id.iv_anim_matrix);
		imageView2 = (ImageView) findViewById(R.id.iv_anim_matrix2);

		button.setOnClickListener(clickListener);
		imageView.setOnClickListener(clickListener);
		imageView2.setOnClickListener(clickListener);

	}

	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			applyAnim(v);
		}
	};

	protected void applyAnim(View v) {
		MyAnim anim = new MyAnim();
		anim.setDuration(5000);
		anim.setFillAfter(true);
		anim.setInterpolator(new OvershootInterpolator());
		v.startAnimation(anim);
	}

	public class MyAnim extends Animation {
		@Override
		protected void applyTransformation(float interpolatedTime,
				Transformation t) {
			super.applyTransformation(interpolatedTime, t);

			Matrix matrix = t.getMatrix();
			matrix.setScale(interpolatedTime, interpolatedTime);
		}

	}
}
