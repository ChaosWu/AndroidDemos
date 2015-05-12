package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 辅助跟踪触摸事件的速度
 * 
 * @author Elroy
 * 
 */
public class AndroidVelocityTracker extends Activity {
	TextView tvAction;
	TextView tvMaxVelocityX;
	TextView tvMaxVelocityY;
	TextView tvVelocityX;
	TextView tvVelocityY;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_velocity_tracker);
		
		tvAction=(TextView) findViewById(R.id.tv_android_velocity_tracker_action);
		tvMaxVelocityX=(TextView) findViewById(R.id.tv_android_velocity_tracker_max_velocity_x);
		tvMaxVelocityY = (TextView) findViewById(R.id.tv_android_velocity_tracker_max_velocity_y);
		tvVelocityX=(TextView) findViewById(R.id.tv_android_velocity_tracker_velocity_x);
		tvVelocityY=(TextView) findViewById(R.id.tv_android_velocity_tracker_velocity_y);
	}
}
