package cn.android.demo.apis.java;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Code add View
 * 
 * 阴影效果   文字/图片
 * 
 * 对于API等级11 ，你必须调用setLayerType （ INT layerType ，油漆涂料）来指定层支持的视图类型
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class JavaAddView extends Activity {
	LinearLayout layout;

	// 用泛型来提高效率
	@SuppressWarnings("unchecked")
	public <T extends View> T $id(int id) {
		return ((T) findViewById(id));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_code_add_view);
		layout = $id(R.id.ll_java_code_add_view);
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.chaoswu);

		TextView textView = new TextView(this);
		textView.setText("这是java代码添加的阴影");

		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		imageView.setLayoutParams(layoutParams);
		textView.setShadowLayer(5f, 10f, 10f, 0xFFffffff);

		MyView myView = new MyView(this);

		layout.addView(imageView);
		layout.addView(textView);
		layout.addView(myView);

		// textView1.setRotation(90);

	}

	private class MyView extends View {

		public MyView(Context context) {
			super(context);
		}

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			float cx = getWidth() / 3;
			float cy = getHeight() / 3;

			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.chaoswu);

			canvas.drawColor(Color.GRAY);
			Paint shadowPaint = new Paint();
			shadowPaint.setAntiAlias(true);
			shadowPaint.setColor(Color.WHITE);
			shadowPaint.setTextSize(45.0f);
			shadowPaint.setStrokeWidth(2.0f);
			shadowPaint.setStyle(Paint.Style.STROKE);
			shadowPaint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.BLACK);

			canvas.drawText("http://www.baidu.com/", 50, 200,
					shadowPaint);
			// android:minSdkVersion="11"
			setLayerType(LAYER_TYPE_SOFTWARE, shadowPaint);

			canvas.drawBitmap(bitmap, cx, cy, shadowPaint);
		}

	}

}
