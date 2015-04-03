package cn.android.demo.apis.anim;

import cn.android.demo.apis.R;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

/**
 * ObjectAnimation使用 通过结果的得出，
 * 
 * 1：animator 是对 view属性发生了变化
 * 
 * 2：animation 只是图片移动或者变化，view属性没有随之变化；
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class AndroidObjectAnimation extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_object_animation);
		Button animator = (Button) findViewById(R.id.bt_ob_animatorbutton);
		Button animation = (Button) findViewById(R.id.bt_ob_animationbutton);

		ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(animator,
				"translationX", 0f, 400f);
		objectAnimator.setDuration(1000);
		objectAnimator.start();

		AnimationSet animationSet = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1f,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);

		animationSet.addAnimation(translateAnimation);
		animationSet.setDuration(500);
		animationSet.setFillAfter(true);
		animation.setAnimation(animationSet);

		animator.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"Animator Button Clicked", Toast.LENGTH_SHORT).show();
			}
		});

		animation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"Animation Button Clicked", Toast.LENGTH_SHORT).show();
			}
		});

	}
}
