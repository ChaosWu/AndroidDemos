package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

//多点触控，在nubia手机测试，最多支持2点
public class MultiTouchsView extends View {

	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	final int MAX_NUMBER_OF_POINT = 10;
	float[] x = new float[MAX_NUMBER_OF_POINT];
	float[] y = new float[MAX_NUMBER_OF_POINT];
	boolean[] touching = new boolean[MAX_NUMBER_OF_POINT];

	public MultiTouchsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MultiTouchsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MultiTouchsView(Context context) {
		super(context);
		init();
	}

	void init() {
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1);
		paint.setColor(Color.WHITE);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		for (int i = 0; i < MAX_NUMBER_OF_POINT; i++) {
			if (touching[i]) {
				canvas.drawCircle(x[i], y[i], 50f, paint);
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = (event.getAction() & MotionEvent.ACTION_MASK);
		int pointCount = event.getPointerCount();

		for (int i = 0; i < pointCount; i++) {
			int id = event.getPointerId(i);

			// Ignore pointer higher than our max.
			if (id < MAX_NUMBER_OF_POINT) {
				x[id] = (int) event.getX(i);
				y[id] = (int) event.getY(i);

				if ((action == MotionEvent.ACTION_DOWN)
						|| (action == MotionEvent.ACTION_POINTER_DOWN)
						|| (action == MotionEvent.ACTION_MOVE)) {
					touching[id] = true;
				} else {
					touching[id] = false;
				}
			}
		}

		invalidate();
		return true;

	}

}