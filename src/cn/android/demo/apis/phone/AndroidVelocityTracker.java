package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.TextView;

/**
 * 辅助跟踪触摸事件的速度
 * 
 * @author Elroy
 * 
 */
public class AndroidVelocityTracker extends Activity {
	TextView textAvtion;
	TextView textVelocityX0;
	TextView textVelocityY0;
	TextView textVelocityX1;
	TextView textVelocityY1;
	TextView textVersion;
	VelocityTracker velocityTracker = null;

	static SparseArray<String> arrayVC = new SparseArray<String>();
	static {
		arrayVC.append(VERSION_CODES.BASE,
				"The original, first, version of Android.");
		arrayVC.append(VERSION_CODES.BASE_1_1,
				"First Android update, officially called 1.1");
		arrayVC.append(VERSION_CODES.CUPCAKE, "Android 1.5");
		arrayVC.append(VERSION_CODES.CUR_DEVELOPMENT, "Magic version number...");
		arrayVC.append(VERSION_CODES.DONUT, "Android 1.6");
		arrayVC.append(VERSION_CODES.ECLAIR, "Android 2.0");
		arrayVC.append(VERSION_CODES.ECLAIR_0_1, "Android 2.0.1");
		arrayVC.append(VERSION_CODES.ECLAIR_MR1, "Android 2.1");
		arrayVC.append(VERSION_CODES.FROYO, "Android 2.2");
		arrayVC.append(VERSION_CODES.GINGERBREAD, "Android 2.3");
		arrayVC.append(VERSION_CODES.GINGERBREAD_MR1, "Android 2.3.3");
		arrayVC.append(VERSION_CODES.HONEYCOMB, "Android 3.0");
		arrayVC.append(VERSION_CODES.HONEYCOMB_MR1, "Android 3.1");
		arrayVC.append(VERSION_CODES.HONEYCOMB_MR2, "Android 3.2");
		arrayVC.append(VERSION_CODES.ICE_CREAM_SANDWICH, "Android 4.0");
		arrayVC.append(VERSION_CODES.ICE_CREAM_SANDWICH_MR1, "Android 4.0.3");
		arrayVC.append(VERSION_CODES.JELLY_BEAN, "Android 4.1");
		arrayVC.append(VERSION_CODES.JELLY_BEAN_MR1, "Android 4.2");
		arrayVC.append(VERSION_CODES.JELLY_BEAN_MR2, "Android 4.3");
		arrayVC.append(VERSION_CODES.KITKAT, "Android 4.4");
	}

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_velocity_tracker);

		textAvtion = $id(R.id.velocity_tracker_action);
		textVersion = $id(R.id.velocity_tracker_version);

		textVelocityX0 = $id(R.id.velocity_tracker_velocityx0);
		textVelocityY0 = $id(R.id.velocity_tracker_velocityy0);
		textVelocityX1 = $id(R.id.velocity_tracker_velocityx1);
		textVelocityY1 = $id(R.id.velocity_tracker_velocityy1);
		int version = Build.VERSION.SDK_INT;
		String buildVersion = arrayVC.get(version, "unknown");
		textVersion.setText(buildVersion);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getActionMasked();
		int index = event.getActionIndex();
		int pointerId = event.getPointerId(index);

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (velocityTracker == null) {
				velocityTracker = VelocityTracker.obtain();
			} else {
				velocityTracker.clear();
			}
			velocityTracker.addMovement(event);

			if (pointerId == 0) {
				textVelocityX0.setText("X-velocity (pixel/s): 0");
				textVelocityY0.setText("Y-velocity (pixel/s): 0");
			} else if (pointerId == 1) {
				textVelocityX1.setText("X-velocity (pixel/s): 0");
				textVelocityY1.setText("Y-velocity (pixel/s): 0");
			}

			break;
		case MotionEvent.ACTION_MOVE:
			velocityTracker.addMovement(event);
			velocityTracker.computeCurrentVelocity(1000);
			// 1000 provides pixels per second

			float xVelocity = VelocityTrackerCompat.getXVelocity(
					velocityTracker, pointerId);

			float yVelocity = VelocityTrackerCompat.getYVelocity(
					velocityTracker, pointerId);

			if (pointerId == 0) {
				textVelocityX0.setText("X-velocity (pixel/s): " + xVelocity);
				textVelocityY0.setText("Y-velocity (pixel/s): " + yVelocity);
			} else if (pointerId == 1) {
				textVelocityX1.setText("X-velocity (pixel/s): " + xVelocity);
				textVelocityY1.setText("Y-velocity (pixel/s): " + yVelocity);
			}

			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			velocityTracker.recycle();
			break;
		}

		return true;
	}

}