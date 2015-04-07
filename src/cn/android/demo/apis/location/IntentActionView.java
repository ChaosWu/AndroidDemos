package cn.android.demo.apis.location;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//没效果
public class IntentActionView extends Activity {
	private final static String TAG = IntentActionView.class.getSimpleName();
	private Button btGetLocation;
	private EditText etXPostion;
	private EditText etYPostion;

	private TextView tvResult;

	private String x;
	private String y;
	private String uriStr;

	LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_google_map);

		btGetLocation = (Button) findViewById(R.id.bt_get_location);
		etXPostion = (EditText) findViewById(R.id.et_x_postion);
		etYPostion = (EditText) findViewById(R.id.et_y_postion);

		tvResult = (TextView) findViewById(R.id.tv_location_result);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			updateLoc(location);
		}

		btGetLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				x = etXPostion.getText().toString();
				y = etYPostion.getText().toString();
				Log.v("DDD","点击了："+x);
				if (x.equals("") || x == null || y.equals("") || y == null) {
					return;
				}

				tvResult.setText("x :" + x + " ,y :" + y);
				Uri uri = Uri.parse(uriStr);

				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(uri);
				startActivity(intent);

			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, listener);

	}

	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(listener);

	}

	private void updateLoc(Location loc) {
		etXPostion.setText("" + loc.getLatitude());
		etYPostion.setText("" + loc.getLongitude());
		
		Log.v("DDD","Latitude:"+ loc.getLatitude());
		Log.v("DDD","Longitude:"+ loc.getLongitude());
	}

	LocationListener listener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLocationChanged(Location location) {
			updateLoc(location);
		}
	};
}
