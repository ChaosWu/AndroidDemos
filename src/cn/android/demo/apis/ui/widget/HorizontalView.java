package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class HorizontalView extends View {
	private float pitch = 0, roll = 0;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private boolean firstDraw;
	final float radiusPt = (float) 10;

	public HorizontalView(Context context) {
		super(context);
		init();
	}

	public HorizontalView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public HorizontalView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {

		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(15);
		paint.setColor(Color.WHITE);
		paint.setTextSize(20);

		firstDraw = true;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 中心点的位置
		int cxPlan = getMeasuredWidth() / 2;
		int cyPlan = getMeasuredHeight() / 2;

		float fullLength, halfLength, ptPerDegree;
		//
		if (cxPlan > cyPlan) {
			fullLength = (float) (getMeasuredHeight() * 0.9);

		} else {
			fullLength = (float) (getMeasuredWidth() * 0.9);
		}
		halfLength = fullLength / 2;
		ptPerDegree = fullLength / 360;
		// l t r b 内
		canvas.drawRect(cxPlan - halfLength, cyPlan - halfLength, cxPlan
				+ halfLength, cyPlan + halfLength, paint);

		// Log.v("DDD", "cxPlan:" + (cxPlan));// 540
		// Log.v("DDD", "halfLength:" + (halfLength));// 385
		// Log.v("DDD", "左:" + (cxPlan - halfLength));// 154
		// Log.v("DDD", "上:" + (cyPlan - halfLength));// 40
		// Log.v("DDD", "右:" + (cxPlan + halfLength));// 925
		// Log.v("DDD", "下:" + (cyPlan + halfLength));// 813

		// l t r b 外
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

		if (!firstDraw) {
			float yPt = cyPlan + (pitch * ptPerDegree);
			float xPt = cxPlan + (roll * ptPerDegree);
			canvas.drawCircle(xPt, yPt, radiusPt, paint);
		}
	}

	public void updateHorizontal(float tPitch, float tRoll) {
		firstDraw = false;
		pitch = tPitch;
		roll = tRoll;
		invalidate();
	}
}