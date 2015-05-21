package cn.android.demo.apis.anim;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 图片移动轨迹
 * 
 * @author Elroy
 * 
 */
public class AnimMovingBitmapAlongPath extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyView view = new MyView(this);
		setContentView(view);
	}

	public class MyView extends View {
		Paint paint;
		Bitmap bm;
		int bmOffsetX;
		int bmOffsetY;

		Path animPath;
		Path touchPath;

		PathMeasure pathMeasure;

		float pathLength;

		float step;// 每一步的距离
		float distance;// 移动的距离

		float curX;
		float curY;

		float curAngle; // current angle
		float targetAngle; // target angle
		float stepAngle; // angle each step

		float[] pos;
		float[] tan;

		Matrix matrix;

		public MyView(Context context) {
			super(context);
			init();
		}

		private void init() {
			paint = new Paint();
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(3);
			paint.setStyle(Style.STROKE);

			bm = BitmapFactory
					.decodeResource(getResources(), R.drawable.anf_ok);
			bmOffsetX = bm.getWidth() / 2;
			bmOffsetY = bm.getHeight() / 2;

			animPath = new Path();
			touchPath = new Path();

			// animPath.moveTo(100, 100);
			// animPath.lineTo(200, 100);
			// animPath.lineTo(300, 50);
			// animPath.lineTo(400, 150);
			// animPath.lineTo(100, 300);
			// animPath.lineTo(600, 300);
			// animPath.lineTo(100, 100);
			// animPath.close();

			// pathMeasure = new PathMeasure(animPath, false);
			// pathLength = pathMeasure.getLength();

			Toast.makeText(getContext(), "pathLength: " + pathLength,
					Toast.LENGTH_LONG).show();

			step = 10;
			distance = 0;

			curX = 0;
			curY = 0;

			stepAngle = 1;
			curAngle = 0;
			targetAngle = 0;

			pos = new float[2];
			tan = new float[2];

			matrix = new Matrix();
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			int action = event.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				touchPath.reset();
				touchPath.moveTo(event.getX(), event.getY());
				break;
			case MotionEvent.ACTION_MOVE:
				touchPath.lineTo(event.getX(), event.getY());
				break;

			case MotionEvent.ACTION_UP:
				touchPath.lineTo(event.getX(), event.getY());
				animPath = new Path(touchPath);
				
				pathMeasure=new PathMeasure(animPath, false);
				pathLength=pathMeasure.getLength();
				
				invalidate();
				break;
			default:
				break;
			}

			return true;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			if (animPath.isEmpty()) {
				return;
			}

			canvas.drawPath(animPath, paint);
			matrix.reset();

			if ((targetAngle - curAngle) > stepAngle) {
				curAngle += stepAngle;

				matrix.postRotate(curAngle, bmOffsetX, bmOffsetY);
				matrix.postTranslate(curX, curY);

				canvas.drawBitmap(bm, matrix, paint);

			} else if ((curAngle - targetAngle) > stepAngle) {
				curAngle -= stepAngle;
				matrix.postRotate(curAngle, bmOffsetX, bmOffsetY);
				matrix.postTranslate(curX, curY);
				canvas.drawBitmap(bm, matrix, paint);

			} else {
				curAngle = targetAngle;
				if (distance < pathLength) {
					pathMeasure.getPosTan(distance, pos, tan);

					targetAngle = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
					matrix.postRotate(curAngle, bmOffsetX, bmOffsetY);

					curX = pos[0] - bmOffsetX;
					curY = pos[1] - bmOffsetY;
					matrix.postTranslate(curX, curY);

					canvas.drawBitmap(bm, matrix, null);

					distance += step;
				} else {
					distance = 0;
				}
			}
			// if (distance < pathLength) {
			// pathMeasure.getPosTan(distance, pos, tan);
			//
			// matrix.reset();
			//
			// float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 /
			// Math.PI);
			// matrix.postRotate(degrees, bmOffsetX, bmOffsetY);
			// matrix.postTranslate(pos[0] - bmOffsetX, pos[1] - bmOffsetY);
			//
			// canvas.drawBitmap(bm, matrix, paint);
			//
			// distance += step;
			//
			// } else {
			// distance = 0;
			// }
			invalidate();
		}

	}
}
