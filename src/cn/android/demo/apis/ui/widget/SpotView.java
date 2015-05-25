package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

public class SpotView extends View {
	public interface SpotCallBack {
		void onTick(String msg);

		void onFinish(String msg);
	}

	SpotCallBack spotCallBack;
	CountDownTimer countDownTimer;

	int state;
	final static int STATE_IDLE = 0;
	final static int STATE_RED = 1;
	final static int STATE_GREEN = 2;
	final static int STATE_BLUE = 3;

	Paint paintRed;
	Paint paintGreen;
	Paint paintBlue;

	public SpotView(Context context) {
		super(context);
		init();
	}

	public SpotView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SpotView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		state = STATE_IDLE;

		paintRed = new Paint();
		paintRed.setColor(Color.RED);
		paintRed.setStrokeWidth(1);
		paintRed.setStyle(Style.FILL);

		paintGreen = new Paint();
		paintGreen.setColor(Color.GREEN);
		paintGreen.setStrokeWidth(1);
		paintGreen.setStyle(Style.FILL);

		paintBlue = new Paint();
		paintBlue.setColor(Color.BLUE);
		paintBlue.setStrokeWidth(1);
		paintBlue.setStyle(Style.FILL);
	}

	public void setCallback(SpotCallBack cb) {
		spotCallBack = cb;
	}

	public void startRun() {
		state = STATE_RED;
		if (countDownTimer != null) {
			countDownTimer.cancel();
		}
		countDownTimer = new CountDownTimer(3000, 500) {

			@Override
			public void onTick(long millisUntilFinished) {
				spotCallBack.onTick(String.valueOf(millisUntilFinished));
			}

			@Override
			public void onFinish() {
				if (state == STATE_RED) {
					state = STATE_GREEN;
					spotCallBack.onFinish("GREEN");

				} else if (state == STATE_GREEN) {
					state = STATE_BLUE;
					spotCallBack.onFinish("BLUE");
				} else if (state == STATE_BLUE) {
					state = STATE_RED;
					spotCallBack.onFinish("RED");
				}
				countDownTimer.start();
				invalidate();

			}
		};

		countDownTimer.start();
		spotCallBack.onFinish("RED");
		invalidate();

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		float w = getWidth();
		float h = getHeight();

		if (state == STATE_RED) {
			canvas.drawCircle(w / 4, h / 2, 100, paintRed);
		} else if (state == STATE_GREEN) {
			canvas.drawCircle(w / 2, h / 2, 100, paintGreen);
		} else if (state == STATE_BLUE) {
			canvas.drawCircle(3 * w / 4, h / 2, 100, paintBlue);

		}
	}

}
