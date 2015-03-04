package cn.android.demo.apis.ui.resources;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class StringSpinner extends Activity {
	Spinner spinner;
	TextView textView;
	String[] dayofWeek;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_resources_string_spinner);
		dayofWeek = getResources().getStringArray(R.array.DayofWeek);
		spinner = (Spinner) findViewById(R.id.spinner);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.simple_spinner_dropdown_item, R.id.weekofday,
				dayofWeek);
		MyCustomAdapter adapter2 = new MyCustomAdapter(this,
				R.layout.simple_spinner_dropdown_item, dayofWeek);
		spinner.setAdapter(adapter2);


	}

	public class MyCustomAdapter extends ArrayAdapter<String> {

		public MyCustomAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			// return super.getView(position, convertView, parent);

			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.simple_spinner_dropdown_item,
					parent, false);
			TextView label = (TextView) row.findViewById(R.id.weekofday);
			label.setText(dayofWeek[position]);

			// ImageView icon = (ImageView) row.findViewById(R.id.icon);

			// if (dayofWeek[position] == "Sunday") {
			// icon.setImageResource(R.drawable.icon);
			// } else {
			// icon.setImageResource(R.drawable.icongray);
			// }

			return row;
		}
	}
}
