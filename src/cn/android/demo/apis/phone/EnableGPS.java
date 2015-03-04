package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
/**
 * 
 * @author Elroy
 *
 */
public class EnableGPS extends Activity {
	Button  btAlarm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_enable_gps);
		btAlarm=(Button) findViewById(R.id.bt_alarm_service);
		btAlarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CheckEnableGPS();
			}
		});
	}

	private void CheckEnableGPS() {
		String provider = Settings.Secure.getString(getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		if (!provider.equals("")) {
			Toast.makeText(this, "GPS Enabled:" + provider, 1);
		}else if (provider.contains("gps")) {
			Toast.makeText(this, "GPS Enabled:" + provider, 1);
		} else  {
			Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
			startActivity(intent);
		}
	}
}
