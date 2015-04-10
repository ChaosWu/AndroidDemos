package cn.android.demo.apis.anim;

import com.nineoldandroids.animation.Animator;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

/**
 * 循环动画播放
 * 
 * @author Elroy
 * 
 */
public class AndroidImageViewSlidedown extends Activity {

	ImageView imageView1;
	ImageView imageView2;
	ImageView imageView3;

	ImageView curSlidingImage;

	Animation animSlideDownIn;
	Animation animSlideDownOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_imageview_slidedown);

		imageView1 = (ImageView) findViewById(R.id.anim_slidedown_image1);
		imageView2 = (ImageView) findViewById(R.id.anim_slidedown_image2);
		imageView3 = (ImageView) findViewById(R.id.anim_slidedown_image3);

		animSlideDownIn = AnimationUtils.loadAnimation(this,
				R.anim.slidedown_in);
		animSlideDownOut = AnimationUtils.loadAnimation(this,
				R.anim.slidedown_out);

		animSlideDownIn.setAnimationListener(animSlideInListener);
		animSlideDownOut.setAnimationListener(animSlideOutListener);

		curSlidingImage = imageView1;

		imageView1.startAnimation(animSlideDownIn);
		imageView1.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		imageView1.clearAnimation();
		imageView2.clearAnimation();
		imageView3.clearAnimation();
	}

	AnimationListener animSlideInListener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			if (curSlidingImage == imageView1) {
				imageView1.startAnimation(animSlideDownOut);
			} else if (curSlidingImage == imageView2) {
				imageView2.startAnimation(animSlideDownOut);
			} else if (curSlidingImage == imageView3) {
				imageView3.startAnimation(animSlideDownOut);
			}
		}
	};

	AnimationListener animSlideOutListener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			if (curSlidingImage == imageView1) {
				curSlidingImage = imageView2;
				setImageViewVisibility(curSlidingImage);
			} else if (curSlidingImage == imageView2) {
				curSlidingImage = imageView3;
				setImageViewVisibility(curSlidingImage);
			} else if (curSlidingImage == imageView3) {
				curSlidingImage = imageView1;
				setImageViewVisibility(curSlidingImage);
			}
		}
	};

	void setImageViewVisibility(ImageView view) {
		view.startAnimation(animSlideDownIn);
		imageView1.setVisibility(View.INVISIBLE);
		imageView2.setVisibility(View.INVISIBLE);
		imageView3.setVisibility(View.INVISIBLE);

		view.setVisibility(View.VISIBLE);
	}
}
