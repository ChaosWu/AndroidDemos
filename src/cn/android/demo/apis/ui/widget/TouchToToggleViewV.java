package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchToToggleViewV extends View {

	public interface OnToggledListener {
		void OnToggled(TouchToToggleViewV v, boolean touchOn);
	}

	boolean touchOn;
	boolean mDownTouch = false;
	private OnToggledListener toggledListener;

	public TouchToToggleViewV(Context context) {
		super(context);
		init();
	}

	public TouchToToggleViewV(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TouchToToggleViewV(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		touchOn = false;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (touchOn) {
			canvas.drawColor(Color.RED);
		} else {
			canvas.drawColor(Color.GRAY);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			touchOn = !touchOn;
			invalidate();

			if (toggledListener != null) {
				toggledListener.OnToggled(this, touchOn);
			}

			mDownTouch = true;
			return true;

		case MotionEvent.ACTION_UP:
			if (mDownTouch) {
				mDownTouch = false;
				performClick();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean performClick() {
		super.performClick();
		return true;
	}

	public void setOnToggledListener(OnToggledListener listener) {
		toggledListener = listener;
	}

}