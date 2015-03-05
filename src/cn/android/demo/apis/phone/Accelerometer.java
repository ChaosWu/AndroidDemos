package cn.android.demo.apis.phone;

import java.util.List;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * 传感器
 * 
 * @author Elroy
 * 
 */
public class Accelerometer extends Activity {
	private SensorManager sensorManager;
	private boolean accelerometerPresent;
	private Sensor accelerometerSensor;

	private TextView tv_x;
	private TextView tv_y;
	private TextView tv_z;

	private SeekBar seekBar_x;
	private SeekBar seekBar_y;
	private SeekBar seekBar_z;

	private float SensitiveX;
	private float SensitiveY;
	private float SensitiveZ;

	private TextView text_xrate;
	private TextView text_zrate;
	private TextView text_ystate;
	private TextView text_zstate;
	private TextView text_xstate;
	private TextView text_yrate;

	private TextView face;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_accelerometer);

		face = (TextView) findViewById(R.id.tv_face_accelerometer);

		tv_x = (TextView) findViewById(R.id.tv_accelerometer_x);
		tv_y = (TextView) findViewById(R.id.tv_accelerometer_y);
		tv_z = (TextView) findViewById(R.id.tv_accelerometer_z);

		text_xrate = (TextView) findViewById(R.id.xrate);
		text_xstate = (TextView) findViewById(R.id.xstate);

		text_yrate = (TextView) findViewById(R.id.yrate);
		text_ystate = (TextView) findViewById(R.id.ystate);

		text_zrate = (TextView) findViewById(R.id.zrate);
		text_zstate = (TextView) findViewById(R.id.zstate);

		seekBar_x = (SeekBar) findViewById(R.id.sb_accelerometer_xsensitive);
		seekBar_y = (SeekBar) findViewById(R.id.sb_accelerometer_ysensitive);
		seekBar_z = (SeekBar) findViewById(R.id.sb_accelerometer_zsensitive);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

		if (sensors.size() > 0) {
			accelerometerPresent = true;
			accelerometerSensor = sensors.get(0);
			Log.v("DDD", "Sensor Name: " + accelerometerSensor.getName());
			// nubia-mini:
			// Sensor Name:MPU6500
			// Accelerometer,Gyroscope,Temperature,Double-Tap
		} else {
			accelerometerPresent = false;
		}

		seekBar_x.setOnSeekBarChangeListener(barChangeListener);
		seekBar_y.setOnSeekBarChangeListener(barChangeListener);
		seekBar_z.setOnSeekBarChangeListener(barChangeListener);

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (accelerometerPresent) {
			sensorManager.registerListener(eventListener, accelerometerSensor,
					SensorManager.SENSOR_DELAY_NORMAL);

		}
	}

	@Override
	protected void onStop() {
		super.onStop();

		if (accelerometerPresent) {
			sensorManager.unregisterListener(eventListener);
		}
	}

	private OnSeekBarChangeListener barChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (seekBar == seekBar_x) {
				SensitiveX = progress;

			} else if (seekBar == seekBar_y) {
				SensitiveY = progress;

			} else if (seekBar == seekBar_z) {
				SensitiveZ = progress;

			}
		}
	};
	private SensorEventListener eventListener = new SensorEventListener() {
		float lastx, lasty, lastz;
		long lasttime;
		boolean firsttime = true;

		@Override
		public void onSensorChanged(SensorEvent event) {
			float x_value = event.values[0];
			float y_value = event.values[1];
			float z_value = event.values[2];

			tv_x.setText(String.valueOf(x_value));
			tv_y.setText(String.valueOf(y_value));
			tv_z.setText(String.valueOf(z_value));

			if (z_value >= 0) {
				face.setText("Face up!");

			} else {
				face.setText("Face down!");

			}

			long currenttime = System.currentTimeMillis();

			if (!firsttime) {
				long deltatime = currenttime - lasttime;
				float xrate = Math.abs(x_value - lastx) * 10000 / deltatime;
				float yrate = Math.abs(y_value - lasty) * 10000 / deltatime;
				float zrate = Math.abs(z_value - lastz) * 10000 / deltatime;
				text_xrate.setText(String.valueOf(xrate));
				text_yrate.setText(String.valueOf(yrate));
				text_zrate.setText(String.valueOf(zrate));

				if (xrate > SensitiveX) {
					text_xstate.setText("Shaking:)");
				} else {
					text_xstate.setText("");
				}

				if (yrate > SensitiveY) {
					text_ystate.setText("Shaking:)");
				} else {
					text_ystate.setText("");
				}

				if (zrate > SensitiveZ) {
					text_zstate.setText("Shaking:)");
				} else {
					text_zstate.setText("");
				}

			}
			lasttime = currenttime;
			lastx = x_value;
			lasty = y_value;
			lastz = z_value;
			firsttime = false;
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}
	};
}
