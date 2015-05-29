package cn.android.demo.apis.phone;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SensorList extends ListActivity {
	SensorManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);

		List<String> listSensorType = new ArrayList<String>();
		for (int i = 0; i < sensors.size(); i++) {
			listSensorType.add(sensors.get(i).getName());

		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listSensorType);

		setListAdapter(adapter);

		// getListView().setTextFilterEnabled(true);

	}
}
