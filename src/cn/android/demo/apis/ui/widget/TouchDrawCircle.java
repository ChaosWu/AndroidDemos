package cn.android.demo.apis.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class TouchDrawCircle extends Activity {
	/**
	 * Custom view
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setContentView(new TouchDrawCircleView(this));

		LinearLayout mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);

		TouchDrawCircleView myView1 = new TouchDrawCircleView(this);
		LayoutParams myView1Params = new LayoutParams(200, 200);
		myView1.setLayoutParams(myView1Params);
		myView1.setBackgroundColor(Color.GREEN);

		TouchDrawCircleView myView2 = new TouchDrawCircleView(this);
		LayoutParams myView2Params = new LayoutParams(300, 200);
		myView2.setLayoutParams(myView2Params);
		myView2.setBackgroundColor(Color.BLUE);

		mainLayout.addView(myView1);
		mainLayout.addView(myView2);
		setContentView(mainLayout);
	}

	private class TouchDrawCircleView extends View {
		private Paint mPaint;

		private float intX;
		private float intY;
		private float mRadius;

		private boolean drawing = false;

		public TouchDrawCircleView(Context context) {
			super(context);
			// 初始化画笔
			mPaint = new Paint();
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setStrokeWidth(5);
			mPaint.setColor(Color.WHITE);
		}

		@Override
		protected void onDraw(Canvas canvas) {

			if (drawing) {
				canvas.drawCircle(intX, intY, mRadius, mPaint);
			}
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			int action = event.getAction();
			float x;
			float y;
			if (action == MotionEvent.ACTION_DOWN) {
				Log.v("DDD", "DOWN!!!");
				intX = event.getX();
				intY = event.getY();

				mRadius = 20;
				drawing = true;

			} else if (action == MotionEvent.ACTION_MOVE) {
				Log.v("DDD", "MOVE!!!");
				x = event.getX();
				y = event.getY();
				mRadius = (float) Math.sqrt(Math.pow(x - intX, 2)
						+ Math.pow(y - intY, 2));

			} else if (action == MotionEvent.ACTION_UP) {
				Log.v("DDD", "UP!!!");
				drawing = false;
			}
			// switch (action) {
			// case MotionEvent.ACTION_DOWN:
			// Log.v("DDD", "DOWN!!!");
			//
			//
			// case MotionEvent.ACTION_MOVE:
			// Log.v("DDD", "MOVE!!!");
			//
			// case MotionEvent.ACTION_UP:
			// Log.v("DDD", "UP!!!");
			//
			// }
			invalidate();
			return true;
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
					MeasureSpec.getSize(heightMeasureSpec));

		}

	}

}
