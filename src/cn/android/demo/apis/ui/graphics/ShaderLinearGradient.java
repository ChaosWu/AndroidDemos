package cn.android.demo.apis.ui.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

/**
 * TileMode: CLAMP 复制边缘颜色，如果着色器绘制其原有的边界之外
 * 
 * REPEAT 水平和垂直方向重复着色器的图像
 * 
 * MIRROR 水平和垂直方向重复着色器的形象，交替的镜像，使得相邻的图像总是缝
 * 
 * @author Elroy
 * 
 */
public class ShaderLinearGradient extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}

	private class MyView extends View {

		Context context;
		Paint paintBg;
		Paint paint;

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

		private void init(Context cxt) {
			this.context = cxt;
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

			float shaderCx = cx;
			float shaderCy = cy;

			int shaderColor0 = Color.RED;
			int shaderColor1 = Color.BLUE;

			Shader linearGradientShader;

			linearGradientShader = new LinearGradient(0, 0, w, h, shaderColor0,
					shaderColor0, TileMode.MIRROR);
			paintBg.setShader(linearGradientShader);
			canvas.drawRect(0, 0, w, h, paintBg);

			linearGradientShader = new LinearGradient(cx, cy, cx + radius, cy
					+ radius, shaderColor0, shaderColor1, TileMode.MIRROR);
			paint.setShader(linearGradientShader);
			canvas.drawCircle(cx, cy, radius, paint);

		}

	}
}
