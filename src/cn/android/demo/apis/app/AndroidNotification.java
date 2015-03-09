package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AndroidNotification extends Activity {

	private final String mUrl = "http://www.baidu.com/";

	private Button btStart;
	private Button btStop;
	private Notification notification;
	private NotificationManager notificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_android_notification);

		btStart = (Button) findViewById(R.id.send);
		btStop = (Button) findViewById(R.id.stopservice);
		btStop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(NotifyService.ACTION);
				intent.putExtra("RQS", NotifyService.STOP_SERVICE);
				sendBroadcast(intent);
			}
		});
		btStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AndroidNotification.this,
						NotifyService.class);
				AndroidNotification.this.startService(intent);

				// notificationManager = (NotificationManager)
				// getSystemService(Context.NOTIFICATION_SERVICE);
				// notification = new Notification(R.drawable.chaoswu,
				// "Notification", System.currentTimeMillis());
				// Context context = getApplicationContext();
				//
				// String notificationTitle = "Exercise of Notification!";
				// String notificationText = "http://www.baidu.com/";
				//
				// Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri
				// .parse(mUrl));
				//
				// PendingIntent pendingIntent = PendingIntent.getActivity(
				// AndroidNotification.this, 0, myIntent,
				// Intent.FLAG_ACTIVITY_NEW_TASK);
				//
				// notification.defaults |= Notification.DEFAULT_SOUND;
				// notification.flags |= Notification.FLAG_AUTO_CANCEL;
				// notification.setLatestEventInfo(context, notificationTitle,
				// notificationText, pendingIntent);
				// notificationManager.notify(1, notification);

			}
		});

	}

	public class NotifyService extends Service {

		final static String ACTION = "NotifyServiceAction";
		final static String STOP_SERVICE = "";
		final static int RQS_STOP_SERVICE = 1;

		NotifyServiceReceiver notifyServiceReceiver;

		private static final int MY_NOTIFICATION_ID = 1;
		private NotificationManager notificationManager;
		private Notification myNotification;
		private final String myBlog = "http://www.baidu.com/";

		@Override
		public void onCreate() {
			// TODO Auto-generated method stub
			notifyServiceReceiver = new NotifyServiceReceiver();
			super.onCreate();
		}

		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			// TODO Auto-generated method stub

			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(ACTION);
			registerReceiver(notifyServiceReceiver, intentFilter);

			// Send Notification
			notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			myNotification = new Notification(R.drawable.chaoswu,
					"Notification!", System.currentTimeMillis());
			Context context = getApplicationContext();
			String notificationTitle = "Exercise of Notification!";
			String notificationText = "http://android-er.blogspot.com/";
			Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myBlog));
			PendingIntent pendingIntent = PendingIntent.getActivity(
					getBaseContext(), 0, myIntent,
					Intent.FLAG_ACTIVITY_NEW_TASK);
			myNotification.defaults |= Notification.DEFAULT_SOUND;
			myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
			myNotification.setLatestEventInfo(context, notificationTitle,
					notificationText, pendingIntent);
			notificationManager.notify(MY_NOTIFICATION_ID, myNotification);

			return super.onStartCommand(intent, flags, startId);
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			this.unregisterReceiver(notifyServiceReceiver);
			super.onDestroy();
		}

		@Override
		public IBinder onBind(Intent arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public class NotifyServiceReceiver extends BroadcastReceiver {

			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				int rqs = arg1.getIntExtra("RQS", 0);
				if (rqs == RQS_STOP_SERVICE) {
					stopSelf();
				}
			}
		}

	}
}
