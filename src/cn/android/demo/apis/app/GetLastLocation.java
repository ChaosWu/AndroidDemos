package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class GetLastLocation extends Activity {

	TextView textView_GpsLocation, textView_NetworkLocation,
			textView_MyLocation;

	final String gpsLocationProvider = LocationManager.GPS_PROVIDER;
	final String networkLocationProvider = LocationManager.NETWORK_PROVIDER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_get_last_location);
		textView_GpsLocation = (TextView) findViewById(R.id.textgpslocation);
		textView_NetworkLocation = (TextView) findViewById(R.id.textnetworklocation);
		textView_MyLocation = (TextView) findViewById(R.id.textmylocation);

		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		Location lastKnownLocation_byGps = locationManager
				.getLastKnownLocation(gpsLocationProvider);
		Location lastKnownLocation_byNetwork = locationManager
				.getLastKnownLocation(networkLocationProvider);

		if (lastKnownLocation_byGps == null) {
			textView_GpsLocation.setText("GPS Last Location not available");

			if (lastKnownLocation_byNetwork == null) {
				textView_NetworkLocation
						.setText("Network Last Location not available");
				textView_MyLocation.setText("No Last Known Location!");
			} else {
				textView_NetworkLocation.setText("Network Location:\n"
						+ lastKnownLocation_byNetwork.toString());

				textView_MyLocation.setText("My Location is "
						+ lastKnownLocation_byNetwork.getLatitude() + " : "
						+ lastKnownLocation_byNetwork.getLongitude());
			}

		} else {
			textView_GpsLocation.setText("GPS Location:\n"
					+ lastKnownLocation_byGps.toString());

			if (lastKnownLocation_byNetwork == null) {
				textView_NetworkLocation
						.setText("Network Last Location not available");
				textView_MyLocation.setText("My Location is "
						+ lastKnownLocation_byGps.getLatitude() + " : "
						+ lastKnownLocation_byGps.getLongitude());
			} else {
				textView_NetworkLocation.setText("Network Location:\n"
						+ lastKnownLocation_byNetwork.toString());

				// Both Location provider have last location
				// decide location base on accuracy
				if (lastKnownLocation_byGps.getAccuracy() <= lastKnownLocation_byNetwork
						.getAccuracy()) {
					textView_MyLocation.setText("My Location from GPS\n"
							+ lastKnownLocation_byGps.getLatitude() + " : "
							+ lastKnownLocation_byGps.getLongitude());
				} else {
					textView_MyLocation.setText("My Location from Network\n"
							+ lastKnownLocation_byNetwork.getLatitude() + " : "
							+ lastKnownLocation_byNetwork.getLongitude());
				}

			}
		}

	}

}