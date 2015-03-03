package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SetPreference extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.app_android_preference);
	}
}
