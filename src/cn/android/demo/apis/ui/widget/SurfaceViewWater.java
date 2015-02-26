package cn.android.demo.apis.ui.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 水波纹效果
 * 
 * @author Elroy
 * 
 */
public class SurfaceViewWater extends Activity {
	private boolean drawing = true;

	private float initX;
	private float initY;
	private float radius;

	private float targetX;
	private float targetY;

	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MySurfaceView mySurfaceView = new MySurfaceView(this);
		setContentView(mySurfaceView);
	}

	@SuppressLint("WrongCall")
	public class MySurfaceThread extends Thread {

		private SurfaceHolder myThreadSurfaceHolder;
		private MySurfaceView myThreadSurfaceView;
		private boolean myThreadRun = false;

		public MySurfaceThread(SurfaceHolder surfaceHolder,
				MySurfaceView surfaceView) {
			myThreadSurfaceHolder = surfaceHolder;
			myThreadSurfaceView = surfaceView;
		}

		public void setRunning(boolean b) {
			myThreadRun = b;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			while (myThreadRun) {
				Canvas c = null;
				try {
					c = myThreadSurfaceHolder.lockCanvas(null);
					synchronized (myThreadSurfaceHolder) {
						myThreadSurfaceView.onDraw(c);
					}

					sleep(500);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// do this in a finally so that if an exception is thrown
					// during the above, we don't leave the Surface in an
					// inconsistent state
					if (c != null) {
						myThreadSurfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}
		}
	}

	public class MySurfaceView extends SurfaceView implements
			SurfaceHolder.Callback {
		private MySurfaceThread thread;

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			// super.onDraw(canvas);
			if (drawing) {
				canvas.drawRGB(0, 0, 0);
				canvas.drawCircle(initX, initY, 3, paint);
				if ((initX == targetX) && (initY == targetY)) {
					drawing = false;
				} else {
					initX = (initX + targetX) / 2;
					initY = (initY + targetY) / 2;
				}
			}
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			// return super.onTouchEvent(event);
			int action = event.getAction();

			if (action == MotionEvent.ACTION_DOWN) {
				targetX = event.getX();
				targetY = event.getY();
				drawing = true;
			}

			return true;
		}

		public MySurfaceView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			init();
		}

		public MySurfaceView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
			init();
		}

		public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			// TODO Auto-generated constructor stub
			init();
		}

		private void init() {
			getHolder().addCallback(this);
			thread = new MySurfaceThread(getHolder(), this);

			setFocusable(true); // make sure we get key events

			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(100);
			paint.setColor(Color.WHITE);

			initX = targetX = 0;
			initY = targetY = 0;

		}

		@Override
		public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			thread.setRunning(true);
			thread.start();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			boolean retry = true;
			thread.setRunning(false);
			while (retry) {
				try {
					thread.join();
					retry = false;
				} catch (InterruptedException e) {
				}
			}
		}
	}

}
