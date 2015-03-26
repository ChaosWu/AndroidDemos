package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyWidgetView extends View {
	final int MIN_WIDTH = 800;
	final int MIN_HEIGHT = 300;

	final int DEFAULT_COLOR = Color.WHITE;

	int _color;

	final int STROKE_WIDTH = 2;
	Paint paint;
	Path path1;
	Path path2;

	public MyWidgetView(Context context) {
		super(context);
		init();
	}

	public MyWidgetView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public MyWidgetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init() {
		// 设置的最小值
		setMinimumWidth(MIN_WIDTH);
		setMinimumHeight(MIN_HEIGHT);
		_color = DEFAULT_COLOR;
		paint = new Paint();
		paint.setStrokeWidth(STROKE_WIDTH);

		path1 = new Path();
		path2 = new Path();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 获取最小值
		super.onMeasure(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(_color);
		canvas.drawRect(5, 5, getWidth() - 5, getHeight() - 5, paint);

		drawText(canvas);
	}

	private void drawText(Canvas canvas) {
		paint.setColor(Color.GRAY);
		paint.setTextSize(25);

		path1.addArc(new RectF(25, 25, getWidth() - 25, getHeight() - 25), 180,
				90);
		canvas.drawTextOnPath("Hello", path1, 0, 0, paint);

		path2.addArc(new RectF(25, 25, getWidth() - 25, getHeight() - 25), 0, 90);
		canvas.drawTextOnPath("Android", path2, 0, 0, paint);

	}

	public void setColor(int color) {
		_color = color;

	}
}