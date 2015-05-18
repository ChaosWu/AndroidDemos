package cn.android.demo.apis.ui.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * Android SurfaceView使用
 * 
 * @author Elroy
 * 
 */
public class AndroidSurfaceView extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MySurfaceView view = new MySurfaceView(this);
		setContentView(view);
	}

	public class MyThread extends Thread {
		MySurfaceView view;
		private boolean running = false;

		public MyThread(MySurfaceView view) {
			this.view = view;
		}

		public MyThread(SurfaceViewDrawBitmap v) {
		}

		public void setRunning(boolean run) {
			running = run;
		}

		@Override
		public void run() {
			while (running) {
				Canvas canvas = view.getHolder().lockCanvas();
				if (canvas != null) {
					synchronized (view.getHolder()) {
						view.drawSometing(canvas);
					}
					view.getHolder().unlockCanvasAndPost(canvas);

				}
				SystemClock.sleep(30);
			}

		}
	}

	public class MySurfaceView extends SurfaceView {
		private SurfaceHolder holder;
		private Bitmap bitmap;
		private MyThread myThread;
		int xPos = 0;
		int yPos = 0;
		int deltaX = 30;
		int deltaY = 30;
		int iconW;
		int iconH;

		public MySurfaceView(Context context) {
			super(context);
			init();
		}

		public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init();
		}

		public MySurfaceView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init();
		}

		private void init() {

			myThread = new MyThread(this);
			holder = getHolder();
			bitmap = BitmapFactory.decodeResource(getResources(),
					cn.android.demo.apis.R.drawable.radio_green_color);

			iconH = bitmap.getHeight();
			iconW = bitmap.getWidth();

			holder.addCallback(new Callback() {

				@Override
				public void surfaceDestroyed(SurfaceHolder holder) {
					boolean retry = true;
					myThread.setRunning(false);
					while (retry) {
						try {
							// 阻塞当前线程，直到接收完成它的执行和死亡。
							myThread.join();
							retry = false;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				@Override
				public void surfaceCreated(SurfaceHolder holder) {
					// Canvas canvas = holder.lockCanvas(null);
					// drawSometing(canvas);
					// holder.unlockCanvasAndPost(canvas);

					myThread.setRunning(true);
					myThread.start();

				}

				@Override
				public void surfaceChanged(SurfaceHolder holder, int format,
						int width, int height) {

				}
			});

		}

		protected void drawSometing(Canvas canvas) {
			canvas.drawColor(Color.BLACK);
			canvas.drawBitmap(bitmap, getWidth() / 2, getHeight() / 2, null);

			xPos += deltaX;
			if (deltaX > 0) {
				if (xPos >= getWidth() - iconW) {
					deltaX *= -1;
				}

			} else {
				if (xPos <= 0) {
					deltaX *= -1;

				}
			}

			yPos += deltaY;
			if (deltaY > 0) {
				if (yPos >= getHeight() - iconH) {
					deltaY *= -1;
				}
			} else {
				if (yPos <= 0) {
					deltaY *= -1;
				}
			}

			canvas.drawColor(Color.BLACK);
			canvas.drawBitmap(bitmap, xPos, yPos, null);
		}

	}

}
