package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 电池电量信息
 * 
 * @author Elroy
 * 
 */
public class DetectBatteryInfo extends Activity {

	private TextView batteryLevel;
	private TextView batteryVoltage;// 电压
	private TextView batteryTemperature;// 温度
	private TextView batteryTechnology;//
	private TextView batteryStatus;//
	private TextView batteryHealth;//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_detect_battery_info);

		batteryLevel = (TextView) findViewById(R.id.batterylevel);
		batteryVoltage = (TextView) findViewById(R.id.batteryvoltage);
		batteryTemperature = (TextView) findViewById(R.id.batterytemperature);
		batteryTechnology = (TextView) findViewById(R.id.batterytechology);
		batteryStatus = (TextView) findViewById(R.id.batterystatus);
		batteryHealth = (TextView) findViewById(R.id.batteryhealth);

		this.registerReceiver(this.myBatteryReceiver, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));

	}

	private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
				batteryLevel.setText("Level: "
						+ String.valueOf(intent.getIntExtra("level", 0)) + "%");
				batteryVoltage.setText("Voltage: "
						+ String.valueOf((float) intent.getIntExtra("voltage",
								0) / 1000) + "V");
				batteryTemperature.setText("Temperature: "
						+ String.valueOf((float) intent.getIntExtra(
								"temperature", 0) / 10) + "c");
				batteryTechnology.setText("Technology: "
						+ intent.getStringExtra("technology"));

				int status = intent.getIntExtra("status",
						BatteryManager.BATTERY_STATUS_UNKNOWN);
				String strStatus;
				if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
					strStatus = "Charging";
				} else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
					strStatus = "Dis-charging";
				} else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
					strStatus = "Not charging";
				} else if (status == BatteryManager.BATTERY_STATUS_FULL) {
					strStatus = "Full";
				} else {
					strStatus = "Unknown";
				}
				batteryStatus.setText("Status: " + strStatus);

				int health = intent.getIntExtra("health",
						BatteryManager.BATTERY_HEALTH_UNKNOWN);
				String strHealth;
				if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
					strHealth = "Good";
				} else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
					strHealth = "Over Heat";
				} else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
					strHealth = "Dead";
				} else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
					strHealth = "Over Voltage";
				} else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
					strHealth = "Unspecified Failure";
				} else {
					strHealth = "Unknown";
				}
				batteryHealth.setText("Health: " + strHealth);

			}
		}

	};
}
