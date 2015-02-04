package cn.android.demo.apis.ui.resources;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class StringSpinner extends Activity {
	Spinner spinner;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_resources_string_spinner);

		spinner = (Spinner) findViewById(R.id.spinner);
		String[] dayofWeek = getResources().getStringArray(R.array.DayofWeek);

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_item, dayofWeek);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(adapter);

		textView = (TextView) findViewById(R.id.numberofman);
		int numberofMan = 2;
		String strNumberofman = getResources().getQuantityString(
				R.plurals.NumberOfMan, numberofMan);
		
		
		textView.setText("It's " + String.valueOf(numberofMan) + " "
		        + strNumberofman + " here.");

	}
}
