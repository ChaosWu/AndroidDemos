package cn.android.demo.apis.location;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidLocationActivity extends Activity {

	LocationManager locationManager;
	double myLatitude, myLongitude;

	TextView textLatitude, textLongitude, textLog;

	LinkedList<Location> locList;
	final static int LOG_SIZE = 5;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_activity);
		textLatitude = (TextView) findViewById(R.id.location_latitude);
		textLongitude = (TextView) findViewById(R.id.location_longitude);
		textLog = (TextView) findViewById(R.id.location_log);

		locList = new LinkedList<Location>();

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// for demo, getLastKnownLocation from GPS only, not from NETWORK
		Location lastLocation = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (lastLocation != null) {
			updateLoc(lastLocation);
		}
	}

	private void updateLoc(Location loc) {
		textLatitude.setText("Latitude: " + loc.getLatitude());
		textLongitude.setText("Longitude: " + loc.getLongitude());

		locList.add(loc);

		// maintain the LOG_SIZE
		if (locList.size() > LOG_SIZE) {
			locList.remove();
		}

		String locLog = "\n LOCATION LOG (last " + LOG_SIZE + " logs)\n";
		for (int i = 0; i < locList.size(); i++) {
			if (locList.get(i) != null) {

				String formatedTime = (new SimpleDateFormat("mm:ss:SSS"))
						.format(locList.get(i).getTime());

				locLog += "\n--- " + i + " ---\n" + "@ " + formatedTime + "\n"
						+ "Latitude: " + locList.get(i).getLatitude() + "\n"
						+ "Longitude: " + locList.get(i).getLongitude() + "\n"
						+ "Time: " + String.valueOf(locList.get(i).getTime())
						+ "\n" + "Provider: " + locList.get(i).getProvider()
						+ "\n";

				if (locList.get(i).hasAltitude()) {
					locLog += "Altitude: " + locList.get(i).getAltitude()
							+ "\n";
				}

				if (locList.get(i).hasAccuracy()) {
					locLog += "Accuracy: " + locList.get(i).getAccuracy()
							+ "(m)\n";
				}

				if (locList.get(i).hasBearing()) {
					locLog += "Bearing: " + locList.get(i).getBearing()
							+ "(m)\n";
				}

				if (locList.get(i).hasSpeed()) {
					locLog += "Speed: " + locList.get(i).getSpeed() + "(m)\n";
				}

			}

		}

		textLog.setText(locLog);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, myLocationListener);
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, myLocationListener);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationManager.removeUpdates(myLocationListener);
	}

	private LocationListener myLocationListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			updateLoc(location);

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}
	};

}