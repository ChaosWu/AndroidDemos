package cn.android.demo.apis.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScheduledReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent intent2 = new Intent(context, ScheduledActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
		
	}

}
