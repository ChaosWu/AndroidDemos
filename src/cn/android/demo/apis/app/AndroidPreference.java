package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class AndroidPreference extends Activity {
	private CheckBox checkBox;
	private Button buttonSetPreference;
	private TextView editTextStatus;
	private TextView myListPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_android_preference);

		buttonSetPreference = (Button) findViewById(R.id.setpreference);
		checkBox = (CheckBox) findViewById(R.id.checkbox);
		editTextStatus = (TextView) findViewById(R.id.edittextstatus);

		myListPref = (TextView) findViewById(R.id.list_pref);

		buttonSetPreference.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(AndroidPreference.this,
						SetPreference.class));
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		ToastUtil.showToast(this, "onResume  ");

		SharedPreferences myPreference = PreferenceManager
				.getDefaultSharedPreferences(this);
		checkBox.setChecked(myPreference.getBoolean("checkbox", true));

		editTextStatus.setText("EditText Status: "
				+ myPreference.getString("edittexvalue", ""));

		myListPref
				.setText(myPreference.getString("listPref", "default choice"));

	}
}
