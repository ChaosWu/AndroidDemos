package cn.android.demo.apis.ui.widget;

import java.util.ArrayList;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

/**
 * onTouchEvent方法返回fasle，表示你的代码不会处理这些事件，系统将不会传给你的后续活动
 * 
 * @author Elroy
 * 
 */
public class TouchOnCustomViewDrawIcon extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyView myView = new MyView(this);
		setContentView(myView);
	}

	public class MyView extends View {

		private ArrayList<xy> drawList;
		private float offsetX, offsetY;
		private boolean touching = false;
		private Bitmap bm;

		Paint paintTouchPointer_ONE;
		Paint paintTouchPointer;

		public MyView(Context context) {
			super(context);
			init();
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
					MeasureSpec.getSize(heightMeasureSpec));
		}

		private void init() {
			paintTouchPointer_ONE = new Paint();
			paintTouchPointer_ONE.setColor(Color.BLUE);
			paintTouchPointer_ONE.setStyle(Paint.Style.STROKE);

			paintTouchPointer = new Paint();
			paintTouchPointer.setColor(Color.RED);
			paintTouchPointer.setStyle(Style.FILL);

			bm = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
			offsetX = bm.getWidth() / 2;
			offsetY = bm.getHeight() / 2;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.GRAY);
			if (touching) {
				for (xy pt : drawList) {

					float PRESSURE_ONE_RADIUS = 50;
					float radius = pt.getPressure() * PRESSURE_ONE_RADIUS;
					// canvas.drawBitmap(bm, pt.getX() - offsetX, pt.getY()
					// - offsetY, null);

					canvas.drawCircle(pt.getX(), pt.getY(), radius,
							paintTouchPointer);

					canvas.drawCircle(pt.getX(), pt.getY(),
							PRESSURE_ONE_RADIUS, paintTouchPointer_ONE);
				}
			}
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {

			int action = event.getAction() & MotionEvent.ACTION_MASK;
			switch (action) {
			case MotionEvent.ACTION_MOVE:
			case MotionEvent.ACTION_DOWN:

				ArrayList<xy> touchList = new ArrayList<xy>();
				int pointerCount = event.getPointerCount();

				for (int i = 0; i < pointerCount; i++) {
					// 加偏移量，方便观看
					touchList.add(new xy(event.getX(i), event.getY(i), event
							.getPressure(i)));
				}
				drawList = touchList;
				touching = true;
				break;
			default:
				touching = false;
			}

			invalidate();
			return true;
		}

		class xy {
			float x;
			float y;
			float pressure;

			public xy(float x, float y, float pressure) {
				this.x = x;
				this.y = y;
				this.pressure = pressure;
			}

			public float getX() {
				return x;
			}

			public float getY() {
				return y;
			}

			public float getPressure() {
				return pressure;
			}
		}

	}
}
