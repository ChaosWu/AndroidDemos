package cn.android.demo.apis.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmBroadcastReceier extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "AlarmBroadcastReceier.onReceive",
				Toast.LENGTH_SHORT).show();
	
	}

}
