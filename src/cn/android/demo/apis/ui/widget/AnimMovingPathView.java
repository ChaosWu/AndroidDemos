package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

public class AnimMovingPathView extends View {
	Paint paint;
	Bitmap bm;
	int bmOffsetX;
	int bmOffsetY;
	
	Path animPath;
	PathMeasure pathMeasure;
	float pahtLength;

	public AnimMovingPathView(Context context) {
		super(context);
	}

	public AnimMovingPathView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AnimMovingPathView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

}
