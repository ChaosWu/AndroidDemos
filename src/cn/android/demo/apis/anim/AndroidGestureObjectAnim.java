package cn.android.demo.apis.anim;

import com.nineoldandroids.animation.ObjectAnimator;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class AndroidGestureObjectAnim extends Activity {

	FrameLayout frameLayout;
	ImageView imageView;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.anim_gesture_object_anim);

		imageView = (ImageView) findViewById(R.id.gestrue_anim_flingobject);
		textView = (TextView) findViewById(R.id.gestrue_anim_info);
		frameLayout = (FrameLayout) findViewById(R.id.gestrue_anim_mainscreen);

		final GestureDetector detector = new GestureDetector(this,
				new MyOnGestrueListener());
		imageView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return detector.onTouchEvent(event);
			}
		});
		imageView.setClickable(true);
	}

	class MyOnGestrueListener implements OnGestureListener {
		int MIN_DIST = 100;

		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			float e1X = e1.getX();
			float e1Y = e1.getY();

			float e2X = e2.getX();
			float e2Y = e2.getY();

			float disX = e2X - e1X;
			float disY = e2Y - e1Y;

//			if (disX > MIN_DIST) {
//				ObjectAnimator fillAnimator = ObjectAnimator.ofFloat(imageView,
//						"translationX", e1X, e2X);
//				fillAnimator.setDuration(1000);
//				fillAnimator.start();
//			} else if (disX < -MIN_DIST) {
//				ObjectAnimator fillAnimator = ObjectAnimator.ofFloat(imageView,
//						"translationX", e1X, e2X);
//				fillAnimator.setDuration(1000);
//				fillAnimator.start();
//			} else if (disY > MIN_DIST) {
//				ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(
//						imageView, "translationY", e1Y, e2Y);
//				flingAnimator.setDuration(1000);
//				flingAnimator.start();
//			} else if (disY < -MIN_DIST) {
//				ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(
//						imageView, "translationY", e1Y, e2Y);
//				flingAnimator.setDuration(1000);
//				flingAnimator.start();
//			}

			
			
			
			textView.setText("e1X : e1Y" + String.valueOf(e1X) + " : "
					+ String.valueOf(e1Y) + "\n" + "e2X : e2Y"
					+ String.valueOf(e2X) + " : " + String.valueOf(e2Y) + "\n"
					+ "velocityX:" + String.valueOf(velocityX) + "\n"
					+ "velocityY:" + String.valueOf(velocityY));

			DisplayMetrics displayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

			int offsetY = displayMetrics.heightPixels
					- frameLayout.getMeasuredHeight();

			int[] location = new int[2];
			imageView.getLocationOnScreen(location);

			float orgX = location[0];
			// TODO 在我的经验中，返回x位置是正确的，但y位置始终是误差的固定偏移。偏移不同取决于设备和配置。
			float orgY = location[1] - offsetY;

			float stopX = orgX + disX;
			float stopY = orgY + disY;
			if (disX > MIN_DIST) {
				ObjectAnimator fillAnimator = ObjectAnimator.ofFloat(imageView,
						"translationX", orgX, stopX);
				fillAnimator.setDuration(1000);
				fillAnimator.start();
			} else if (disX < -MIN_DIST) {
				ObjectAnimator fillAnimator = ObjectAnimator.ofFloat(imageView,
						"translationX", orgX, stopX);
				fillAnimator.setDuration(1000);
				fillAnimator.start();
			} else if (disY > MIN_DIST) {
				ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(
						imageView, "translationY", orgY, stopY);
				flingAnimator.setDuration(1000);
				flingAnimator.start();
			} else if (disY < -MIN_DIST) {
				ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(
						imageView, "translationY", orgY, stopY);
				flingAnimator.setDuration(1000);
				flingAnimator.start();
			}

			return true;
		}
	}

}
