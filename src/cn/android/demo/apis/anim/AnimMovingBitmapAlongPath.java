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
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

/**
 * 图片移动轨迹
 * 
 * @author Elroy
 * 
 */
public class AnimMovingBitmapAlongPath extends Activity {
	LinearLayout layout;
	SeekBar bar;
	MyView view;

	Button forward;
	Button backward;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		LayoutParams barParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		layout = new LinearLayout(this);
		layout.setLayoutParams(layoutParams);
		layout.setOrientation(LinearLayout.VERTICAL);

		bar = new SeekBar(this);
		bar.setLayoutParams(barParams);
		bar.setMax(45);
		bar.setProgress(0);

		forward = new Button(this);
		forward.setLayoutParams(barParams);
		forward.setText("FORWARD");

		backward = new Button(this);
		backward.setLayoutParams(barParams);
		backward.setText("BACKWARD");

		view = new MyView(this);

		setContentView(layout);
		layout.addView(bar);
		layout.addView(forward);
		layout.addView(backward);
		layout.addView(view);

		view.setSpeed(bar.getProgress());

		forward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				view.replayForward();
			}
		});

		backward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				view.replayBackward();
			}
		});

		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				view.setSpeed(progress);
			}
		});

	}

	public class MyView extends View {

		static final int FORWARD = 1;
		static final int BACKWARD = -1;
		int direction;// 方向

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
		private long lastTime;
		private Paint paintText;

		public MyView(Context context) {
			super(context);
			init();
		}

		private void init() {
			paint = new Paint();
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(3);
			paint.setStyle(Style.STROKE);

			paintText = new Paint();
			paintText.setColor(Color.RED);
			paintText.setStrokeWidth(1);
			paintText.setStyle(Paint.Style.FILL);
			paintText.setTextSize(26);

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
			direction = FORWARD; // default

			pos = new float[2];
			tan = new float[2];

			matrix = new Matrix();

			lastTime = System.currentTimeMillis();
		}

		public void setSpeed(int sp) {
			step = sp;
			stepAngle = sp;
		}

		public void replayForward() {
			direction = FORWARD;
			invalidate();
		}

		public void replayBackward() {
			direction = BACKWARD;
			invalidate();
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

				pathMeasure = new PathMeasure(animPath, false);
				pathLength = pathMeasure.getLength();

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

			long startNanos = System.nanoTime();
			long startMillis = System.currentTimeMillis();

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
				if ((direction == FORWARD && distance < pathLength)
						|| (direction == BACKWARD && distance > 0)) {
					pathMeasure.getPosTan(distance, pos, tan);

					targetAngle = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
					matrix.postRotate(curAngle, bmOffsetX, bmOffsetY);

					curX = pos[0] - bmOffsetX;
					curY = pos[1] - bmOffsetY;
					matrix.postTranslate(curX, curY);

					canvas.drawBitmap(bm, matrix, null);

					distance += step * direction;
				} else {
					// distance = 0;
					matrix.postRotate(curAngle, bmOffsetX, bmOffsetY);
					matrix.postTranslate(curX, curY);
					canvas.drawBitmap(bm, matrix, null);
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

			long endNanos = System.nanoTime();
			long betweenFrame = startMillis - lastTime;
			int fps = (int) (1000 / betweenFrame);

			String strProcessingTime = "Processing Time (ns=0.000001ms) = "
					+ (endNanos - startNanos);
			String strBetweenFrame = "Between Frame (ms) = " + betweenFrame;
			String strFPS = "Frame Per Second (approximate) = " + fps;

			lastTime = startMillis;
			canvas.drawText(strProcessingTime, 10, 30, paintText);
			canvas.drawText(strBetweenFrame, 10, 60, paintText);
			canvas.drawText(strFPS, 10, 90, paintText);
			canvas.drawText(String.valueOf(pathLength), 10, 120, paintText);

		}
	}
}
