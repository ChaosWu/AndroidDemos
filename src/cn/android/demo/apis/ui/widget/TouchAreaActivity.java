package cn.android.demo.apis.ui.widget;

import cn.android.demo.utils.ToastUtil;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
//触摸压力
//event.getTouchMajor api9

public class TouchAreaActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TouchView touchView = new TouchView(this);
		setContentView(touchView);
		touchView.setOnViewTouchedListener(new OnViewTouchedListener() {

			@Override
			public void OnViewTouched(float x, float y, boolean touched) {
				ToastUtil.showToast(TouchAreaActivity.this, "Touched: "
						+ touched + "\n" + "x: " + x + "\n" + "y: " + y);
			}
		});
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	class TouchView extends View {

		private Paint paint;
		private Paint touchPaint;
		private boolean touched;
		private float touchX;
		private float touchMajor;
		private float touchY;
		private float touchMinor;

		public TouchView(Context context) {
			super(context);
			init(null, 0);
		}

		public void init(AttributeSet attrs, int defStyle) {
			paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setColor(Color.BLUE);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(1);

			touchPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			touchPaint.setColor(Color.RED);
			touchPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			touchPaint.setStrokeWidth(1);

			touched = false;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			int paddingLeft = getPaddingLeft();
			int paddingTop = getPaddingTop();
			int paddingRight = getPaddingRight();
			int paddingBottom = getPaddingBottom();

			canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			canvas.drawRect(paddingLeft, paddingTop, getWidth() - paddingRight,
					getHeight() - paddingBottom, paint);

			if (touched) {
				canvas.drawCircle(touchX, touchY, touchMinor, touchPaint);
				canvas.drawCircle(touchX, touchY, touchMajor, paint);
			}
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
			case MotionEvent.ACTION_DOWN:
				touchX = event.getX();
				touchY = event.getY();
				touchMajor = event.getTouchMajor();
				touchMinor = event.getTouchMinor();
				touched = true;
				break;
			default:
				touched = false;
			}

			onViewTouchedListener.OnViewTouched(touchX, touchY, touched);

			invalidate();
			return true;
		}

		/* Set up callback function */
		private OnViewTouchedListener onViewTouchedListener;

		public void setOnViewTouchedListener(OnViewTouchedListener listener) {
			onViewTouchedListener = listener;
		}
	}
}

interface OnViewTouchedListener {
	public void OnViewTouched(float x, float y, boolean touched);
}
