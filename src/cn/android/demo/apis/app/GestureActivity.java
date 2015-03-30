package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * GestureDetector.SimpleOnGestureListener是一个方便的类来扩展，当你只想要听的所有手势的一个子集。
 * 
 * @author Elroy
 * 
 */
public class GestureActivity extends Activity {
	TextView tvGesture;
	GestureDetector gestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.app_gesture_event);
		tvGesture = (TextView) findViewById(R.id.tv_gesture_event);
		gestureDetector = new GestureDetector(this, simpleOnGestureListener);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// return super.onTouchEvent(event);
		return gestureDetector.onTouchEvent(event);
	}

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
         * 点击一下稍微慢点的(不滑动)Touch Up: onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed   
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
         * Touch了还没有滑动时触发  
         * (1)onDown只要Touch Down一定立刻触发  
         * (2)Touch Down后过一会没有滑动先触发onShowPress再触发onLongPress  
         * So: Touch Down后一直不滑动，onDown -> onShowPress -> onLongPress这个顺序触发。  
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