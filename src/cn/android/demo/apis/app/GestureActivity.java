package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * GestureDetector.SimpleOnGestureListener是一个方便的类来扩展，当你只想要听的所有手势的一个子集。
 * 
 * @author Elroy
 * 
 */
public class GestureActivity extends Activity {
	TextView tvGesture;
	TextView tvGesture2;
	TextView scale;

	ImageView image;

	GestureDetector gestureDetector;
	ScaleGestureDetector scaleGestureDetector;

	Bitmap bitmap;
	int bmpW;
	int bmpH;
	float curScale = 1F;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.app_gesture_event);
		tvGesture = (TextView) findViewById(R.id.tv_gesture_event);
		tvGesture2 = (TextView) findViewById(R.id.tv_gesture_event2);

		scale = (TextView) findViewById(R.id.tv_scale_gesture);
		image = (ImageView) findViewById(R.id.iv_scale_imageview);

		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.chaoswu);

		bmpW = bitmap.getWidth();
		bmpH = bitmap.getHeight();
		drawMatrix();

		gestureDetector = new GestureDetector(this, simpleOnGestureListener);
		scaleGestureDetector = new ScaleGestureDetector(this,
				scaleGestureListener);
	}

	private void drawMatrix() {
		curScale = ((curScale - 1) * 10) + 1;

		if (curScale < 0.1) {
			curScale = 0.1F;
		}
		Bitmap resizeBitmap;

		int newH = (int) (bmpH * curScale);
		int newW = (int) (bmpW * curScale);

		resizeBitmap = Bitmap.createScaledBitmap(bitmap, newW, newH, false);
		image.setImageBitmap(resizeBitmap);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		scaleGestureDetector.onTouchEvent(event);
		return gestureDetector.onTouchEvent(event);
	}

	SimpleOnScaleGestureListener scaleGestureListener = new SimpleOnScaleGestureListener() {

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			Log.v("DDD", "onScale");
			scale.setText(String.valueOf(detector.getScaleFactor()));
			curScale = detector.getScaleFactor();
			drawMatrix();
			return true;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			Log.v("DDD", "onScaleBegin");
			scale.setVisibility(View.VISIBLE);
			return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector detector) {
			Log.v("DDD", "onScaleEnd");
			// super.onScaleEnd(detector);
			scale.setVisibility(View.INVISIBLE);
			super.onScaleEnd(detector);
		}

	};
	SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener() {

		// 双击的第二下Touch down时触发
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// TODO Auto-generated method stub
			tvGesture.setText("onDoubleTap: \n" + e.toString());
			return super.onDoubleTap(e);
		}

		// Touch了滑动一点距离后，up时触发
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			tvGesture.setText("onFling: \n" + e1.toString() + "\n"
					+ e2.toString() + "\n" + "velocityX= "
					+ String.valueOf(velocityX) + "\n" + "velocityY= "
					+ String.valueOf(velocityY) + "\n");

			String swipe = "";
			float sensitvity = 50;

			if ((e1.getX() - e2.getX()) > sensitvity) {
				swipe += "Swipe Left\n";
			} else if ((e2.getX() - e1.getX()) > sensitvity) {
				swipe += "Swipe Right\n";
			} else {
				swipe += "\n";
			}

			if ((e1.getY() - e2.getY()) > sensitvity) {
				swipe += "Swipe UP\n";
			} else if ((e2.getY() - e1.getY()) > sensitvity) {
				swipe += "Swipe Down\n";
			} else {
				swipe += "\n";
			}

			tvGesture2.setText(swipe);

			return super.onFling(e1, e2, velocityX, velocityY);
		}

		// Touch了不移动一直Touch down时触发
		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			tvGesture.setText("onLongPress: \n" + e.toString());
			super.onLongPress(e);
		}

		/*
		 * 两个函数都是在Touch Down后又没有滑动(onScroll)，又没有长按(onLongPress)，然后Touch Up时触发
		 * 点击一下非常快的(不滑动)Touch Up: onDown->onSingleTapUp->onSingleTapConfirmed
		 * 点击一下稍微慢点的(不滑动)Touch Up:
		 * onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed
		 */
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			tvGesture.setText("onSingleTapConfirmed: \n" + e.toString());
			return super.onSingleTapConfirmed(e);
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onSingleTapUp(e);
		}

		// Touch了滑动时触发
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		/*
		 * Touch了还没有滑动时触发 (1)onDown只要Touch Down一定立刻触发 (2)Touch
		 * Down后过一会没有滑动先触发onShowPress再触发onLongPress So: Touch Down后一直不滑动，onDown
		 * -> onShowPress -> onLongPress这个顺序触发。
		 */
		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onShowPress(e);
		}

		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onDown(e);
		}

		// 双击的第二下Touch down和up都会触发，可用e.getAction()区分
		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onDoubleTapEvent(e);
		}

	};

}