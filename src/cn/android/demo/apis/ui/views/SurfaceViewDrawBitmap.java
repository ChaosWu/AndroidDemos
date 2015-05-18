package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.ui.views.AndroidSurfaceView.MyThread;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class SurfaceViewDrawBitmap extends SurfaceView {
	private SurfaceHolder holder;
	private MyThread myThread;

	SurfaceView2DrawBitmap activity;

	long timeStart;
	long timeA;
	long timeB;
	long timeFillBackground;
	long timeDrawBitmap;
	long timeTotal;
	long numberOfPt;

	public SurfaceViewDrawBitmap(Context context) {
		super(context);
		init(context);
	}

	public SurfaceViewDrawBitmap(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public SurfaceViewDrawBitmap(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		activity = (SurfaceView2DrawBitmap) context;
		numberOfPt = 0;
		myThread = new MyThread(this);

		holder = getHolder();

		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				while (retry) {
					try {
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
				myThread.setRunning(true);
				myThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * 创建int的数组，填写数据点逐点，然后createBitmap从阵列
	 * 
	 * @param w
	 * @param h
	 * @param cnt
	 * @return
	 */
	private Bitmap prepareBitmap_A(int w, int h, long cnt) {
		int[] data = new int[w * h];

		// fill with dummy data
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				// data[x + y*w] = 0xFF000000 + x;

				if (cnt >= 0) {
					data[x + y * w] = 0xFFff0000;
					cnt--;
				} else {
					data[x + y * w] = 0xFFa0a0a0;
				}

			}
		}
		timeA = System.currentTimeMillis();
		Bitmap bm = Bitmap.createBitmap(data, w, h, Bitmap.Config.ARGB_8888);
		timeB = System.currentTimeMillis();
		return bm;
	}

	/**
	 * 创建一个位图，然后通过调用setPixel填写像素
	 * 
	 * @param w
	 * @param h
	 * @param cnt
	 * @return
	 */
	private Bitmap prepareBitmap_B(int w, int h, long cnt) {

		Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		timeA = System.currentTimeMillis();

		// fill with dummy data
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				// data[x + y*w] = 0xFF000000 + x;

				if (cnt >= 0) {
					bm.setPixel(x, y, 0xFFff0000);
					cnt--;
				} else {
					bm.setPixel(x, y, 0xFFa0a0a0);
				}

			}
		}
		timeB = System.currentTimeMillis();
		return bm;
	}

	protected void drawSomething(Canvas canvas) {

		numberOfPt += 500;
		if (numberOfPt > (long) ((getWidth() * getHeight()))) {
			numberOfPt = 0;
		}

		timeStart = System.currentTimeMillis();
		Bitmap bmDummy = prepareBitmap_A(getWidth(), getHeight(), numberOfPt);

		canvas.drawColor(Color.BLACK);
		timeFillBackground = System.currentTimeMillis();
		canvas.drawBitmap(bmDummy, 0, 0, null);
		timeDrawBitmap = System.currentTimeMillis();

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				activity.showDur(timeA - timeStart, timeB - timeA,
						timeFillBackground - timeB, timeDrawBitmap
								- timeFillBackground, timeDrawBitmap
								- timeStart);
			}
		});
	}

	public class MyThread extends Thread {

		SurfaceViewDrawBitmap myView;
		private boolean running = false;

		public MyThread(SurfaceViewDrawBitmap view) {
			myView = view;
		}

		public void setRunning(boolean run) {
			running = run;
		}

		@Override
		public void run() {
			while (running) {

				Canvas canvas = myView.getHolder().lockCanvas();

				if (canvas != null) {
					synchronized (myView.getHolder()) {
						myView.drawSomething(canvas);
					}
					myView.getHolder().unlockCanvasAndPost(canvas);
				}

				try {
					sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

}
