package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 生成器的通知对象
 * 
 * v4 NotificationCompat.Builder 替代 new Notification.Builder
 * 
 * @author Elroy
 * 
 */
public class AndroidNotificationBuilder extends Activity {
	private static final int MY_NOTIFICATION_ID = 1;

	NotificationManager notificationManager;
	Notification notification;
	String blog;
	Button bt;
	Button bt1;

	Context context;
	Intent intent;
	PendingIntent pendingIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		blog = getResources().getString(R.string.my_qq_blog_address);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		context = getApplicationContext();
		intent = new Intent(Intent.ACTION_VIEW, Uri.parse(blog));

		pendingIntent = PendingIntent.getActivity(
				AndroidNotificationBuilder.this, 0, intent,
				Intent.FLAG_ACTIVITY_NEW_TASK);

		setContentView(R.layout.app_android_notification_builder);
		bt = (Button) findViewById(R.id.bt_notification_builder_send);
		bt.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// min sdk 16
				notification = new Notification.Builder(context)
						.setContentTitle("Exercise of Notification")
						.setContentText(blog).setTicker("Notification!")
						.setWhen(System.currentTimeMillis())
						.setContentIntent(pendingIntent)
						.setDefaults(Notification.DEFAULT_SOUND)
						.setAutoCancel(true).setSmallIcon(R.drawable.chaoswu)
						.build();

				notificationManager.notify(MY_NOTIFICATION_ID, notification);
			}
		});

		bt1 = (Button) findViewById(R.id.bt_notification_builder_send1);
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// min sdk 4
				notification = new NotificationCompat.Builder(context)
						.setContentTitle("Exercise of Notification!")
						.setContentText("http://android-er.blogspot.com/")
						.setTicker("Notification!")
						.setWhen(System.currentTimeMillis())
						.setContentIntent(pendingIntent)
						.setDefaults(Notification.DEFAULT_SOUND)
						.setAutoCancel(true)
						.setSmallIcon(R.drawable.ic_launcher).build();
				notificationManager.notify(MY_NOTIFICATION_ID, notification);
			}
		});
	}
}
