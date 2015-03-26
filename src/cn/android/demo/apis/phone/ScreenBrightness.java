package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 
 * 
 * WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
 * layoutParams.screenBrightness = BackLightValue;
 * getWindow().setAttributes(layoutParams);
 * 
 * 
 * @author Elroy
 * 
 */
public class ScreenBrightness extends Activity {
	TextView systemBrightness;
	TextView BackLightSetting;
	SeekBar BackLightControl;

	Button updatesystemsetting;
	Button screenOrientation;

	int curBrightnessValue;// 系统亮度
	float BackLightValue = 0.5f; // dummy default value

	private boolean isOrientation = false;// 屏幕旋转方向

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_screen_brightness);

		try {
			curBrightnessValue = android.provider.Settings.System.getInt(
					getContentResolver(),
					android.provider.Settings.System.SCREEN_BRIGHTNESS);
		} catch (SettingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BackLightControl = (SeekBar) findViewById(R.id.backlightcontrol);
		BackLightSetting = (TextView) findViewById(R.id.backlightsetting);
		updatesystemsetting = (Button) findViewById(R.id.updatesystemsetting);

		// screenOrientation = (Button)
		// findViewById(R.id.bt_screen_orientation);

		systemBrightness = (TextView) findViewById(R.id.tv_system_screen_brightness);
		systemBrightness.setText("系统亮度：" + curBrightnessValue);

		updatesystemsetting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int SysBackLightValue = (int) (BackLightValue * 255);
				android.provider.Settings.System.putInt(getContentResolver(),
						android.provider.Settings.System.SCREEN_BRIGHTNESS,
						SysBackLightValue);
			}
		});
		BackLightControl
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

					@Override
					public void onProgressChanged(SeekBar arg0, int progress,
							boolean arg2) {
						// TODO Auto-generated method stub
						float BackLightValue = (float) progress / 100;
						BackLightSetting.setText(String.valueOf(BackLightValue));
						// 获取当前窗口的属性相关面板
						WindowManager.LayoutParams layoutParams = getWindow()
								.getAttributes();
						layoutParams.screenBrightness = BackLightValue;
						getWindow().setAttributes(layoutParams);

					}

					@Override
					public void onStartTrackingTouch(SeekBar arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onStopTrackingTouch(SeekBar arg0) {
						// TODO Auto-generated method stub

					}
				});

		// screenOrientation.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// if (!isOrientation) {
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// isOrientation = true;
		// } else {
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// isOrientation = false;
		//
		// }
		// }
		// });
	}
}