package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferencesEditor extends Activity implements
		OnClickListener {

	private EditText et1;
	private EditText et2;

	private TextView tv1;
	private TextView tv2;

	private Button bt1;
	private Button bt2;

	private static final String KEY_1 = "MEM1";
	private static final String KEY_2 = "MEM2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_shared_preferences_editor);
		tv1 = (TextView) findViewById(R.id.tv_save_1);
		tv2 = (TextView) findViewById(R.id.tv_save_2);

		et1 = (EditText) findViewById(R.id.et_get_mem_1);
		et2 = (EditText) findViewById(R.id.et_get_mem_2);

		bt1 = (Button) findViewById(R.id.bt_save_mem1);
		bt2 = (Button) findViewById(R.id.bt_save_mem2);

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);

		LoadPreferences();
	}

	private void LoadPreferences() {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString(KEY_1, "");
		String strSavedMem2 = sharedPreferences.getString(KEY_2, "");
		tv1.setText(strSavedMem1);
		tv2.setText(strSavedMem2);
	}

	private void SavePreferences(String key, String value) {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_save_mem1:
			SavePreferences(KEY_1, et1.getText().toString());
			LoadPreferences();
			break;
		case R.id.bt_save_mem2:
			SavePreferences(KEY_2, et2.getText().toString());
			LoadPreferences();
			break;
		default:
			break;
		}
	}

}
