package cn.android.demo.apis.bug;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class DebugActivity extends Activity {

	Button btnDisplaySetting;
	Intent intentDisplaySetting;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug_activity);
		btnDisplaySetting = (Button) findViewById(R.id.displaySetting);

		intentDisplaySetting = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
		ResolveInfo resolveInfo = getPackageManager().resolveActivity(
				intentDisplaySetting, 0);

		if (resolveInfo == null) {
			ToastUtil.showToast(this, "Not Support!");
			btnDisplaySetting.setEnabled(false);
		} else {
			btnDisplaySetting.setEnabled(true);
		}

		btnDisplaySetting.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(intentDisplaySetting);
			}
		});
	}
}