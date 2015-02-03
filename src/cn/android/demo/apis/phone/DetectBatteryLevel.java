package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 检测电量
 * 
 * @author Elroy
 * 
 */
public class DetectBatteryLevel extends Activity {
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_detect_battery_level);
		textView = (TextView) findViewById(R.id.tv_battery_level);

		this.registerReceiver(broadcastReceiver, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));
	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			int mLevel = intent.getIntExtra("level", 0);
			textView.setText("电量：" + String.valueOf(mLevel) + " %");
		}

	};
}
