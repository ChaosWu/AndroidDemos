package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

/**
 * 
 * @author Elroy
 * 
 */
public class AndroidViewFlipper extends Activity implements OnClickListener,
		OnGestureListener {
	private ViewFlipper flipper;
	private Button bt1;
	private Button bt2;

	private Button autoFlipper;
	private Animation slide_in_left;
	private Animation slide_out_right;
	private Animation slide_in_right;
	private Animation slide_out_left;

	private GestureDetectorCompat mDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDetector = new GestureDetectorCompat(this, this);
		// mDetector=new GestureDetectorCompat(this, new MyGestureListener());
		setContentView(R.layout.ui_view_viewflipper);
		flipper = (ViewFlipper) findViewById(R.id.viewflipper);

		bt1 = (Button) findViewById(R.id.button1);
		bt2 = (Button) findViewById(R.id.button2);

		autoFlipper = (Button) findViewById(R.id.bt_auto_flipper);

		// Animation animationFlipIn = AnimationUtils.loadAnimation(this,
		// R.anim.flip_in);
		// Animation animationFlipOut = AnimationUtils.loadAnimation(this,
		// R.anim.flip_out);

		slide_in_left = AnimationUtils
				.loadAnimation(this, R.anim.slide_in_left);
		slide_out_right = AnimationUtils.loadAnimation(this,
				R.anim.slide_out_right);

		slide_in_right = AnimationUtils.loadAnimation(this,
				R.anim.slide_in_right);
		slide_out_left = AnimationUtils.loadAnimation(this,
				R.anim.slide_out_left);

		flipper.setFlipInterval(500);
		// flipper.setInAnimation(animationFlipIn);
		// flipper.setOutAnimation(animationFlipOut);

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		autoFlipper.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			flipperNext();
			break;
		case R.id.button2:
			flipperPrevious();
			break;
		case R.id.bt_auto_flipper:
			// Returns true if the child views are flipping.
			if (flipper.isFlipping()) {
				flipper.stopFlipping();
				autoFlipper.setText("开始自动翻转");
			} else {
				flipper.startFlipping();
				autoFlipper.setText("停止自动翻转");
			}

			break;
		default:
			break;
		}
	}

	private void flipperNext() {
		flipper.setInAnimation(slide_in_left);
		flipper.setOutAnimation(slide_out_right);
		flipper.showNext();
	}

	private void flipperPrevious() {
		flipper.setInAnimation(slide_in_right);
		flipper.setOutAnimation(slide_out_left);
		flipper.showPrevious();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

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
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
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
		float sensitvity = 50;
		if ((e1.getX() - e2.getX()) > sensitvity) {
			flipperPrevious();
		} else if ((e2.getX() - e1.getX()) > sensitvity) {
			flipperNext();
		}

		return true;
	}

	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			float sensitvity = 50;
			if ((e1.getX() - e2.getX()) > sensitvity) {
				flipperPrevious();
			} else if ((e2.getX() - e1.getX()) > sensitvity) {
				flipperNext();
			}

			return true;
		}
	}
}
