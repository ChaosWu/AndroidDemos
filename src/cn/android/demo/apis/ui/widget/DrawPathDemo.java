package cn.android.demo.apis.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class DrawPathDemo extends Activity {
	private Paint paint;
	private Path path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(new PathView(this));
	}

	public class PathView extends View {

		public PathView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			float w = getWidth();
			float h = getHeight();

			float radius;

			if (w > h) {
				radius = h / 4;

			} else {
				radius = w / 4;
			}

			Path path = new Path();
			path.addCircle(w / 2, h / 2, radius, Path.Direction.CW);

			Paint paint = new Paint();
			paint.setColor(Color.WHITE);
			paint.setStrokeWidth(5);
			paint.setStyle(Style.STROKE);
			paint.setTextSize(88);

			canvas.drawTextOnPath("Android-er http://android-er.blogspot.com/",
					path, 0, 0, paint);
			
			paint.setColor(Color.GREEN);
			paint.setStyle(Style.FILL);
			canvas.drawTextOnPath("Android-er http://android-er.blogspot.com/",
					path, 0, 0, paint);
		}

	}

	public class WuPathView extends View {
		class Pt {
			float x, y;

			Pt(float x, float y) {
				this.x = x;
				this.y = y;
			}
		}

		Pt[] myPath = { new Pt(100, 100),

		new Pt(200, 200),

		new Pt(200, 500),

		new Pt(400, 500),

		new Pt(400, 200)

		};

		public WuPathView(Context context) {
			super(context);
			init();
		}

		public WuPathView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init();
		}

		public WuPathView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			init();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			path.moveTo(myPath[0].x, myPath[0].y);
			for (int i = 1; i < myPath.length; i++) {
				path.lineTo(myPath[i].x, myPath[i].y);
			}
			canvas.drawPath(path, paint);

		}
	}

	public void init() {
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.STROKE);

		path = new Path();

	}
}
