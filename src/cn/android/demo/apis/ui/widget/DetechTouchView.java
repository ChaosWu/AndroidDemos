package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DetechTouchView extends ImageView {

	Bitmap bm;
	Bitmap mask;
	double bmW;
	double bmH;

	String touchInfo;
	float tX;
	float tY;

	public DetechTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public DetechTouchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DetechTouchView(Context context) {
		super(context);
		init();
	}

	private void init() {
		bm = BitmapFactory.decodeResource(getResources(), R.drawable.android);

		mask = BitmapFactory.decodeResource(getResources(),
				R.drawable.android_mask);
		bmW = bm.getWidth();
		bmH = bm.getHeight();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			tX = event.getX();
			tY = event.getY();

			long maskColor = getColor(tX, tY);
			if (maskColor == Color.RED) {
				touchInfo = "Heart";
			} else if (maskColor == Color.GREEN) {
				touchInfo = "Head";
			} else if (maskColor == Color.BLUE) {
				touchInfo = "Body";
			} else if (maskColor == -256) {
				touchInfo = "Hand";
			} else if (maskColor == -16711681) {
				touchInfo = "Foot";
			} else {
				touchInfo = "";
			}
			DetechTouchActivity
					.notifyDetech(touchInfo, tX, tY, (int) maskColor);
			return true;
		default:
			return false;
		}
	}

	// private long getMaskColor(float x, float y) {
	// DetechTouchActivity parent = (DetechTouchActivity) (getContext());
	// return parent.getMaskColor(x, y);
	// }

	private long getColor(float x, float y) {
		if (x < 0 || y < 0 || x > getWidth() || y > getHeight()) {
			return 0;
		} else {
			int xBm = (int) (x * (bmW / (double) getWidth()));
			int yBm = (int) (y * (bmH / (double) getHeight()));

			return mask.getPixel(xBm, yBm);
		}

	}

	DetechChangeListener change;

	public interface DetechChangeListener {

		public void notifyDetech(String touchInfo, float tX, float tY,
				int maskColor);

	}

	public void setChangeListener(DetechChangeListener changeListener) {
		this.change = changeListener;
	}

}
