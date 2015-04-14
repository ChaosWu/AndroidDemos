package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 温度传感器
 * 
 * @author Elroy
 * 
 */
public class AccessTemperatureSensor extends Activity {
	TextView textTEMPERATURE_available, textTEMPERATURE_reading;
	TextView textAMBIENT_TEMPERATURE_available,
			textAMBIENT_TEMPERATURE_reading;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_access_temperature_sensor);

		textTEMPERATURE_available = (TextView) findViewById(R.id.TEMPERATURE_available);
		textTEMPERATURE_reading = (TextView) findViewById(R.id.TEMPERATURE_reading);
		textAMBIENT_TEMPERATURE_available = (TextView) findViewById(R.id.AMBIENT_TEMPERATURE_available);
		textAMBIENT_TEMPERATURE_reading = (TextView) findViewById(R.id.AMBIENT_TEMPERATURE_reading);

		SensorManager mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		Sensor TemperatureSensor = mySensorManager
				.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
		if (TemperatureSensor != null) {
			textTEMPERATURE_available
					.setText("Sensor.TYPE_TEMPERATURE Available");
			mySensorManager.registerListener(TemperatureSensorListener,
					TemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);

		} else {
			textTEMPERATURE_available
					.setText("Sensor.TYPE_TEMPERATURE NOT Available");
		}

		Sensor AmbientTemperatureSensor = mySensorManager
				.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
		if (AmbientTemperatureSensor != null) {
			textAMBIENT_TEMPERATURE_available
					.setText("Sensor.TYPE_AMBIENT_TEMPERATURE Available");
			mySensorManager
					.registerListener(AmbientTemperatureSensorListener,
							AmbientTemperatureSensor,
							SensorManager.SENSOR_DELAY_NORMAL);
		} else {
			textAMBIENT_TEMPERATURE_available
					.setText("Sensor.TYPE_AMBIENT_TEMPERATURE NOT Available");
		}
	}

	private final SensorEventListener TemperatureSensorListener = new SensorEventListener() {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
				textTEMPERATURE_reading.setText("TEMPERATURE: "
						+ event.values[0]);
			}
		}

	};

	private final SensorEventListener AmbientTemperatureSensorListener = new SensorEventListener() {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
				textAMBIENT_TEMPERATURE_reading.setText("AMBIENT TEMPERATURE: "
						+ event.values[0]);
			}
		}

	};

}
