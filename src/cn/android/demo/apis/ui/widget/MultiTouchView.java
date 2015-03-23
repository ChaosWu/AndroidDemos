package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouchView extends View {
	private final int TOUCH_CNT = 2;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	float[] x = new float[TOUCH_CNT];
	float[] y = new float[TOUCH_CNT];

	boolean[] isTouch = new boolean[TOUCH_CNT];

	public MultiTouchView(Context context) {
		super(context);
	}

	public MultiTouchView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MultiTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (isTouch[0]) {
			paint.setStrokeWidth(1);

			paint.setColor(Color.RED);
			paint.setStyle(Paint.Style.FILL);
			canvas.drawCircle(x[0], y[0], 75f, paint);
		}
		if (isTouch[1]) {
			paint.setStrokeWidth(1);
			paint.setColor(Color.BLUE);
			paint.setStyle(Paint.Style.FILL);
			canvas.drawCircle(x[1], y[1], 75f, paint);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int pointerIndex = ((event.getAction() & event.ACTION_POINTER_ID_MASK) >> event.ACTION_POINTER_ID_SHIFT);
		int pointerId = event.getPointerId(pointerIndex);
		int action = event.getAction() & event.ACTION_MASK;
		int pointCnt = event.getPointerCount();

		if (pointCnt <= TOUCH_CNT) {
			if (pointerIndex <= TOUCH_CNT - 1) {
				for (int i = 0; i < pointCnt; i++) {
					int id = event.getPointerId(i);
					x[i] = event.getX(i);
					y[i] = event.getY(i);
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
				case MotionEvent.ACTION_MOVE:
					isTouch[pointerId] = true;
					break;

				default:
					isTouch[pointerId] = false;

				}
			}
		}

		invalidate();
		return true;
	}
}
