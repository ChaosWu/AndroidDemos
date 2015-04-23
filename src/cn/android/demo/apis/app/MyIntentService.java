package cn.android.demo.apis.app;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

	public static final String ACTION_MyIntentService = "com.example.androidintentservice.RESPONSE";

	public static final String ACTION_MyUpdate = "com.example.androidintentservice.UPDATE";

	public static final String EXTRA_KEY_UPDATE = "EXTRA_UPDATE";

	public static final String EXTRA_KEY_IN = "EXTRA_IN";
	public static final String EXTRA_KEY_OUT = "EXTRA_OUT";

	String extraIn;
	String extraOut;

	MyParcelable parcelable;

	public MyIntentService() {
		super("com.example.androidintentservice.MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		// get input
		// parcelable = intent.getParcelableExtra(EXTRA_KEY_IN);
		// extraIn = intent.getStringExtra(EXTRA_KEY_IN);
		//
		// extraOut = "Result from MyIntentService: Hello " + "\n"
		// + parcelable.name + "\n" + parcelable.address + "\n";

		// dummy delay for 5 sec
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } // wait 3 sec
		for (int j = 0; j < 10; j++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // wait 3 sec

			// return update
			Intent intentUpdate = new Intent();
			intentUpdate.setAction(ACTION_MyUpdate);
			intentUpdate.addCategory(Intent.CATEGORY_DEFAULT);
			intentUpdate.putExtra(EXTRA_KEY_UPDATE, j);
			sendBroadcast(intentUpdate);
		}

		// return result
		// Intent intentResponse = new Intent();
		// intentResponse.setAction(ACTION_MyIntentService);
		// intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
		// intentResponse.putExtra(EXTRA_KEY_OUT, extraOut);
		// sendBroadcast(intentResponse);
	}

}