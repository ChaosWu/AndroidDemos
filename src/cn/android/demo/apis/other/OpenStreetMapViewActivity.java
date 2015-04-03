package cn.android.demo.apis.other;

import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.os.Bundle;
//未完成
public class OpenStreetMapViewActivity extends Activity {

	private MapView myOpenMapView;
	private MapController myMapController;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_open_street_map_view);

		myOpenMapView = (MapView) findViewById(R.id.open_street_mapview);
		myOpenMapView.setBuiltInZoomControls(true);
		myMapController = myOpenMapView.getController();
		myMapController.setZoom(4);
	}

}