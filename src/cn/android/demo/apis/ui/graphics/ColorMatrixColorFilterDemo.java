package cn.android.demo.apis.ui.graphics;

import cn.android.demo.apis.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/*
 * 5x4 matrix for transforming the color+alpha components of a Bitmap. 
 * The matrix is stored in a single array, and its treated as follows: 
 * [  a, b, c, d, e, 
 *   f, g, h, i, j, 
 *   k, l, m, n, o, 
 *   p, q, r, s, t ] 
 * 
 * When applied to a color [r, g, b, a], the resulting color is computed 
 * as (after clamping) 
 * R' = a*R + b*G + c*B + d*A + e; 
 * G' = f*R + g*G + h*B + i*A + j; 
 * B' = k*R + l*G + m*B + n*A + o; 
 * A' = p*R + q*G + r*B + s*A + t;
 */

public class ColorMatrixColorFilterDemo extends Activity {
	/*
	 * 转换ImageView的黑白图像使用ColorFilter
	 * 这次演习“示例应用ColorFilter在ImageView的”展示ColorFilter的基本操作
	 * 。通过采用合适的嘉洛斯，很容易将彩色图像转换为黑色和白色。
	 * 
	 * private float brightness; float[] colorMatrix = {
	 * 
	 * 0.33f, 0.33f, 0.33f, 0,brightness, // red
	 * 
	 * 0.33f, 0.33f, 0.33f, 0, brightness, // green
	 * 
	 * 0.33f,0.33f, 0.33f, 0, brightness, // blue
	 * 
	 * 0, 0, 0, 1, 0 // alpha };
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(new MyView(this));
	}

	private class MyView extends View {
		Context context;
		Paint paint;
		Paint paintBg;

		public MyView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context ctx) {
			this.context = ctx;
			paint = new Paint();
			paintBg = new Paint();
			paintBg.setColor(Color.WHITE);

		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			int w = MeasureSpec.getSize(widthMeasureSpec);
			int h = MeasureSpec.getSize(heightMeasureSpec);
			setMeasuredDimension(w, h);
			// 1080:1770
		}

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

			float[] colorMatrix = { 1, 0, 0, 0, 0, // red
					0, 0, 0, 0, 0, // green
					0, 0, 0, 0, 0, // blue
					0, 0, 0, 1, 0 // alpha
			};
			// 常规颜色修改
			ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
			paint.setColorFilter(colorFilter);
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.radio_green_color);

			canvas.drawBitmap(bitmap, 0, 0, paint);
			// 颜色反转
			float[] colorMatrix_Negative = { -1.0f, 0, 0, 0, 255, // red
					0, -1.0f, 0, 0, 255, // green
					0, 0, -1.0f, 0, 255, // blue
					0, 0, 0, 1.0f, 0 // alpha
			};

			colorFilter = new ColorMatrixColorFilter(colorMatrix_Negative);
			paint.setColorFilter(colorFilter);
			canvas.drawBitmap(bitmap, 0, bitmap.getHeight(), paint);

			// LightingColorFilter
			// 它的改变法则是图片的RGB值分别*mul+add，然后对255求余，最后得到新的RGB值，整个过程中alpha不参与改变
			int mul = 0xFFFFFF00; // remove BLUE component
			int add = 0x0000FF00; // set GREEN full
			LightingColorFilter lightingColorFilter = new LightingColorFilter(
					mul, add);
			paint.setColorFilter(lightingColorFilter);
			canvas.drawBitmap(bitmap, 0, bitmap.getHeight() * 2, paint);

			// 因为上一个图片变为绿色，所有 叠加 就变成全绿的正方形
			int srcColor = 0xFF00FF00;
			android.graphics.PorterDuff.Mode mode = android.graphics.PorterDuff.Mode.ADD;
			PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(
					srcColor, mode);

			paint.setColorFilter(porterDuffColorFilter);

			canvas.drawBitmap(bitmap, 0, bitmap.getHeight() * 3, paint);
		}
	}

}
