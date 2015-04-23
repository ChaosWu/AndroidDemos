package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyIntentService extends IntentService {

	public static final String ACTION_MyIntentService = "com.example.androidintentservice.RESPONSE";

	public static final String ACTION_MyUpdate = "com.example.androidintentservice.UPDATE";

	public static final String EXTRA_KEY_UPDATE = "EXTRA_UPDATE";

	public static final String EXTRA_KEY_IN = "EXTRA_IN";
	public static final String EXTRA_KEY_OUT = "EXTRA_OUT";

	private static final int MY_NOTIFICATION_ID = 1;

	String extraIn;
	String extraOut;

	MyParcelable parcelable;

	NotificationManager notificationManager;
	Notification myNotification;

	public MyIntentService() {
		super("com.example.androidintentservice.MyIntentService");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
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
		for (int j = 0; j <= 10; j++) {
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

			// generate notification
			String notificationText = String.valueOf((int) (100 * j / 10))
					+ " %";
			PendingIntent pendingIntent = PendingIntent.getActivity(
					MyIntentService.this, 0, new Intent(), // Dummy Intent do
															// nothing
					Intent.FLAG_ACTIVITY_NEW_TASK);

			myNotification = new NotificationCompat.Builder(
					getApplicationContext()).setContentTitle("Progress")
					.setContentText(notificationText)
					.setTicker("Notification!")
					.setWhen(System.currentTimeMillis())
					.setContentIntent(pendingIntent)
					.setDefaults(Notification.DEFAULT_SOUND)
					.setAutoCancel(true).setSmallIcon(R.drawable.chaoswu)
					.build();

			notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
		}

		// return result
		// Intent intentResponse = new Intent();
		// intentResponse.setAction(ACTION_MyIntentService);
		// intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
		// intentResponse.putExtra(EXTRA_KEY_OUT, extraOut);
		// sendBroadcast(intentResponse);
	}
}