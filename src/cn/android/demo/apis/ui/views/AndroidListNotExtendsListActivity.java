package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Not extends ListActivity
 * 
 * @author Elroy
 * 
 */
public class AndroidListNotExtendsListActivity extends Activity {
	ListView listView;
	String[] month = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_not_extends_listview);
		listView = (ListView) findViewById(R.id.lv_not_extends_listactivity);
		ListAdapter adapter = new ArrayAdapter<>(getApplicationContext(),
				R.layout.listview_textview_item, month);

		listView.setAdapter(adapter);
		listView.setFocusable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(getApplicationContext(),
//						parent.getItemAtPosition(position).toString(),
//						Toast.LENGTH_LONG).show();
				
				
				Log.v("DDD", "position:"+position);
			}
		});

	}

}
