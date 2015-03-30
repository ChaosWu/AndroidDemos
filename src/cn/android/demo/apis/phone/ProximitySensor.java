package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 接近传感器
 * 
 * @author Elroy
 * 
 */
public class ProximitySensor extends Activity {
	TextView ProximitySensor;
	TextView ProximityMax;
	TextView ProximityReading;

	SensorManager sensorManager;
	Sensor sensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_proximity_sensor);

		ProximitySensor = (TextView) findViewById(R.id.tv_proximitySensor);
		ProximityMax = (TextView) findViewById(R.id.tv_proximityMax);
		ProximityReading = (TextView) findViewById(R.id.tv_proximityReading);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

		if (sensor == null) {
			ProximitySensor.setText("No Proximity Sensor!");
		} else {
			ProximitySensor.setText(sensor.getName());

			ProximityMax.setText("Maximum Range: "
					+ String.valueOf(sensor.getMaximumRange()));
			sensorManager.registerListener(eventListener,
					sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}

	}

	SensorEventListener eventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
				ProximityReading.setText("Proximity Sensor Reading:"
						+ String.valueOf(event.values[0]));
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}
	};
}
