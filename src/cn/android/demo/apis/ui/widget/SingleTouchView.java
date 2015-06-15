package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchView extends View {
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private float x, y;
	private boolean touching = false;

	public SingleTouchView(Context context) {
		super(context);
	}

	public SingleTouchView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SingleTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
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

		if (touching) {
			paint.setColor(Color.WHITE);
			paint.setStyle(Paint.Style.FILL);
			canvas.drawCircle(x, y, 75f, paint);

		}

	}

	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_DOWN:
			x = event.getX();
			y = event.getY();
			touching = true;
			break;

		default:
			touching = false;
		}
		invalidate();
		return true;
	}
	
}
