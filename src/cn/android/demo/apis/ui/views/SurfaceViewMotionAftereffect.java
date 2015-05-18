package cn.android.demo.apis.ui.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * MAE 运动后效
 * 
 * @author Elroy
 * 
 */
public class SurfaceViewMotionAftereffect extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MySurfaceView sv = new MySurfaceView(this);
		setContentView(sv);
	}

	public class MySurfaceView extends SurfaceView {

		private SurfaceHolder surfaceHolder;
		private MyThread myThread;

		SurfaceViewMotionAftereffect mainActivity;

		int T;
		static final float freq = 80;

		public MySurfaceView(Context context) {
			super(context);
			init(context);
		}

		public MySurfaceView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		private void init(Context c) {
			mainActivity = (SurfaceViewMotionAftereffect) c;
			T = 0;
			myThread = new MyThread(this);

			surfaceHolder = getHolder();

			surfaceHolder.addCallback(new SurfaceHolder.Callback() {

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

				@Override
				public void surfaceDestroyed(SurfaceHolder holder) {
					boolean retry = true;
					myThread.setRunning(false);
					while (retry) {
						try {
							myThread.join();
							retry = false;
						} catch (InterruptedException e) {
						}
					}
				}
			});
		}

		protected void drawSomething(Canvas canvas) {

			int sizex = getWidth();
			int sizey = getHeight();

			float divby_sizex_sq = 1.0f / ((float) sizex * (float) sizex);

			int[] data = new int[sizex * sizey];
			int m;

			T++;
			if (T >= 1200) {
				T = 0;
			}
			float halfT = T * 0.5f;

			for (int j = 0; j < sizey; j++) {

				float y0 = j * 2 - sizey;
				float y2_2 = y0 * y0 * divby_sizex_sq;

				float absY = Math.abs(y0 / (float) sizey);
				float halfT_plus_absY = absY + halfT;
				float halfT_neg_plus_absY = absY - halfT;

				int j_multi_sizex = j * sizex;

				for (int i = 0; i < sizex; i++) {
					float x = (i * 2 - sizex) / (float) sizex;

					// 0.2 instead of 0.1 to have a bigger circle
					if ((x * x + y2_2) < 0.2) {
						m = (int) (Math.sin((halfT_plus_absY + Math.abs(x))
								* freq) * 127.0 + 128) & 0xFF;
					} else {
						m = (int) (Math.sin((halfT_neg_plus_absY + Math.abs(x))
								* freq) * 127.0 + 128) & 0xFF;
					}

					data[i + j_multi_sizex] = 0xFF000000 + (m << 16) + (m << 8)
							+ m;
				}
			}

			Bitmap bm = Bitmap.createBitmap(data, sizex, sizey,
					Bitmap.Config.ARGB_8888);

			canvas.drawBitmap(bm, 0, 0, null);

		}
	}

	public class MyThread extends Thread {

		MySurfaceView myView;
		private boolean running = false;

		public MyThread(MySurfaceView view) {
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

				/*
				 * try { sleep(30); } catch (InterruptedException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 */

			}
		}

	}
}
