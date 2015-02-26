package cn.android.demo.apis.bug;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 按home键，再次启动应用
 * 
 * throws IllegalThreadStateException
 * 
 * 重复启动同一个thread
 * 
 * @author Elroy
 * 
 * 
 */
public class BUGSurfaceViewWater extends Activity {

	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);// 初始画笔

	private float initX;// 圆心x
	private float initY;// 圆心y

	private float radius;// 半径

	private boolean drawing = false;

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
			while (myThreadRun) {
				Canvas c = null;
				try {
					c = myThreadSurfaceHolder.lockCanvas(null);
					synchronized (myThreadSurfaceHolder) {
						myThreadSurfaceView.onDraw(c);
					}
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
				canvas.drawCircle(initX, initY, radius, paint);
			}
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			// return super.onTouchEvent(event);
			int action = event.getAction();
			if (action == MotionEvent.ACTION_MOVE) {
				float x = event.getX();
				float y = event.getY();
				radius = (float) Math.sqrt(Math.pow(x - initX, 2)
						+ Math.pow(y - initY, 2));
			} else if (action == MotionEvent.ACTION_DOWN) {
				initX = event.getX();
				initY = event.getY();
				radius = 1;
				drawing = true;
			} else if (action == MotionEvent.ACTION_UP) {
				drawing = false;
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
			paint.setStrokeWidth(3);
			paint.setColor(Color.WHITE);
		}

		@Override
		public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			// 线程是否运行
			// boolean b = thread.isAlive();

		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			if (!thread.isAlive()) {
				thread.setRunning(true);
				thread.start();
			}
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MySurfaceView mySurfaceView = new MySurfaceView(this);
		setContentView(mySurfaceView);
	}
}
