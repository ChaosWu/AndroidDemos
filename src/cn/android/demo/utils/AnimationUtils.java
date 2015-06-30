package cn.android.demo.utils;


import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public class AnimationUtils {
	static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
	static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
	static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
	
	static class AnimationListenerAdapter implements
			Animation.AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
		
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

	}
}
