package cn.android.demo.apis.phone;

import java.util.List;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.widget.TextView;

/**
 * 设备传感器
 * 
 * 
 * event.values[0]: azimuth偏振角, rotation around the Z axis.
 * 
 * event.values[1]: pitch倾斜, rotation around the X axis 在x轴旋转.
 * 
 * event.values[2]: roll, rotation around the Y axis .
 * 
 * @author Elroy
 * 
 */
public class OrientationSensor extends Activity {
	private static SensorManager mySensorManager;

	private TextView textviewOrientation;
	private TextView tvAzimuth;
	private TextView tvPitch;
	private TextView tvRoll;

	private OrientationEventListener myOrientationEventListener;

	private boolean sersorrunning;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_orientation_sensor);

		textviewOrientation = (TextView) findViewById(R.id.textorientation);
		tvRoll = (TextView) findViewById(R.id.textroll);
		tvPitch = (TextView) findViewById(R.id.textpitch);
		tvAzimuth = (TextView) findViewById(R.id.textazimuth);

		mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> mySensors = mySensorManager
				.getSensorList(Sensor.TYPE_ORIENTATION);

		if (mySensors.size() > 0) {
			mySensorManager.registerListener(sensorEventListener,
					mySensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
			sersorrunning = true;
			ToastUtil.showToast(this, "Start ORIENTATION Sensor");

		} else {
			ToastUtil.showToast(this, "No ORIENTATION Sensor");
			sersorrunning = false;

		}

		myOrientationEventListener = new OrientationEventListener(this,
				SensorManager.SENSOR_DELAY_NORMAL) {

			@Override
			public void onOrientationChanged(int orientation) {
				Log.v("DDD", "int:" + orientation);
				textviewOrientation.setText("Orientation: "
						+ String.valueOf(orientation));
			}
		};
		// 返回true,如果传感器启用,否则,则返回false
		if (myOrientationEventListener.canDetectOrientation()) {
			ToastUtil.showToast(this, "Can DetectOrientation");
			myOrientationEventListener.enable();
		} else {
			ToastUtil.showToast(this, "Can't DetectOrientation");

		}

	}

	/** 接受通知Sensor的值发生改变 */
	private SensorEventListener sensorEventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			tvAzimuth.setText("Azimuth: " + String.valueOf(event.values[0]));
			tvPitch.setText("Pitch: " + String.valueOf(event.values[1]));
			tvRoll.setText("Roll: " + String.valueOf(event.values[2]));
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 禁用
		myOrientationEventListener.disable();

		if (sersorrunning) {
			mySensorManager.unregisterListener(sensorEventListener);
			ToastUtil.showToast(this, "unregisterListener");
		}
	}
}
