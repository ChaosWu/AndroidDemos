package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Bitmap.Config;
import android.graphics.PaintFlagsDrawFilter;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * 自定义控件 画矩形 扩充,人脸识别
 * 
 * @author Elroy
 * 
 */
public class CanvasBitmap extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(new CanvasBitmapView(this));
	}

	private class CanvasBitmapView extends View {
		private Paint mPaint;
		private PointF mMidPoint;
		private Bitmap bitmap;
		// 图片宽高
		private int imageW;
		private int imageH;
		// 屏幕宽高
		private int screenW;
		private int screenH;
		// 人脸识别
		private FaceDetector detector;
		private FaceDetector.Face[] mFace;

		private int numberOfFace = 5;
		private int numberOfFaceDetected;
		private float mEyesDistance;

		public CanvasBitmapView(Context context) {
			super(context);
			Log.v("DDD", "CanvasBitmapView...");
			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			// DisplayMetrics dm = new DisplayMetrics();
			// getWindowManager().getDefaultDisplay().getMetrics(dm);
			// screenW = dm.widthPixels;
			// screenH = dm.heightPixels;
			// Log.v("DDD", "w: " + screenW);
			// Log.v("DDD", "h: " + screenH);
			BitmapFactory.Options options = new Options();
			options.inPreferredConfig = Bitmap.Config.RGB_565;

			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.face_1, options);
//			bitmap = BitmapFactory.decodeResource(getResources(),
//					R.drawable.chaoswu, options);

			imageW = bitmap.getWidth();
			imageH = bitmap.getHeight();

			mFace = new FaceDetector.Face[numberOfFace];
			detector = new FaceDetector(imageW, imageH, numberOfFace);
			numberOfFaceDetected = detector.findFaces(bitmap, mFace);

			mPaint = new Paint();
			mPaint.setColor(Color.GREEN);
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setStrokeWidth(5);

			mMidPoint = new PointF();

		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			Log.v("DDD", "onDraw...");
			canvas.drawBitmap(bitmap, 0, 0, null);

			for (int i = 0; i < numberOfFaceDetected; i++) {
				Face face = mFace[i];
				face.getMidPoint(mMidPoint);
				mEyesDistance = face.eyesDistance();
				drawFaceRect(canvas, mEyesDistance);

			}
//			canvas.drawRect(10, 10, 100, 100, mPaint);
		}

		/**
		 * 画脸的框子
		 * 
		 * @param canvas
		 * @param distance
		 */
		public void drawFaceRect(Canvas canvas, float distance) {
			canvas.drawRect(mMidPoint.x - distance, mMidPoint.y - distance,
					mMidPoint.x + distance, mMidPoint.y + distance, mPaint);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			Log.v("DDD", "onMeasure...");
			int widthSize = MeasureSpec.getSize(widthMeasureSpec);
			int heightSize = MeasureSpec.getSize(heightMeasureSpec);
			Log.v("DDD", "widthSize: " + widthSize);
			Log.v("DDD", "heightSize: " + heightSize);
			// setIamgeSize(widthSize, heightSize);
			setMeasuredDimension(widthSize, heightSize);
		}

		/**
		 * 设置图片大小
		 * 
		 * @param widthSize
		 * @param heightSize
		 */
		public void setIamgeSize(int widthSize, int heightSize) {

		}
	}

}
