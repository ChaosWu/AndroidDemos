package cn.android.demo.apis.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 自定义View 数据查询 基本demo
 * 
 * @author Elroy
 * 
 */
public class MultiChoiceListView extends Activity {
	ListView listView;
	Button getResult;
	MyArrayAdapter adapter;
	private ArrayList<String> dayOfWeekList = new ArrayList<String>();

	public void initDayOfWeekList() {
		dayOfWeekList.add("Sunday");
		dayOfWeekList.add("Monday");
		dayOfWeekList.add("Tuesday");
		dayOfWeekList.add("Wednesday");
		dayOfWeekList.add("Thursday");
		dayOfWeekList.add("Friday");
		dayOfWeekList.add("Saturday");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_multi_choice_listview);
		initDayOfWeekList();
		listView = (ListView) findViewById(R.id.multi_listview);
		getResult = (Button) findViewById(R.id.multi_listview_get_result);

		adapter = new MyArrayAdapter(this, R.layout.multi_listview_row,
				android.R.id.text1, dayOfWeekList);

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(onItemClickListener);

		getResult.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String result = "";

				List<Integer> resultList = adapter.getCheckedItemPositions();
				for (int i = 0; i < resultList.size(); i++) {
					result += String.valueOf(resultList.get(i)) + " ";
				}

				List<String> list = adapter.getCheckedItems();
				for (int i = 0; i < list.size(); i++) {
					result += String.valueOf(list.get(i)) + "\n";
				}
				adapter.getCheckedItemPositions().toString();
				Toast.makeText(getApplicationContext(), result,
						Toast.LENGTH_LONG).show();
			}
		});

	}

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			adapter.toggleChecked(position);
		}
	};

	private class MyArrayAdapter extends ArrayAdapter<String> {
		private HashMap<Integer, Boolean> myChecked = new HashMap<Integer, Boolean>();

		public MyArrayAdapter(Context context, int resource,
				int textViewResourceId, List<String> objects) {
			super(context, resource, textViewResourceId, objects);

			for (int i = 0; i < objects.size(); i++) {
				myChecked.put(i, false);
			}
		}

		public void toggleChecked(int position) {
			if (myChecked.get(position)) {
				myChecked.put(position, false);
			} else {
				myChecked.put(position, true);
			}
			notifyDataSetChanged();
		}

		public List<Integer> getCheckedItemPositions() {
			List<Integer> checkedItemPositions = new ArrayList<Integer>();
			for (int j = 0; j < myChecked.size(); j++) {
				if (myChecked.get(j)) {
					checkedItemPositions.add(j);
				}
			}
			return checkedItemPositions;

		}

		public List<String> getCheckedItems() {
			List<String> checkedItems = new ArrayList<String>();
			for (int i = 0; i < checkedItems.size(); i++) {
				if (myChecked.get(i)) {
					checkedItems.add(dayOfWeekList.get(i));
				}
			}
			return checkedItems;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.multi_listview_row, parent,
						false);
			}

			CheckedTextView checkedTextView = (CheckedTextView) row
					.findViewById(R.id.multi_checked_text1);
			checkedTextView.setText(dayOfWeekList.get(position));

			Boolean checked = myChecked.get(position);
			if (checked != null) {
				checkedTextView.setChecked(checked);
			}

			return row;
		}

	}
}
