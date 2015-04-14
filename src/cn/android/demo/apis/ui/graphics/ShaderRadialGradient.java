package cn.android.demo.apis.ui.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

public class ShaderRadialGradient extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}

	private class MyView extends View {

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			float w, h, cx, cy, radius;
			w = getWidth();
			h = getHeight();
			cx = w / 2;
			cy = h / 2;

			if (w > h) {
				radius = h / 4;
			} else {
				radius = w / 4;
			}


			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);

			float shaderCx = 0;
			float shaderCy = 0;
			float shaderRadius = w;
			int shaderColor0 = Color.WHITE;
			int shaderColor1 = Color.BLACK;
			paint.setAntiAlias(true);
			Shader radialGradientShader;

			radialGradientShader = new RadialGradient(shaderCx, shaderCy,
					shaderRadius, shaderColor0, shaderColor1,
					Shader.TileMode.MIRROR);

			paint.setShader(radialGradientShader);
			canvas.drawRect(0, 0, w, h, paint);

			shaderCx = cx;
			shaderCy = cy;
			shaderRadius = radius;
			shaderColor0 = Color.RED;
			shaderColor1 = Color.BLUE;

			radialGradientShader = new RadialGradient(shaderCx, shaderCy,
					shaderRadius, shaderColor0, shaderColor1,
					Shader.TileMode.MIRROR);

			paint.setShader(radialGradientShader);
			canvas.drawCircle(cx, cy, radius, paint);
		}
	}
}
