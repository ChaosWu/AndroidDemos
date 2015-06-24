package cn.android.demo.apis.anim;

import java.util.Random;

import cn.android.demo.apis.R;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 动画移动轨迹+打乱图片顺序
 * 
 * @author Elroy
 * 
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AndroidAnimImageMove extends Activity implements OnClickListener {
	private ImageView[] mImageViews = new ImageView[9];
	private Button shuffle_button;
	private Button shift_button;

	private boolean mShifted;

	private int mStartIndex;
	private Random random = new Random();
	private int[] mImageIds = { R.drawable.p1, R.drawable.p2, R.drawable.p3,
			R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7,
			R.drawable.p8, R.drawable.p9 };
	View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_imageview_move);
		view = findViewById(R.id.rl_anim_imageview_move);

		shuffle_button = (Button) findViewById(R.id.shuffle_button);
		shift_button = (Button) findViewById(R.id.shift_button);

		mImageViews[0] = (ImageView) findViewById(R.id.anim_imageview_move_image0);
		mImageViews[1] = (ImageView) findViewById(R.id.anim_imageview_move_image1);
		mImageViews[2] = (ImageView) findViewById(R.id.anim_imageview_move_image2);
		mImageViews[3] = (ImageView) findViewById(R.id.anim_imageview_move_image3);
		mImageViews[4] = (ImageView) findViewById(R.id.anim_imageview_move_image4);
		mImageViews[5] = (ImageView) findViewById(R.id.anim_imageview_move_image5);
		mImageViews[6] = (ImageView) findViewById(R.id.anim_imageview_move_image6);
		mImageViews[7] = (ImageView) findViewById(R.id.anim_imageview_move_image7);
		mImageViews[8] = (ImageView) findViewById(R.id.anim_imageview_move_image8);

		shuffle_button.setOnClickListener(this);
		shift_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shuffle_button:
			shuffle();
			break;
		case R.id.shift_button:
			shift(v);
			break;
		default:
			break;
		}
	}

	private ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
		@Override
		public void onAnimationUpdate(ValueAnimator animation) {
			view.invalidate();
		}
	};

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void shift(View view) {
		if (!mShifted) {
			for (ImageView imageView : mImageViews) {
				ObjectAnimator tx = ObjectAnimator.ofFloat(imageView,
						View.TRANSLATION_X, (random.nextFloat() - 0.5f) * 500);
				tx.addUpdateListener(listener);
				ObjectAnimator ty = ObjectAnimator.ofFloat(imageView,
						View.TRANSLATION_Y, (random.nextFloat() - 0.5f) * 500);
				ty.addUpdateListener(listener);
				AnimatorSet set = new AnimatorSet();
				set.playTogether(tx, ty);
				set.setDuration(3000);
				set.setInterpolator(new OvershootInterpolator());
				set.addListener(new AnimationEndListener(imageView));
				set.start();
			}
			mShifted = true;
		} else {
			for (ImageView imageView : mImageViews) {
				ObjectAnimator tx = ObjectAnimator.ofFloat(imageView,
						View.TRANSLATION_X, 0);
				tx.addUpdateListener(listener);
				ObjectAnimator ty = ObjectAnimator.ofFloat(imageView,
						View.TRANSLATION_Y, 0);
				ty.addUpdateListener(listener);
				AnimatorSet set = new AnimatorSet();
				set.playTogether(tx, ty);
				set.setDuration(3000);
				set.setInterpolator(new OvershootInterpolator());
				set.addListener(new AnimationEndListener(imageView));
				set.start();
			}
			mShifted = false;
		}
	}

	private void shuffle() {
		// 随机选择一个不同的开始可用图像阵列中
		int newStartIndex;
		do {
			newStartIndex = mImageIds[random.nextInt(mImageIds.length)];
		} while (newStartIndex == mStartIndex);
		mStartIndex = newStartIndex;

		// 更新中包含的模糊视图图像视图的图像
		for (int i = 0; i < mImageViews.length; i++) {
			int drawableId = mImageIds[(mStartIndex + i) % mImageIds.length];
			mImageViews[i].setImageDrawable(getResources().getDrawable(
					drawableId));
		}
		view.invalidate();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private static class AnimationEndListener implements AnimatorListener {

		View mView;

		public AnimationEndListener(View v) {
			mView = v;
		}

		@Override
		public void onAnimationStart(Animator animation) {
			mView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		}

		@Override
		public void onAnimationEnd(Animator animation) {
			mView.setLayerType(View.LAYER_TYPE_NONE, null);
		}

		@Override
		public void onAnimationCancel(Animator animation) {
			mView.setLayerType(View.LAYER_TYPE_NONE, null);
		}

		@Override
		public void onAnimationRepeat(Animator animation) {

		}
	}

}
