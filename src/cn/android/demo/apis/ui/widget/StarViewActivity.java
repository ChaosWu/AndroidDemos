package cn.android.demo.apis.ui.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class StarViewActivity extends Activity {
	StarView sv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sv = new StarView(this);
		setContentView(sv);

		// Random random = new Random();
		// int num = 500;
		// for (int i = 0; i <= num; i++) {
		// startView.addStar(i, random.nextInt(num));
		// }
		new MyAsyncTask(sv).execute();
	}

	public class StarView extends View {
		Paint paintBg;
		Paint paintStar;

		int screenW;
		int screenH;

		Context mContext;

		class Star {
			int x;
			int y;

			Star(int rx, int ry) {
				x = rx;
				y = ry;
			}
		}

		private List<Star> stars = new ArrayList<Star>();

		public StarView(Context context) {
			super(context);
			init(context);
		}

		public StarView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			init(context);
		}

		private void init(Context context) {
			mContext = context;

			paintBg = new Paint();
			paintBg.setStyle(Style.FILL);
			paintBg.setColor(Color.BLACK);

			paintStar = new Paint();
			paintStar.setStyle(Style.STROKE);
			paintStar.setColor(Color.WHITE);
			paintStar.setStrokeWidth(3);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			int w = MeasureSpec.getSize(widthMeasureSpec);
			int h = MeasureSpec.getSize(heightMeasureSpec);
			Log.v("DDD", "onMeasure w: " + w);
			Log.v("DDD", "onMeasure h:" + h);
			screenW = w;
			screenH = h;
			setMeasuredDimension(w, h);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

			for (Star star : stars) {
				int invY = getHeight() - star.y;
				canvas.drawPoint(star.x, invY, paintStar);
				// canvas.drawPoint(star.x, star.y, paintStar);

			}

		}

		void addStar(int x, int y) {
			stars.add(new Star(x, y));
		}

		// int getScreenWidth() {
		// return screenW;
		//
		// }
		//
		// int getScreenHeight() {
		// return screenH;
		// }

	}

	public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
		StarView starView;

		MyAsyncTask(StarView starView) {
			this.starView = starView;
		}

		@Override
		protected Void doInBackground(Void... params) {
			Random random = new Random();
			for (int i = 0; i <= 500; i++) {
				starView.addStar(i, random.nextInt(500));
				starView.postInvalidate();
				SystemClock.sleep(100);
			}

			return null;
		}

	}

}
