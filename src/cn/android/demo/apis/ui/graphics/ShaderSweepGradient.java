package cn.android.demo.apis.ui.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class ShaderSweepGradient extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));

	}

	private class MyView extends View {
		Paint paintBg;
		Paint paint;
		Context context;

		public MyView(Context context) {
			super(context);
			init(context);
		}

		public MyView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);

		}

		public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			init(context);

		}

		private void init(Context ctx) {
			this.context = ctx;
			paintBg = new Paint();
			paintBg.setAntiAlias(true);
			paintBg.setStyle(Style.FILL);
			paintBg.setColor(Color.BLACK);

			paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			paint.setAntiAlias(true);

		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			int w = MeasureSpec.getSize(widthMeasureSpec);
			int h = MeasureSpec.getSize(heightMeasureSpec);
			setMeasuredDimension(w, h);

		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			float w;
			float h;
			float cx;
			float cy;
			float radius;

			w = getWidth();
			h = getHeight();
			cx = w / 2;
			cy = h / 2;

			if (w > h) {
				radius = h / 4;
			} else {
				radius = w / 4;
			}

			canvas.drawRect(0, 0, w, h, paintBg);
			float shaderCx = cx;
			float shaderCy = cy;

			int shaderColor0 = Color.GREEN;
			int shaderColor1 = Color.BLUE;

			paint.setShader(new SweepGradient(shaderCx, shaderCy, shaderColor0,
					shaderColor1));
			canvas.drawCircle(cx, cy, radius, paint);

		}
	}
}
