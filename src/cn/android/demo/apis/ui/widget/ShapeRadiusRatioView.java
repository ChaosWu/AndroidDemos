package cn.android.demo.apis.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class ShapeRadiusRatioView extends View {
	MyShape myShape;
	float ratioRadius;
	float ratioInnerRadius;

	int numberOfPoint = 3;// 默认
	TextView textLayerInfo;

	public ShapeRadiusRatioView(Context context) {
		super(context);
		init();
	}

	public ShapeRadiusRatioView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public ShapeRadiusRatioView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		myShape = new MyShape();
	}

	@SuppressLint("NewApi")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		long starting = System.nanoTime();

		int w = getWidth();
		int h = getHeight();

		if ((w <= 0) || (h <= 0)) {
			return;
		}
		float x = (float) w / 2.0F;
		float y = (float) h / 2.0F;
		float radius;
		float innerRadius;
		if (w > h) {
			radius = h * ratioRadius;
			innerRadius = h * ratioInnerRadius;
		} else {
			radius = w * ratioRadius;
			innerRadius = w * ratioInnerRadius;
		}
		myShape.setStar(x, y, radius, innerRadius, numberOfPoint);
		// myShape.setCircle(x, y, radius, Direction.CCW);
		canvas.drawPath(myShape.getPath(), myShape.getPaint());

		long end = System.nanoTime();

		String info = "myView.isHardwareAccelerated() = "
				+ isHardwareAccelerated() + "\n"
				+ "canvas.isHardwareAccelerated() = "
				+ canvas.isHardwareAccelerated() + "\n"
				+ "processing time (reference only) : "
				+ String.valueOf(end - starting) + " (ns)";
		textLayerInfo.setText(info);
	}

	public void setShapeRadiusRatio(float ratio) {
		ratioRadius = ratio;
	}

	public void setNumberOfPoint(int pt) {
		numberOfPoint = pt;
	}

	public void setShapeInnerRadiusRatio(float ratio) {
		ratioInnerRadius = ratio;
	}

	public void passElements(TextView tvLayerInfo) {
		this.textLayerInfo = tvLayerInfo;
	}

	public class MyShape {
		private Paint paint;
		private Path path;

		public MyShape() {
			paint = new Paint();
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(3);
			// paint.setStyle(Style.STROKE);
			paint.setStyle(Style.FILL_AND_STROKE);

			// ###########路径与圆的角落##############>>>
			float radius = 50.0f;
			CornerPathEffect cornerPathEffect = new CornerPathEffect(radius);
			paint.setPathEffect(cornerPathEffect);
			// >>>#########################
			path = new Path();
		}

		public void setCircle(float x, float y, float radius, Path.Direction dir) {
			path.reset();
			path.addCircle(x, y, radius, dir);
		}

		public void setPolygon(float x, float y, float radius, int numOfPt) {
			double section = 2.0 * Math.PI / numOfPt;

			path.reset();
			path.moveTo((float) (x + radius * Math.cos(0)), (float) (y + radius
					* Math.sin(0)));

			for (int i = 0; i < numOfPt; i++) {
				path.lineTo((float) (x + radius * Math.cos(section * i)),
						(float) (y + radius * Math.sin(section * i)));

			}
			path.close();
		}

		public void setStar(float x, float y, float radius, float innerRadius,
				int numOfPt) {

			double section = 2.0 * Math.PI / numOfPt;

			path.reset();
			path.moveTo((float) (x + radius * Math.cos(0)), (float) (y + radius
					* Math.sin(0)));
			path.lineTo(
					(float) (x + innerRadius * Math.cos(0 + section / 2.0)),
					(float) (y + innerRadius * Math.sin(0 + section / 2.0)));

			for (int i = 1; i < numOfPt; i++) {
				path.lineTo((float) (x + radius * Math.cos(section * i)),
						(float) (y + radius * Math.sin(section * i)));
				path.lineTo(
						(float) (x + innerRadius
								* Math.cos(section * i + section / 2.0)),
						(float) (y + innerRadius
								* Math.sin(section * i + section / 2.0)));
			}

			path.close();

		}

		public Path getPath() {
			return path;
		}

		public Paint getPaint() {
			return paint;
		}
	}

}
