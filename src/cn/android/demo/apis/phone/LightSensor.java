package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
/**
 * 感光传感器
 * @author Elroy
 *
 */
public class LightSensor extends Activity {
	SensorManager mySensorManager;
	Sensor myLightSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_light_sensor);

		TextView tv = (TextView) findViewById(R.id.lightsensor);
		mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		myLightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

		if (myLightSensor == null) {
			tv.setText("No Light Sensor!");

		} else {
			tv.setText("= =! " + myLightSensor.getName() + "\n "
					+ myLightSensor.getPower());
		}
	}
}
