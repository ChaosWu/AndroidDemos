package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class TouchPinchZoomActivity extends Activity {
	TextView myTouchEvent;
	ImageView myImageView;
	Bitmap bitmap;
	int touchState;
	final int IDLE = 0;
	final int TOUCH = 1;
	final int PINCH = 2;

	int bmpWidth, bmpHeight;

	float dist0, distCurrent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_touch_pinch_zoom);
		myTouchEvent = (TextView) findViewById(R.id.touchevent);
		myImageView = (ImageView) findViewById(R.id.imageview);

		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		myImageView.setImageBitmap(bitmap);

		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		bmpWidth = bitmap.getWidth();
		bmpHeight = bitmap.getHeight();

		distCurrent = 1; // Dummy default distance
		dist0 = 1; // Dummy default distance
		drawMatrix();

		myImageView.setOnTouchListener(onTouchListener);
		touchState = IDLE;
	}

	private void drawMatrix() {
		float curScale = distCurrent / dist0;
		if (curScale < 0.1) {
			curScale = 0.1f;
		}

		Bitmap resizedBitmap;
		int newHeight = (int) (bmpHeight * curScale);
		int newWidth = (int) (bmpWidth * curScale);
		resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight,
				false);
		myImageView.setImageBitmap(resizedBitmap);
	}

	OnTouchListener onTouchListener = new OnTouchListener() {
		float distx, disty;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				// A pressed gesture has started, the motion contains the
				// initial starting location.
				myTouchEvent.setText("ACTION_DOWN");
				touchState = TOUCH;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				// A non-primary pointer has gone down.
				myTouchEvent.setText("ACTION_POINTER_DOWN");
				touchState = PINCH;
				// Get the distance when the second pointer touch
				distx = event.getX(0) - event.getX(1);
				disty = event.getY(0) - event.getY(1);
				dist0 = FloatMath.sqrt(distx * distx + disty * disty);
				break;
			case MotionEvent.ACTION_MOVE:
				// A change has happened during a press gesture (between
				// ACTION_DOWN and ACTION_UP).
				myTouchEvent.setText("ACTION_MOVE");
				if (touchState == PINCH) {
					// Get the current distance
					distx = event.getX(0) - event.getX(1);
					disty = event.getY(0) - event.getY(1);
					distCurrent = FloatMath.sqrt(distx * distx + disty * disty);

					drawMatrix();
				}
				break;
			case MotionEvent.ACTION_UP:
				// A pressed gesture has finished.
				myTouchEvent.setText("ACTION_UP");
				touchState = IDLE;
				break;
			case MotionEvent.ACTION_POINTER_UP:
				// A non-primary pointer has gone up.
				myTouchEvent.setText("ACTION_POINTER_UP");
				touchState = TOUCH;
				break;
			}

			return true;
		}

	};
}
