package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

/**
 * 
 * @author Elroy
 * 
 */
public class AndroidParcelable extends Activity {
	TextView tv;
	MyBroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tv = new TextView(this);

		setContentView(tv);

		MyParcelable myParcelable = new MyParcelable();
		myParcelable.name = "ElroyWu";
		myParcelable.address = "www.elroywu.com";

		Intent intent = new Intent(this, MyIntentService.class);
		intent.putExtra(MyIntentService.EXTRA_KEY_IN, myParcelable);
		startService(intent);

		receiver = new MyBroadcastReceiver();

		IntentFilter filter = new IntentFilter(
				MyIntentService.ACTION_MyIntentService);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(receiver, filter);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	public class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String result = intent
					.getStringExtra(MyIntentService.EXTRA_KEY_OUT);
			tv.setText(result);
		}

	}

}
