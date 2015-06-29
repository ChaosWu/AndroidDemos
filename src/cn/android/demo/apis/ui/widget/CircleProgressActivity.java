package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class CircleProgressActivity extends Activity implements
		View.OnClickListener {

	private CircleProgress mProgressView;
	private View mStartBtn;
	private View mStopBtn;
	private View mResetBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_widget_circle_progress);
		mProgressView = (CircleProgress) findViewById(R.id.progress);
		mProgressView.startAnim();
		mStartBtn = findViewById(R.id.start_btn);
		mStartBtn.setOnClickListener(this);
		mStopBtn = findViewById(R.id.stop_btn);
		mStopBtn.setOnClickListener(this);
		mResetBtn = findViewById(R.id.reset_btn);
		mResetBtn.setOnClickListener(this);

		SeekBar mSeekBar = (SeekBar) findViewById(R.id.out_seek);
		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float factor = seekBar.getProgress() / 100f;
				mProgressView.setRadius(factor);
			}
		});
	}



	@Override
	public void onClick(View v) {
		if (v == mStartBtn) {
			mProgressView.startAnim();
		} else if (v == mStopBtn) {
			mProgressView.stopAnim();
		} else if (v == mResetBtn) {
			mProgressView.reset();
		}
	}
}
