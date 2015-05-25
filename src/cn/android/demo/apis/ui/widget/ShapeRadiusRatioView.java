package cn.android.demo.apis.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;
import android.graphics.PathDashPathEffect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class ShapeRadiusRatioView extends View {
	MyShape myShape;
	float ratioRadius;
	float ratioInnerRadius;
	float rotate;

	int numberOfPoint = 3;// 默认
	TextView textLayerInfo;
	private Matrix matrix;
	// 虚线效果
	DashPathEffect dashPathEffect;
	// 动态线效果
	PathDashPathEffect pathDashPathEffect;

	Path dashPath;
	float phase;

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
		matrix = new Matrix();

		// TODO init DashPathEffect
		dashPathEffect = new DashPathEffect(new float[] { 10.0f, 5.0f }, // interval
				0); // phase

		dashPath = new Path();
		dashPath.addCircle(0, 0, 3, Direction.CCW);
		phase = 0.0f;
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

		// TODO 1 旋转使用Matrix
		// Rotate the path by angle in degree
		// Path path = myShape.getPath();
		// matrix.reset();
		// matrix.postRotate(rotate, x, y);
		// path.transform(matrix);

		// TODO 2 旋转使用画布
		// Save and rotate canvas
		canvas.save();
		canvas.rotate(rotate, x, y);

		Paint paintDash = myShape.getPaint();
		paintDash.setPathEffect(dashPathEffect);

		// 动态效果
		// phase++;
		// pathDashPathEffect = new PathDashPathEffect(dashPath, 15.0f, phase,
		// PathDashPathEffect.Style.MORPH);

		// Paint paintDash2 = myShape.getPaint();
		// paintDash2.setPathEffect(pathDashPathEffect);

		//
		// phase++;
		// PathDashPathEffect pathDashPathEffect = new PathDashPathEffect(
		// dashPath, advance, phase, PathDashPathEffect.Style.MORPH);
		//
		// Paint paintDash = myShape.getPaint();
		// paintDash.setPathEffect(pathDashPathEffect);

		canvas.drawPath(myShape.getPath(), myShape.getPaint());

		// restore canvas
		canvas.restore();
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

	public void setShapeRotate(int rot) {
		rotate = (float) rot;
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
