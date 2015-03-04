package cn.android.demo.apis.ui.widget;

import java.util.List;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

public class Compass extends Activity {

	private static SensorManager mySensorManager;
	private boolean sersorrunning;
	private CompassView myCompassView;
	private HorizontalView horizontalView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_compass);

		myCompassView = (CompassView) findViewById(R.id.mycompassview);
		horizontalView = (HorizontalView) findViewById(R.id.myhorizontalview);
		mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> mySensors = mySensorManager
				.getSensorList(Sensor.TYPE_ORIENTATION);

		if (mySensors.size() > 0) {
			mySensorManager.registerListener(mySensorEventListener,
					mySensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
			sersorrunning = true;
			Toast.makeText(this, "Start ORIENTATION Sensor", Toast.LENGTH_LONG)
					.show();

		} else {
			Toast.makeText(this, "No ORIENTATION Sensor", Toast.LENGTH_LONG)
					.show();
			sersorrunning = false;
			finish();
		}
	}

	private SensorEventListener mySensorEventListener = new SensorEventListener() {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			myCompassView.updateDirection((float) event.values[0]);
			horizontalView.updateHorizontal((float) event.values[1],
					(float) event.values[2]);
		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		if (sersorrunning) {
			mySensorManager.unregisterListener(mySensorEventListener);
		}
	}

}