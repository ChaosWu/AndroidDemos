package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 关于IntentService 用法
 * 
 * @author Elroy
 * 
 */
public class AndroidIntentService extends Activity {

	TextView textResult;
	ProgressBar progressBar;
	//private MyBroadcastReceiver myBroadcastReceiver;
	private MyBroadcastReceiverUpdate broadcastReceiverUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_android_intent_service);
		textResult = (TextView) findViewById(R.id.tv_intent_service_result);
		progressBar = (ProgressBar) findViewById(R.id.pb_intent_service);

		// Start MyIntentService
		Intent intentMyIntentService = new Intent(this, MyIntentService.class);
		intentMyIntentService.putExtra(MyIntentService.EXTRA_KEY_IN,
				"Android-er");
		startService(intentMyIntentService);

		//myBroadcastReceiver = new MyBroadcastReceiver();
		broadcastReceiverUpdate = new MyBroadcastReceiverUpdate();

		IntentFilter filterUpdate = new IntentFilter(
				MyIntentService.ACTION_MyUpdate);
		filterUpdate.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(broadcastReceiverUpdate, filterUpdate);

		// register BroadcastReceiver
		// IntentFilter intentFilter = new IntentFilter(
		// MyIntentService.ACTION_MyIntentService);
		// intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
		// registerReceiver(myBroadcastReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// un-register BroadcastReceiver
		// unregisterReceiver(myBroadcastReceiver);
		unregisterReceiver(broadcastReceiverUpdate);
	}

	// public class MyBroadcastReceiver extends BroadcastReceiver {
	//
	// @Override
	// public void onReceive(Context context, Intent intent) {
	// String result = intent
	// .getStringExtra(MyIntentService.EXTRA_KEY_OUT);
	// textResult.setText(result);
	//
	// }
	//
	// }

	public class MyBroadcastReceiverUpdate extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int update = intent
					.getIntExtra(MyIntentService.EXTRA_KEY_UPDATE, 0);
			progressBar.setProgress(update);
		}

	}

}