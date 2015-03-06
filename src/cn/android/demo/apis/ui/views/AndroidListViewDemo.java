package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import cn.android.demo.utils.BitmapUtil;
import cn.android.demo.utils.ConfigUtil;
import cn.android.demo.utils.ToastUtil;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AndroidListViewDemo extends ListActivity {
	// private String imageUrl =
	// "https://avatars2.githubusercontent.com/u/4157575?v=3&s=460";
	// private String imageUrl =
	// "http://a.hiphotos.baidu.com/image/pic/item/09fa513d269759ee7d56a5cdb1fb43166d22dfb6.jpg";
	private String[] month = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" };

	String[] DayOfWeek = new String[] { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };
	private Bitmap bitmap;

	private ArrayAdapter<String> weekOfDayAdapter;
	private MyCustomAdapter monthAdapter;

	private String strMonth;

	private String strDayOfWeek;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("DDD", "ListView onCreate！");
		// setListAdapter(new ArrayAdapter<String>(this,
		// R.layout.ui_view_listview_row,R.id.weekofday, month));

		weekOfDayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, DayOfWeek);
		monthAdapter = new MyCustomAdapter(this, R.layout.ui_view_listview_row,
				month);
		setListAdapter(monthAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String selection = l.getItemAtPosition(position).toString();
		ToastUtil.showToast(AndroidListViewDemo.this, selection);
		if (getListAdapter() == monthAdapter) {
			strMonth = (String) getListView().getItemAtPosition(position);
			setListAdapter(weekOfDayAdapter);
			weekOfDayAdapter.notifyDataSetChanged();
		} else {
			strDayOfWeek = (String) getListView().getItemAtPosition(position);
			ToastUtil.showToast(this, strMonth + ":" + strDayOfWeek);
			setListAdapter(monthAdapter);
			monthAdapter.notifyDataSetChanged();
		}

	}

	public class MyCustomAdapter extends ArrayAdapter<String> {

		public MyCustomAdapter(Context context, int resource, String[] objects) {
			super(context, resource, objects);
			new Thread(new Runnable() {

				@Override
				public void run() {
					BitmapFactory.Options options;
					options = new BitmapFactory.Options();
					options.inSampleSize = 1;
					bitmap = BitmapUtil.LoadImage(ConfigUtil.imageHeadUrl,
							options);

				}
			}).start();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;

			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.ui_view_listview_row, parent,
						false);
			}

			TextView label = (TextView) row.findViewById(R.id.weekofday);
			label.setText(month[position]);

			ImageView icon = (ImageView) row.findViewById(R.id.icon);
			icon.setImageBitmap(bitmap);
			return row;
		}

	}
}
