package cn.android.demo.apis.phone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 通过地理编码名称搜索地址
 * 
 * android.location.Geocoder的getFromLocationName() 方法返回已知描述命名的位置的地址的数组。
 * 
 * @author Elroy
 * 
 */
public class SearchAddressByNameWithGeocoder extends Activity {

	EditText et;
	Button bt;
	ListView lv;

	Geocoder geocoder;
	ArrayAdapter<String> adapter;

	static int maxResults = 5;
	List<Address> locationList;
	List<String> locationNameList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_search_address_by_name_with_geocoder);
		et = (EditText) findViewById(R.id.et_serch_address_with_geocoder_in);
		bt = (Button) findViewById(R.id.bt_serch_address_with_geocoder);
		lv = (ListView) findViewById(R.id.lv_serch_address_with_geocoder_out);

		bt.setOnClickListener(onClickListener);

		geocoder = new Geocoder(this, Locale.ENGLISH);
		locationNameList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, locationNameList);
		lv.setAdapter(adapter);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			String locationName = et.getText().toString();

			Toast.makeText(getApplicationContext(),
					"Search for: " + locationName, Toast.LENGTH_SHORT).show();

			if (locationName == null) {
				Toast.makeText(getApplicationContext(), "locationName == null",
						Toast.LENGTH_LONG).show();
			} else {
				try {
					locationList = geocoder.getFromLocationName(locationName,
							maxResults);

					if (locationList == null) {
						Toast.makeText(getApplicationContext(),
								"locationList == null", Toast.LENGTH_LONG)
								.show();
					} else {
						if (locationList.isEmpty()) {
							Toast.makeText(getApplicationContext(),
									"locationList is empty", Toast.LENGTH_LONG)
									.show();
						} else {
							Toast.makeText(getApplicationContext(),
									"number of result: " + locationList.size(),
									Toast.LENGTH_LONG).show();

							locationNameList.clear();

							for (Address i : locationList) {
								if (i.getFeatureName() == null) {
									locationNameList.add("unknown");
								} else {
									locationNameList.add(i.getFeatureName());
								}
							}

							adapter.notifyDataSetChanged();
						}
					}

				} catch (IOException e) {
					Toast.makeText(
							getApplicationContext(),
							"network unavailable or any other I/O problem occurs"
									+ locationName, Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		}
	};

}